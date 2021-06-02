/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rgr.spring.datajpa.repository;


import com.rgr.spring.datajpa.model.GoodProds;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */

public interface GoodProdusserRepository extends JpaRepository<GoodProds, String> {
//	List<GoodProds> findAll();
}
