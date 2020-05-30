package io.abhi.ratingsdataservice.controller;

import java.util.Arrays;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.abhi.ratingsdataservice.models.Rating;
import io.abhi.ratingsdataservice.models.UserRating;



@RestController
@RequestMapping("/ratingsData")
public class RatingController {

	@GetMapping("/users/{id}")
	public ResponseEntity<?> getRating(@PathVariable("id") String id) {
		UserRating userRating = new UserRating();
		userRating.setUserId(id);
		userRating.setUserRatings(Arrays.asList(new Rating("299536", 3), new Rating("545609", 4)));  
		return ResponseEntity.ok(userRating);
	}
}
