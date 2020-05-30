package io.abhi.moviecatalogservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.abhi.moviecatalogservice.models.CatalogItem;
import io.abhi.moviecatalogservice.models.UserRating;
import io.abhi.moviecatalogservice.services.MovieInfo;
import io.abhi.moviecatalogservice.services.UserRatingsInfo;



@RestController
@RequestMapping("/catalog")
public class MovieCatalougeController {
	
	@Autowired
	private MovieInfo movieinfo ;
	
	@Autowired
	private UserRatingsInfo userRatingsInfo ;
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		UserRating ratings =   userRatingsInfo.getRatings( userId);
		
		return ratings.getUserRatings().stream().map(rating -> 
		movieinfo.getCatalogItem(rating)).collect(Collectors.toList());			
		
	}


}
