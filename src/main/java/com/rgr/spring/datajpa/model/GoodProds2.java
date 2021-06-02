/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rgr.spring.datajpa.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.annotation.Immutable;


public class GoodProds2 {

 	@Id
	private final String name;
	private final Long cnt;

	public GoodProds2(String name,Long cnt) {
            this.name = name;
            this.cnt = cnt;

	}

	public String getName() {
		return name;
	}

	public Long getCnt() {
		return cnt;
	}


}
