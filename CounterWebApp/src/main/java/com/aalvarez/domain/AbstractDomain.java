package com.aalvarez.domain;

import java.io.Serializable;

public abstract class AbstractDomain implements Serializable{
	 	private static final long serialVersionUID = 1L;

	    public abstract Long getId();
	    
	    public abstract void setId(Long id);
}
