package com.aalvarez.utils;

import com.aalvarez.model.Person;
import com.aalvarez.domain.TSM_Person;


public class Util {
	public static void setPersonData(Person person, TSM_Person tsmemp) throws Exception {
		person.setId(tsmemp.getId());
		person.setFirstName(tsmemp.getFirstName());	
		person.setMiddleName(tsmemp.getMiddleName());
			if(tsmemp.getMiddleName() == null)
				person.setMiddleName("");
		
		person.setLastName(tsmemp.getLastName());
		
		person.setAddress(tsmemp.getAddress());
			if(tsmemp.getAddress() == null)
				person.setAddress("");
		
		person.setCity(tsmemp.getCity());
			if(tsmemp.getCity()==null)
				person.setCity("");
		person.setState(tsmemp.getState());
			if(tsmemp.getState()==null)
				person.setState("");
		person.setZipCode(tsmemp.getZip());
		if(tsmemp.getZip()==null)
			person.setZipCode((long) 0);
	}
	
	public static void setPersonData(TSM_Person tsmemp,Person person) throws Exception {
		tsmemp.setFirstName(person.getFirstName());	
		tsmemp.setMiddleName(person.getMiddleName());
			if(tsmemp.getMiddleName() == null)
				person.setMiddleName("");
		
		tsmemp.setLastName(person.getLastName());
		
		tsmemp.setAddress(person.getAddress());
		if(person.getAddress() == null)
			tsmemp.setAddress("");
		
		tsmemp.setCity(person.getCity());
		if(person.getCity()==null)
			person.setCity("");
		
		tsmemp.setState(person.getState());
		if(person.getState()==null)
			tsmemp.setState("");
		
		tsmemp.setZip(person.getZipCode());
		if(person.getZipCode()==null)
			tsmemp.setZip((long) 0);
	}

}
