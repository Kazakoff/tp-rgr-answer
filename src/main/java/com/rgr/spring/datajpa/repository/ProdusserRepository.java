package com.rgr.spring.datajpa.repository;

import com.rgr.spring.datajpa.model.GoodProds2;
import com.rgr.spring.datajpa.model.Produsser;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface ProdusserRepository extends JpaRepository<Produsser, Long> {
	List<Produsser> findByTitleContaining(String title);
        @Query(value="select t.prod from (select g.name, p.name as prod, count(g.id) from Produsser p, Film f, Genre g where p.id = f.id_produsser and g.id = f.id_genre group by g.name, p.name) as t group by t.prod having count(t.prod)=1", nativeQuery = true)
        List<String> findQ();
        @Query(value="select p from Produsser p, Film f, Genre g where p.id = f.id_produsser and g.id = f.id_genre group by g.title, p.title")
        List<Produsser> findQ2();
        
        //https://en.wikibooks.org/wiki/Java_Persistence/Querying#Query_Results
        @Query(value="select new com.rgr.spring.datajpa.model.GoodProds2(p.title as name, count(g.id) as cnt) from Produsser p, Film f, Genre g where p.id = f.id_produsser and g.id = f.id_genre group by g.title, p.title")
        Collection<GoodProds2> findQ3();
}
