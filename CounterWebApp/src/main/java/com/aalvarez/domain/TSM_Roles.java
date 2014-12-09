package com.aalvarez.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TSM_ROLES")
public class TSM_Roles extends TrackDomainsChanges{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ROLE_ID")
	@SequenceGenerator(name="ROLE_ID_SEQ", allocationSize=10)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROLE_ID_SEQ")
	private Long id;

	@Column(name = "ROLE_NAME", length = 25)
	private String roleName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
