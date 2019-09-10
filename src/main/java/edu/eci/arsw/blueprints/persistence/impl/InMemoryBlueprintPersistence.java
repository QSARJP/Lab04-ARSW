/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

/**
 *
 * @author hcadavid
 */
@Component("inMemoryBlueprintPersistence")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence {

    private final Map<Tuple<String, String>, Blueprint> blueprints = new HashMap<>();

    public InMemoryBlueprintPersistence() {
        // load stub data
        Point[] pts = new Point[] { new Point(140, 140), new Point(115, 115),new Point(140, 140), new Point(105, 90) };
        Blueprint bp = new Blueprint("_authorname_", "_bpname_", pts);
        blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
        Point[] pts0 = new Point[] { new Point(140, 140), new Point(115, 115) };
        Blueprint bp0 = new Blueprint("_authorname_", "_bpname2_", pts0);
        blueprints.put(new Tuple<>(bp0.getAuthor(), bp0.getName()), bp0);
        Point[] pts1 = new Point[] { new Point(140, 140), new Point(115, 115) };
        Blueprint bp1 = new Blueprint("_authorname_", "_bpname_3", pts1);
        blueprints.put(new Tuple<>(bp1.getAuthor(), bp1.getName()), bp1);
        Point[] pts2 = new Point[] { new Point(140, 140), new Point(115, 115) };
        Blueprint bp2 = new Blueprint("jack", "paintExample", pts2);
        blueprints.put(new Tuple<>(bp2.getAuthor(), bp2.getName()), bp2);
        Point[] pts3 = new Point[] { new Point(140, 140), new Point(115, 115) };
        Blueprint bp3 = new Blueprint("jack", "paintExample2", pts3);
        blueprints.put(new Tuple<>(bp3.getAuthor(), bp3.getName()), bp3);

    }

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(), bp.getName()))) {
            throw new BlueprintPersistenceException("The given blueprint already exists: " + bp);
        } else {
            blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
        }
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {

        if ( blueprints.get(new Tuple<>(author, bprintname)).equals(null) ){
            throw new BlueprintNotFoundException("No encontro el autor o la obra");
        }else{
            return blueprints.get(new Tuple<>(author, bprintname));
        }
        
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> blue = new HashSet<>();
        for (Entry<Tuple<String, String>, Blueprint> entry : blueprints.entrySet()) {

            if (entry.getKey().getElem1().equals( author)){
                blue.add(entry.getValue());

            }

        }
        if (blue.isEmpty()){
            throw new BlueprintNotFoundException("Error 404: No encontro el autor o la obra ");
        }else {
            return blue;
        }
        
    }

    @Override
    public  Set<Blueprint> getBlueprints() throws BlueprintNotFoundException {
        Set<Blueprint> blue = new HashSet<>();
        for (Entry<Tuple<String, String>, Blueprint> entry : blueprints.entrySet()) {

            blue.add(entry.getValue());

        }
        if (blue.isEmpty()){
            throw new BlueprintNotFoundException("Error 404: No encontro el autor o la obra");
        }else {
            return blue;
        }
        

    }

    @Override
    public  void updatePoints(String author, String bprintname,Point[] points) throws BlueprintNotFoundException {
        
        
        

    }




    
    
}
