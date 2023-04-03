package com.konveyor.forum.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.konveyor.forum.demo.model.*;

@Service
public class ForumService {

	@Autowired
	ForumRepository forum;

	@Cacheable(value = "post")
	public List<Forum> getAllForumPosts() {
		return (List<Forum>) forum.findAll();
	}

	@Cacheable(value = "post")
	public List<Forum> getAllForumPostsByUser(String name) {
		return forum.findByName(name);
	}

	@CacheEvict(value = "post", allEntries = true)
	public Forum addForumPost(Forum forumPost) {

		forumPost.setTimestamp(new Date(System.currentTimeMillis()));

		System.out.println("Forum Post:" + forumPost);

		return forum.save(forumPost);
	}

	@Cacheable(value = "post")
	public Optional<Forum> getAllForumPostsByid(Long id) {
		return forum.findById(id);
	}
}