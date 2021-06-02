package com.rgr.spring.datajpa.controller;

import com.rgr.spring.datajpa.model.GoodProds;
import com.rgr.spring.datajpa.model.GoodProds2;
import com.rgr.spring.datajpa.model.Produsser;
import com.rgr.spring.datajpa.repository.GoodProdusserRepository;
import com.rgr.spring.datajpa.repository.ProdusserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:8084")
@RestController
@RequestMapping("/api")
public class ProdusserController {

	@Autowired
	ProdusserRepository repository;
        
        @Autowired
	GoodProdusserRepository goodRepository;

	@GetMapping("/produsser")
	public ResponseEntity<List<Produsser>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			List<Produsser> produsserList = new ArrayList<Produsser>();

			if (title == null)
				repository.findAll().forEach(produsserList::add);
			else
				repository.findByTitleContaining(title).forEach(produsserList::add);

			if (produsserList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(produsserList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/produsser/q")
	public ResponseEntity<List<String>> getQ() {
		List<String> tutorialData = repository.findQ();

			return new ResponseEntity<>(tutorialData, HttpStatus.OK);
	}

        @GetMapping("/produsser/q2")
	public ResponseEntity<Set<Produsser>> getQ2() {
		List<Produsser> produssers = repository.findQ2();
                Set<Produsser> goodProdussers = new HashSet();
                for (Produsser pr:produssers){
                    int cnt = 0;
                    for (Produsser pr2:produssers){
                        if (pr.equals(pr2)) cnt++;
                    }
                    if (cnt == 1) goodProdussers.add(pr);
                }

			return new ResponseEntity<>(goodProdussers, HttpStatus.OK);
	}
        
        @GetMapping("/produsser/qg")
	public ResponseEntity<List<GoodProds>> getQG() {
		List<GoodProds> produssers = goodRepository.findAll();
               /* for (GoodProds pr:produssers){
                    if (pr.getCnt()!=1) produssers.remove(pr);
                }
*/
			return new ResponseEntity<>(produssers, HttpStatus.OK);
	}
        @GetMapping("/produsser/q3")
	public ResponseEntity<Collection<GoodProds2>> getQ3() {
		Collection<GoodProds2> produssers = repository.findQ3();
               /* for (GoodProds pr:produssers){
                    if (pr.getCnt()!=1) produssers.remove(pr);
                }
*/
			return new ResponseEntity<>(produssers, HttpStatus.OK);
	}
        
	@PostMapping("/produsseradmin")
	public ResponseEntity<Produsser> createTutorial(@RequestBody Produsser film) {
		try {
			Produsser _produsser = repository
					.save(new Produsser(film.getTitle()/*, tutorial.getDescription(), false*/));
			return new ResponseEntity<>(_produsser, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/produsseradmin/{id}")
	public ResponseEntity<Produsser> updateTutorial(@PathVariable("id") long id, @RequestBody Produsser film) {
		Optional<Produsser> tutorialData = repository.findById(id);

		if (tutorialData.isPresent()) {
			Produsser _produsser = tutorialData.get();
			_produsser.setTitle(film.getTitle());
			/*_tutorial.setDescription(tutorial.getDescription());
			_tutorial.setPublished(tutorial.isPublished());*/
			return new ResponseEntity<>(repository.save(_produsser), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/produsseradmin/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/produsseradmin")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			repository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
