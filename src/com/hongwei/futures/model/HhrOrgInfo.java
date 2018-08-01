package com.hongwei.futures.model;

public class HhrOrgInfo implements java.io.Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6773433225860537954L;
	private int id;
    private String orgId;
    private String orgName;
    private String cyryNum;
    public HhrOrgInfo() {
        super();
    }
    public HhrOrgInfo(int id, String orgId, String orgName, String cyryNum) {
        super();
        this.id = id;
        this.orgId = orgId;
        this.orgName = orgName;
        this.cyryNum = cyryNum;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getOrgId() {
        return orgId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    public String getOrgName() {
        return orgName;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public String getCyryNum() {
        return cyryNum;
    }
    public void setCyryNum(String cyryNum) {
        this.cyryNum = cyryNum;
    }

}
