/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.filters.Filter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service("blueprintsServices")
public class BlueprintsServices {

    @Autowired
    @Qualifier("inMemoryBlueprintPersistence")
    BlueprintsPersistence bpp = null;

    //otro servicio = redundancyFiltering
    //otro servicio = subsamplingFiltering
    @Autowired
    @Qualifier("redundancyFiltering")
    Filter ft;

    Set<Blueprint> blueprints = new HashSet<>();

    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        
        bpp.saveBlueprint(bp);
        
        blueprints.add(bp);
    }
    
    public Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException {
        Set<Blueprint> allBlue = null;
        
        allBlue = bpp.getBlueprints();
        
        return allBlue;
        
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name   blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException    if there is no such blueprint
     * @throws BlueprintPersistenceException
     */
    public Blueprint getBlueprint(String author, String name)throws BlueprintNotFoundException  {
        Blueprint blue = null;
    
            blue  = ft.filter(bpp.getBlueprint(author,name));
        
        return blue;
        
        
        
       
         
    }
    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        Set<Blueprint> allBlue = null;
        
            allBlue = bpp.getBlueprintsByAuthor(author);
        
        return allBlue;
        
    }

   
    public void updatePoints(String author, String bprintname,Blueprint blue2) throws BlueprintNotFoundException{
        
        Blueprint blue =bpp.getBlueprint(author, bprintname);
        blue.setPoints(blue2.getPoints());
        
    }
    
}
