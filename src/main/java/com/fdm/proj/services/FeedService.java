package com.fdm.proj.services;

import java.util.List;

import com.fdm.proj.dal.CommentDAO;
import com.fdm.proj.dal.PostDAO;
import com.fdm.proj.dal.UserDAO;
import com.fdm.proj.entities.Comment;
import com.fdm.proj.entities.Post;
import com.fdm.proj.entities.User;


public abstract class FeedService {
	
	protected UserDAO userDAO;
	protected PostDAO postDAO;

	public FeedService(UserDAO userDAO, PostDAO postDAO) {
		this.postDAO = postDAO;
		this.userDAO = userDAO; 
	}

	public abstract List<Post> returnFeedPosts();
	
	public User returnUser(int userId) {
		User user = userDAO.findById(userId);
		return user;
	}
	
	public void userCreatePost(User user, String postBody) {
		Post post = new Post(postBody);
		user.createPost(post);
		userDAO.updateUser(user.getUserId(), user);
	}
	
	public void userCreateComment(User user, Post post, String commentBody) {
		Comment comment = new Comment(commentBody);
		user.createComment(post, comment);
		userDAO.updateUser(user.getUserId(), user);
	}
	
	public Post returnPost(int postID) {
		Post post = postDAO.findById(postID);
		return post;
	}
	
	public List<Comment> returnAllPostComments(Post post) {
		List<Comment> comments = post.getComments();
		return comments;
	}

	public void likePost(User user, Post likedPost) {
		user.likePost(likedPost);
		postDAO.updatePost(likedPost.getPostId(), likedPost);
	}
	
}
