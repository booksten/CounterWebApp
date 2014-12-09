package com.aalvarez.domain;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.aalvarez.domain.TSM_Roles;
import com.aalvarez.domain.TSM_Person;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name="TSM_USERINFO")
public class TSM_UserInfo extends TrackDomainsChanges implements UserDetails{
	
	@Id
	@Column(name = "USERID")
	@SequenceGenerator(name = "TSM_USERINFO_SEQ", sequenceName = "tsm_ui_seq", allocationSize = 10, initialValue = 100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TSM_USERINFO_SEQ")
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID")
	private TSM_Person employee;
    
    @Column(name = "USERNAME", length = 45)
    private String userName;
    
    @Column(name = "PASSWORD", length = 45)
    private String password;
    
    @ManyToOne
	@JoinColumn(name = "ROLE_ID")
	private TSM_Roles role;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TSM_Person getEmployee() {
		return employee;
	}

	public void setEmployee(TSM_Person employee) {
		this.employee = employee;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return isActive();
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return isActive();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return isActive();
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isActive();
	}
	
	private boolean isActive() {
		
		return true;
	}
}
