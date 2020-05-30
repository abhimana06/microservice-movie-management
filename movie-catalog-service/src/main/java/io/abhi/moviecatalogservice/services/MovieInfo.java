package io.abhi.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.abhi.moviecatalogservice.models.CatalogItem;
import io.abhi.moviecatalogservice.models.Movie;
import io.abhi.moviecatalogservice.models.Rating;

@Service
public class MovieInfo {
	
	@Autowired
	private RestTemplate resttemplate ;

	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem", 
					threadPoolKey = "movieInfoPool", //BULKEND Pattern
					threadPoolProperties = {
							@HystrixProperty(name="coreSize" , value ="20"),
							@HystrixProperty(name="maxQueueSize" , value ="10")
					},
				commandProperties = {
						@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value= "2000"),
						@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value ="5"),
						@HystrixProperty(name="circuitBreaker.errorThresholdPercentage" , value= "50"),
						@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="4000")
				})
	public CatalogItem getCatalogItem(Rating rating) {
		if(rating.getRating()!=0) {
			Movie movie = resttemplate.getForObject("http://movie-info-service/movies/"+ rating.getMovieId(), Movie.class);	
			return new CatalogItem(movie.getName(), movie.getOverView(), rating.getRating());
		}
		else {
			Movie movie = resttemplate.getForObject("http://movie-info-service/movies/"+ "420817", Movie.class);	
			return new CatalogItem(movie.getName(), movie.getOverView(), rating.getRating());
		} 
	}
	
	public CatalogItem getFallbackCatalogItem(Rating rating) {
		Movie movie = new Movie();
		movie.setMovieId("0");
		movie.setName("Not available for the moment");
		movie.setOverView("Please wait while we trying to bring the service back. Your pateince is much appreciated");
		return new CatalogItem(movie.getName(), movie.getOverView(), rating.getRating());
	}
}
