package edu.eci.arsw.blueprints.filters;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Component("redundancyFiltering")
public class RedundancyFiltering implements Filter {

	@Override
	public Blueprint filter(Blueprint setBlue) {
        List<String> pointsIn = new ArrayList<>();
        Blueprint blue = null;
        List<Point> points = setBlue.getPoints();
        Point[] ptsNuevos = new Point[points.size()];
        int i = 0 ;
        for ( Point p : points){
            if (!(pointsIn.contains(p.getX()+" "+p.getY()))){
                pointsIn.add(p.getX()+ " "+p.getY());
                ptsNuevos[i] = new Point(p.getX(),p.getY());
                i++;
            } 
        }
        
        Point[] ptsNuevos2 = new Point[i];
        for (int j = 0 ; j < i ; j++){
            ptsNuevos2[j] = ptsNuevos[j];
        }
        
        blue = new Blueprint(setBlue.getAuthor(),setBlue.getName(),ptsNuevos2);
        /*System.out.println(blue.getPoints().size());
        System.out.println(blue.getPoints().get(0).getX() + " " + blue.getPoints().get(0).getY() );
        System.out.println(blue.getPoints().get(1).getX() + " " + blue.getPoints().get(1).getY() );
        System.out.println(blue.getPoints().get(2).getX() + " " + blue.getPoints().get(2).getY() );
        System.out.println(blue.getPoints().get(3).getX() + " " + blue.getPoints().get(3).getY() );
        */
    
        return blue;
	}

}