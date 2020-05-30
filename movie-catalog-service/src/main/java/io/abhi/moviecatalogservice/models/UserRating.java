package io.abhi.moviecatalogservice.models;

import java.util.List;

public class UserRating {
	
	private String userId;

	private List<Rating> userRatings;
	
	public UserRating() {
		super();
		
	}

	public UserRating(String userId,List<Rating> userRatings) {
		super();
		this.userId = userId;
		this.userRatings = userRatings;
	}

	public List<Rating> getUserRatings() {
		return userRatings;
	}

	public void setUserRatings(List<Rating> userRatings) {
		this.userRatings = userRatings;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
