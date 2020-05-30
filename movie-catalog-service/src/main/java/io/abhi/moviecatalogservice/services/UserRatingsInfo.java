package io.abhi.moviecatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.abhi.moviecatalogservice.models.Rating;
import io.abhi.moviecatalogservice.models.UserRating;

@Service
public class UserRatingsInfo {
	
	@Autowired
	private RestTemplate resttemplate ;
	
	@HystrixCommand(fallbackMethod = "getFallbackRating",
	commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value= "2000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value ="5"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage" , value= "50"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="4000")
	})
	public UserRating getRatings(@PathVariable("userId") String userId) {
		return resttemplate.getForObject("http://ratings-data-service/ratingsData/users/" + userId, UserRating.class);
	}
	
	public UserRating getFallbackRating(@PathVariable("userId") String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setUserRatings(Arrays.asList(new Rating("", 0)));
		return userRating;
		
	}

}
