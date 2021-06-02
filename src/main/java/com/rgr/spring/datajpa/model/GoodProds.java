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

@Entity
@Immutable
@Table(name = "goodprods")
public class GoodProds {

	@Id
        @Column(name = "Name")
	private String name;

	@Column(name = "Cnt")
	private Long cnt;

	public GoodProds() {

	}

	public String getName() {
		return name;
	}

	public Long getCnt() {
		return cnt;
	}

	/*public void setName(String name) {
		this.name = name;
	}
	public void setCnt(Long cnt) {
		this.cnt = cnt;
	}
*/
}
