package com.hongwei.futures.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

public class ExportExcel {
	// 创建工作区
	private HSSFWorkbook wb = null;
	// 创建一个表
	private HSSFSheet sheet = null;

	public ExportExcel() {
		wb = new HSSFWorkbook();
		sheet = wb.createSheet();

	}

	/**
	 * 将数据导出到本地的excel表
	 * 
	 * @param head
	 *            表头数据
	 * @param dataSet
	 *            文件的内容数据，dataSet封装了Map，Map封装要导出的每行的数据,Map的key对应每行中单元格所在的位置
	 * @param headerIndex
	 *            表头从哪行开始写起 因为还有表头的说明信息是放在表头之上
	 */
	@SuppressWarnings("unchecked")
	public boolean exportToExcel(String[] headData, List dataSet,
			int headerIndex) {
		if (headerIndex < 0) {
			return false;
		}
		OutputStream output = getStream();
		boolean exportSuccess = true;
		setHeader(headData, headerIndex);
		setRows(dataSet, headerIndex + 1);
		try {
			wb.write(output);
			output.flush();
			output.close();

		} catch (IOException e) {
			e.printStackTrace();

		}

		return exportSuccess;

	}

	public boolean exportToExcel(String[] headData, List dataSet,
			int headerIndex, String filepath) {
		if (headerIndex < 0) {
			return false;

		}

		boolean exportSuccess = true;
		setHeader(headData, headerIndex);
		setRows(dataSet, headerIndex + 1);
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(filepath);
			wb.write(fileOutputStream);
			fileOutputStream.flush();
			fileOutputStream.close();

		} catch (IOException e) {
			exportSuccess = false;
			e.printStackTrace();

		}

		return exportSuccess;
	}

	/**
	 * 设置excel表头
	 */
	@SuppressWarnings("deprecation")
	public void setHeader(String[] headData, int headerIndex) {
		// 创建表头
		HSSFRow hssfHead = sheet.createRow(headerIndex);
		hssfHead.setHeight((short) 900);// 设置行高
		// 设置头样式

		HSSFCellStyle style = CellStyle.geHeadCellStyle(wb);
		// 填充表头数据
		for (int i = 0; i < headData.length; i++) {

			HSSFCell headCell = hssfHead.createCell((short) i);
			headCell.setCellStyle(style);
			sheet.autoSizeColumn((short) i);

			headCell.setCellValue(headData[i]);

		}
	}

	/**
	 * 创建行
	 */
	@SuppressWarnings( { "unchecked" })
	public void setRows(List dataSet, int headerIndex) {
		if (dataSet != null && dataSet.size() > 0) {
			int rowNum = dataSet.size();

			for (int i = 0; i < rowNum; i++) {

				HSSFRow row = sheet.createRow(i + headerIndex);
				row.setHeight((short) 900);// 设置行高
				Object rowdata = dataSet.get(i);
				setCells(rowdata, row, headerIndex);
			}

		}

	}

	/**
	 * 填充行单元格数据
	 * 
	 * @param rowdata
	 *            每行的数据
	 * @param row
	 *            要填充的行
	 */
	@SuppressWarnings( { "deprecation", "unchecked" })
	public void setCells(Object rowdata, HSSFRow row, int headerIndex) {
		int cellNum = 0;

		if (rowdata instanceof HashMap) {
			cellNum = ((HashMap) rowdata).size();

		}
		if (rowdata instanceof List) {
			cellNum = ((List) rowdata).size();
		}

		// 设置表内容样式

		for (int i = 0; i < cellNum; i++) {
			HSSFCell cell = row.createCell((short) i);

			if (headerIndex != 0) {// 表内容信息
				HSSFCellStyle style = CellStyle.getContentStyle(wb);
				cell.setCellStyle(style);// 设置样式

			} else
				cell.setCellStyle(CellStyle.getHeadInfo(wb)); // 表说明信息的样式
			sheet.autoSizeColumn((short) i);
			Object celldata = null;
			if (rowdata instanceof HashMap) {
				celldata = ((HashMap) rowdata).get(i);

			}
			if (rowdata instanceof List) {
				celldata = ((List) rowdata).get(i);
			}
			try {
				switch (getDataType(celldata)) {
				case 0:
					cell.setCellValue("");
					break;// 单元格数据为空
				case 2:
					SimpleDateFormat sdf = new SimpleDateFormat();
					String dateData = sdf.format(celldata); // 数据为Date类型
					cell.setCellValue(dateData);
					break;

				case 3:
					cell.setCellValue(Integer.parseInt(celldata.toString()));
					break;// 数据为整型
				case 4:
					cell.setCellValue(Byte.parseByte(celldata.toString()));
					break;// 数据为Byte型
				case 5:
					cell.setCellValue(Float.parseFloat(celldata.toString()));
					break;// 数据为FLOAT型
				case 6:
					cell.setCellValue(Double.parseDouble(celldata.toString()));
					break;// 数据为DOUBLE 型
					// case 7:cell.setCellValue((Boolean)celldata);break;
					// //数据为Boolean型,要根据boolean类型代表的字段意义进行判断

				default:
					HSSFRichTextString cellrichString = new HSSFRichTextString(
							celldata.toString());

					cell.setCellValue(cellrichString);
					break; // string 类型

				}
			} catch (NumberFormatException e) {
				e.printStackTrace();

			}

		}

	}

	/**
	 * 验证数据的类型
	 * 
	 * @param data
	 * @return
	 */
	private int getDataType(Object data) {

		int typeState = 1;
		if (null != data) {

			if (data instanceof String) {
				typeState = 1;
			} else if (data instanceof Date) {
				typeState = 2;
			} else if (data instanceof Integer) {
				typeState = 3;
			} else if (data instanceof Byte) {
				typeState = 4;
			} else if (data instanceof Float) {
				typeState = 5;
			} else if (data instanceof Double) {
				typeState = 6;
			} else if (data instanceof Boolean) {
				typeState = 7;
			}
		} else
			typeState = 0;
		return typeState;
	}

	/**
	 * 获取输出流
	 * 
	 * @return
	 */
	public OutputStream getStream() {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("octets/ms-excel");
		response.addHeader("Content-Disposition", "attachment;filename="
				+ System.currentTimeMillis() + ".xls");
		OutputStream output = null;
		try {
			output = response.getOutputStream();
		} catch (IOException e1) {
			// System.out.println("不能获取输出流");

			e1.printStackTrace();
		}
		return output;
	}

	public HSSFWorkbook getWb() {
		return wb;
	}

	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
	}

	public HSSFSheet getSheet() {
		return sheet;
	}

	public void setSheet(HSSFSheet sheet) {
		this.sheet = sheet;
	}

}
