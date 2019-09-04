package edu.eci.arsw.blueprints.filters;


import java.util.List;

import org.springframework.stereotype.Component;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;


@Component("subsamplingFiltering")
public class SubsamplingFiltering implements Filter {

	@Override
	public Blueprint filter(Blueprint setBlue) {

		List<Point> points = setBlue.getPoints();
		Point[] ptsNuevos = new Point[points.size()/2];
		int j = 0;
		for (int i = 1 ; i< points.size(); i+=2){
			//System.out.println(i);
			ptsNuevos[j]= points.get(i);
			j++;
		}
		
		return new Blueprint(setBlue.getAuthor(),setBlue.getName(),ptsNuevos);
	}




}