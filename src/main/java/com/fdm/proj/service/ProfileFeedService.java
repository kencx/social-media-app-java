package com.fdm.proj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.proj.dal.PostDAO;
import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.model.Post;
import com.fdm.proj.model.User;

@Service
public class ProfileFeedService extends FeedService {
	
	private int userId;
	
	@Autowired
	public ProfileFeedService(UserDAO userDAO, PostDAO postDAO) {
		super(userDAO, postDAO);
	}

	@Override
	public List<Post> returnFeedPosts() {
		
		User currentUser = returnUser(userId);
		List<Post> userPosts = currentUser.getCreatedPosts();
		return userPosts;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	public void updateUserDetails(String firstname, String lastname, String location, String occupation, String password, String confirmPassword) {

		User currentUser = returnUser(userId);
		
		// TODO simplify this
		if (firstname != null && firstname != "") {
			currentUser.setFirstname(firstname);			
		}
		
		if (lastname != null && lastname != "") {
			currentUser.setLastname(lastname);
		}

		if (location != null && location != "") {
			currentUser.setLocation(location);
		}
		
		if (occupation != null && occupation != "") {
			currentUser.setOccupation(occupation);
		}
		
		// TODO ensure password meets registration requirements
		if (password != null && confirmPassword != null && password != "") {
			if (password.equals(confirmPassword)) {
				currentUser.setPassword(password);
			}
		}
		
		userDAO.updateUser(userId, currentUser);
	}
}