/**
 * 
 */
package com.hongwei.futures.util;

/**
 * @author Jackie.Gao
 *
 */
public class QueryBean {

	private String MerId;
	private String OrdId;
	private String TransDate;
	private String TransType;
	private String Version;
	private String Resv;
	private String ChkValue;
	
	public QueryBean(){
		
	}
	
	public String toString(){
		return new StringBuffer("MerId=").append(MerId)
		.append("&OrdId=").append(OrdId)
		.append("&TransDate=").append(TransDate)
		.append("&TransType=").append(TransType)
		.append("&Version=").append(Version)
		.append("&Resv=").append(Resv)
		.append("&ChkValue=").append(ChkValue).toString();
	}
	
	public String getMerId() {
		return MerId;
	}
	public void setMerId(String merId) {
		MerId = merId;
	}
	public String getOrdId() {
		return OrdId;
	}
	public void setOrdId(String ordId) {
		OrdId = ordId;
	}
	public String getTransDate() {
		return TransDate;
	}
	public void setTransDate(String transDate) {
		TransDate = transDate;
	}
	public String getTransType() {
		return TransType;
	}
	public void setTransType(String transType) {
		TransType = transType;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String getResv() {
		return Resv;
	}
	public void setResv(String resv) {
		Resv = resv;
	}
	public String getChkValue() {
		return ChkValue;
	}
	public void setChkValue(String chkValue) {
		ChkValue = chkValue;
	}
}
