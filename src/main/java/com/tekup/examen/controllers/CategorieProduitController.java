package com.tekup.examen.controllers;

import java.util.List;

import com.tekup.examen.services.ICategorieProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.tekup.examen.entities.CategorieProduitDTO;
import io.swagger.annotations.Api;

@RestController
@Api(tags = "Gestion des categories Produit")
@RequestMapping("/categorieProduit")
public class CategorieProduitController {

	@Autowired
    ICategorieProduitService categorieProduitService;
	
	// http://localhost:8089/SpringMVC/categorieProduit/retrieve-all-categorieProduit
	@GetMapping("/retrieve-all-categorieProduit")
	@ResponseBody
	public List<CategorieProduitDTO> getCategorieProduit() {
		return categorieProduitService.retrieveAllCategorieProduits();
	}

	// http://localhost:8089/SpringMVC/categorieProduit/retrieve-categorieProduit/8
	@GetMapping("/retrieve-categorieProduit/{categorieProduit-id}")
	@ResponseBody
	public CategorieProduitDTO retrieveCategorieProduit(@PathVariable("categorieProduit-id") Long categorieProduitId) {
		return categorieProduitService.retrieveCategorieProduit(categorieProduitId);
	}

	// http://localhost:8089/SpringMVC/categorieProduit/add-categorieProduit
	@PostMapping("/add-categorieProduit")
	@ResponseBody
	public CategorieProduitDTO addCategorieProduit(@RequestBody CategorieProduitDTO cp) {
		return categorieProduitService.addCategorieProduit(cp);
	}


	@DeleteMapping("/remove-categorieProduit/{categorieProduit-id}")
	@ResponseBody
	public void removeCategorieProduit(@PathVariable("categorieProduit-id") Long categorieProduitId) {
		categorieProduitService.deleteCategorieProduit(categorieProduitId);
	}

	// http://localhost:8089/SpringMVC/categorieProduit/modify-categorieProduit
	@PutMapping("/modify-categorieProduit")
	@ResponseBody
	public CategorieProduitDTO modifyCategorieProduit(@RequestBody CategorieProduitDTO categorieProduitDTO) {
		return categorieProduitService.updateCategorieProduit(categorieProduitDTO);
	}

	
}