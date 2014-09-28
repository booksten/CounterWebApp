package com.aalvarez.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TSM_PERSON")
public class TSM_Person extends TrackDomainsChanges{
	
	private static final long serialVersionUID = 2005684647562005075L;
	
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "TSM_PERSON_SEQ", sequenceName = "tsm_per_seq", allocationSize = 10, initialValue = 100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TSM_PERSON_SEQ")
    private Long id;
	
    @Column(name = "FIRST_NAME", length = 45)
    private String firstName;
    
    @Column(name = "MIDDLE_NAME", length = 45)
    private String middleName;
    
    @Column(name = "LAST_NAME", length = 45)
    private String lastName;
    
    @Column(name = "ADDRESS", length = 45)
    private String address;
    
    @Column(name = "ZIP_CODE")
    private Long zip;

    @Column(name ="CITY", length = 30)
    private String city;
    
    @Column(name ="STATE", length = 2)
    private String state;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getZip() {
		return zip;
	}

	public void setZip(Long zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
