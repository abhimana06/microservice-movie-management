package io.abhi.movieinfoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.abhi.movieinfoservice.models.Movie;
import io.abhi.movieinfoservice.models.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{movieId}")
    @HystrixCommand(fallbackMethod = "getFallbackMovieInfo", 
	threadPoolKey = "movieInfoPool", //BULKEND Pattern
	threadPoolProperties = {
			@HystrixProperty(name="coreSize" , value ="20"),
			@HystrixProperty(name="maxQueueSize" , value ="10")
	},commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value= "2000")
	})
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieSummary.class);
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());

    }
    
    
    public Movie getFallbackMovieInfo(@PathVariable("movieId") String movieId) {
        return new Movie(movieId, "themoviedb", "the movie db service is down");

    }

}