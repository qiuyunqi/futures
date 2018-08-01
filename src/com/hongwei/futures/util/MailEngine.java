package com.hongwei.futures.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author 闫成 发送邮件的引擎
 */
@Component("mailEngine")
public class MailEngine {

	private Log log = LogFactory.getLog(MailEngine.class);
	@Resource
	private FreeMarkerConfig freeMarkerConfig;
	@Resource
	private MailSender mailSender;

	public String generateEmailContent(String templateName, Map map) {
		// 使用FreeMaker模板
		try {
			Configuration configuration = freeMarkerConfig.getConfiguration();
			Template t = configuration.getTemplate(templateName);
			return FreeMarkerTemplateUtils.processTemplateIntoString(t, map);
		} catch (TemplateException e) {
			log.error("Error while processing FreeMarker template ", e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			// log.error("Error while open template file ", e);
		} catch (IOException e) {
			log.error("Error while generate Email Content ", e);
		}
		return null;
	}

	/**
	 * 发送邮件
	 * 
	 * @param emailAddress
	 *            收件人Email地址的数组
	 * @param fromEmail
	 *            寄件人Email地址, null为默认寄件人web@vnvtrip.com
	 * @param bodyText
	 *            邮件正文
	 * @param subject
	 *            邮件主题
	 * @param attachmentName
	 *            附件名
	 * @param resource
	 *            附件
	 * @throws MessagingException
	 */
	public void sendMessage(String[] emailAddresses, String fromEmail,
			String bodyText, String subject, String attachmentName,
			ClassPathResource resource) throws MessagingException {
		MimeMessage message = ((JavaMailSenderImpl) mailSender)
				.createMimeMessage();

		// use the true flag to indicate you need a multipart message
		//MimeMessageHelper helper = new MimeMessageHelper(message, true);
		MimeMessageHelper helper = new MimeMessageHelper(message, true,
		"UTF-8");
		helper.setTo(emailAddresses);
		if (fromEmail != null) {
			helper.setFrom(fromEmail);
		}
		helper.setText(bodyText, true);
		helper.setSubject(subject);

		if (attachmentName != null && resource != null)
			helper.addAttachment(attachmentName, resource);

		((JavaMailSenderImpl) mailSender).send(message);
	}

	/**
	 * 使用模版发送HTML格式的邮件
	 * 
	 * @param msg
	 *            装有to,from,subject信息的SimpleMailMessage
	 * @param templateName
	 *            模版名,模版根路径已在配置文件定义于freemakarengine中
	 * @param model
	 *            渲染模版所需的数据
	 */
	public void send(SimpleMailMessage msg, String templateName, Map model) {
		// 生成html邮件内容
		msg.setFrom("中创盟<services@csia.cc>");
		String content = generateEmailContent(templateName, model);
		MimeMessage mimeMsg = null;
		try {
			mimeMsg = ((JavaMailSenderImpl) mailSender).createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true,
					"UTF-8");
			helper.setTo(msg.getTo());

			if (msg.getSubject() != null)
				helper.setSubject(msg.getSubject());

			if (msg.getFrom() != null)
				helper.setFrom(msg.getFrom());

			helper.setText(content, true);

			((JavaMailSenderImpl) mailSender).send(mimeMsg);
		} catch (MessagingException ex) {
			log.error(ex.getMessage(), ex);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
