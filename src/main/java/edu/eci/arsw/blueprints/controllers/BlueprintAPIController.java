/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.logging.Level;
import java.util.logging.Logger;


import edu.eci.arsw.blueprints.services.BlueprintsServices;

import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {

    @Autowired
	@Qualifier("blueprintsServices")
	BlueprintsServices bps ;
    

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> manejadorGetRecursoAllBlueprints(){
		try {
			//obtener datos que se enviarán a través del API
			return new ResponseEntity<>( bps.getAllBlueprints(),HttpStatus.ACCEPTED);
		} catch (Exception ex) {
			Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
		}        
    }	
    
    @RequestMapping(path  = "/{author}",method = RequestMethod.GET)
	public ResponseEntity<?> manejadorGetRecursoAllBluePrintsByAuthor(@PathVariable("author") String author){

        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>( bps.getBlueprintsByAuthor(author),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.valueOf(403));
        }   
    }

    @RequestMapping(path  = "/{author}/{bpname}",method = RequestMethod.GET)
	public ResponseEntity<?> manejadorGetRecursoAllBluePrintsByAuthor(@PathVariable("author") String author,@PathVariable("bpname") String bpname){

        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>( bps.getBlueprint(author, bpname),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
        }   
    }
		     
	
}
    
    
    


