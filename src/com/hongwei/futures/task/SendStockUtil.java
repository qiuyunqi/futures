package com.hongwei.futures.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.FuTransaction;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.StockShare;
import com.hongwei.futures.service.FuStockAccountService;
import com.hongwei.futures.service.FuTransactionService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.StockShareService;
import com.hongwei.futures.util.XmlUtil;

public class SendStockUtil extends TimerTask {
	@Autowired
	private FuStockAccountService fuStockAccountService;
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private FuTransactionService fuTransactionService;
	@Autowired
	private StockShareService stockShareService;

	private Object lock = new Object();
	private volatile boolean isRunning = false;

	/**
	 * 定期发劵
	 */
	@Override
	public void run() {
		synchronized (lock) {
			if (isRunning) {
				return;
			}
			isRunning = true;
			String xmlPath = XmlUtil.getWebRootAbsolutePath() + "uploads/xml/stockAccount.xml";
			List<FuUser> users = fuUserService.findAllUsers();
			List<FuTransaction> transactions = fuTransactionService.findAllTrans();

			int k = 0;
			NodeList nodeList = XmlUtil.getNodeList(xmlPath, "Account");
			if (nodeList != null && nodeList.getLength() > 0) {
				for (int i = 0; i < nodeList.getLength(); i++) {
					if (k >= 3) {// 只抽取三个劵发送出去
						break;
					}
					Element fatherNode = (Element) nodeList.item(i);// 父节点Account
					String fatherId = fatherNode.getAttributes().item(0).getNodeValue();// 父节点id属性值

					NodeList childNodes = fatherNode.getChildNodes();
					String childName = "";
					String childValue = "";
					for (int j = 0; j < childNodes.getLength(); j++) {
						Node childNode = childNodes.item(j);
						childName = childNode.getNodeName();// 子节点名称
						if (childName.equals("isPayMargin") && childNode instanceof Element) {
							childValue = childNode.getFirstChild().getNodeValue();// 子节点值
							break;
						}
					}
					// 没有缴纳保证金代表还没有发出去，可以发劵
					if (childName.equals("isPayMargin") && childValue.equals("0")) {
						System.out.println("发劵任务fatherId =" + fatherId);
						// 取当前父节点里的子节点值添加到股票表
						int userIndex = new Random().nextInt(users.size() - 1);
						int tranIndex = new Random().nextInt(transactions.size() - 1);
						FuStockAccount account = new FuStockAccount();
						account.setFuUser(users.get(userIndex));
						account.setTransactionId(transactions.get(tranIndex).getId());
						account.setOpenUser(fatherNode.getElementsByTagName("openUser").item(0).getTextContent());
						account.setOpenEquity(fatherNode.getElementsByTagName("openEquity").item(0).getTextContent());
						account.setSalesDept(fatherNode.getElementsByTagName("salesDept").item(0).getTextContent());
						account.setCapitalAccount(fatherNode.getElementsByTagName("capitalAccount").item(0).getTextContent());
						account.setCapitalPassword(fatherNode.getElementsByTagName("capitalPassword").item(0).getTextContent());
						account.setAccountType(Integer.valueOf(fatherNode.getElementsByTagName("accountType").item(0).getTextContent()));
						account.setCreateTime(new Date());
						account.setState(0);// 开启委托
						account.setIsDel(0);// 未删除
						account.setExamineStatus(4);// 接单成功
						account.setProfitModel(Integer.parseInt(fatherNode.getElementsByTagName("profitModel").item(0).getTextContent()));
						account.setAvailable(new BigDecimal(fatherNode.getElementsByTagName("available").item(0).getTextContent()));
						account.setAvailableSplit(fatherNode.getElementsByTagName("availableSplit").item(0).getTextContent());
						account.setAbleMoney(new BigDecimal(fatherNode.getElementsByTagName("ableMoney").item(0).getTextContent()));
						// account.setAbleMoneySplit(fatherNode.getElementsByTagName("ableMoneySplit").item(0).getTextContent());
						account.setTransactionStatus(0);// 未退劵
						account.setCommission(fatherNode.getElementsByTagName("commission").item(0).getTextContent());
						account.setOrderTime(new Date());
						account.setIsPublish(1);// 已发布
						account.setSourceType(0);// 网站
						account.setProfitConfig(new BigDecimal(fatherNode.getElementsByTagName("profitConfig").item(0).getTextContent()));
						account.setMessage(fatherNode.getElementsByTagName("message").item(0).getTextContent());
						account.setIsPayMargin(1);// 已缴纳保证金，不会再次发此券
						fuStockAccountService.save(account);

						// 把对应的股票信息添加到股票表
						for (int m = 0; m < childNodes.getLength(); m++) {
							Node shares = childNodes.item(m);
							if (shares.getNodeType() == Node.ELEMENT_NODE && shares.getNodeName().equals("Shares")) {// 得到Shares节点对象
								NodeList shareChild = shares.getChildNodes();// 得到share节点对象数组
								for (int n = 0; n < shareChild.getLength(); n++) {
									if (shareChild.item(n) instanceof Element) {
										Element shareNode = (Element) shareChild.item(n);// 得到share对象
										StockShare share = new StockShare();
										share.setFuStockAccount(account);
										share.setName(shareNode.getElementsByTagName("name").item(0).getTextContent());
										share.setCode(shareNode.getElementsByTagName("code").item(0).getTextContent());
										share.setNumber(Integer.valueOf(shareNode.getElementsByTagName("number").item(0).getTextContent()));
										stockShareService.save(share);
									}
								}
							}
						}
						users.remove(userIndex);// 从数组移除，避免重复发劵
						transactions.remove(tranIndex);// 从数组移除，避免重复派劵

						// 修改对应id父节点的指定名称子节点的值
						XmlUtil.updateNode(XmlUtil.getDocument(xmlPath), xmlPath, "Account", "id", fatherId, "isPayMargin", "1");
						k = k + 1;
					} else {
						continue;
					}
				}
			}
			isRunning = false;
		}
	}

	public static void main(String args[]) {
		// SendStockUtil stockUtil = new SendStockUtil();
		// stockUtil.run();
	}

}