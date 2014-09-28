package com.aalvarez.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.security.core.context.SecurityContextHolder;

@MappedSuperclass
public abstract class TrackDomainsChanges extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	@Column(name = "DATE_CREATED")
	private Timestamp dateCreated;

	@Column(name = "CREATED_BY", length = 100)
	private String createdBy;

	@Column(name = "DATE_MODIFIED")
	private Timestamp dateModified;

	@Column(name = "MODIFIED_BY", length = 100)
	private String modifiedBy;

	@Column(name = "DESCRIPTION", length = 255)
	private String description;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Timestamp getDateModified() {
		return dateModified;
	}

	public void setDateModified(Timestamp dateModified) {
		this.dateModified = dateModified;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@PrePersist
	protected void onCreate() {
		if (dateCreated == null){
			dateCreated = new Timestamp(System.currentTimeMillis());
			dateModified = dateCreated;
		}
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
		TSM_Person user = (TSM_Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			createdBy = "" + user.getId();
			modifiedBy = createdBy;
		}
	}

	@PreUpdate
	protected void onUpdate() {
		//System.out.println("modified");
		dateModified = new Timestamp(System.currentTimeMillis());
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			TSM_Person user = (TSM_Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			modifiedBy = "" + user.getId();
		}
	}

}
