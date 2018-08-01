package com.hongwei.futures.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "hhr_app_version")
public class HhrAppVersion implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5521190614320312730L;
	private Long id;
	private Integer versionCode;
	private String versionName;
	private String downloadUrl;
	private String updateLog;
	private Integer forceUpdate;
	private Integer appType;
	
	public HhrAppVersion() {
		super();
	}

	public HhrAppVersion(Long id, Integer versionCode, String versionName, String downloadUrl, String updateLog, Integer forceUpdate, Integer appType) {
		super();
		this.id = id;
		this.versionCode = versionCode;
		this.versionName = versionName;
		this.downloadUrl = downloadUrl;
		this.updateLog = updateLog;
		this.forceUpdate = forceUpdate;
		this.appType = appType; 
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "version_code")
	public Integer getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	@Column(name = "version_name")
	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	@Column(name = "download_url")
	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	@Column(name = "update_log")
	public String getUpdateLog() {
		return updateLog;
	}

	public void setUpdateLog(String updateLog) {
		this.updateLog = updateLog;
	}

	@Column(name = "app_type")
	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	@Column(name = "force_update")
	public Integer getForceUpdate() {
		return forceUpdate;
	}

	public void setForceUpdate(Integer forceUpdate) {
		this.forceUpdate = forceUpdate;
	}
	
	
}
