package com.konveyor.forum.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.konveyor.forum.demo.model.Forum;
import com.konveyor.forum.demo.service.ForumService;

@Controller
public class ForumController {

	@Autowired
	ForumService forumService;
	
	@GetMapping("/getallforumposts")
	public String getAllForumPosts(Model model) {
		List<Forum> allPosts = forumService.getAllForumPosts();
		model.addAttribute("allPosts",allPosts);
		return "viewAllPosts";
	}
	
	@GetMapping("/getPostsbyUsername/{name}")
	public String getPostsbyUsername(Model model,@PathVariable String name) {
		List<Forum> allPosts = forumService.getAllForumPostsByUser(name);
		model.addAttribute("allPosts",allPosts);
		return "viewUserPosts";
	}
	
	
	@GetMapping("/getPosts/{id}")
	public String getPosts(Model model,@PathVariable Long id) {
				forumService.getAllForumPostsByid(id).ifPresent(allPosts-> model.addAttribute("allPosts", allPosts));
		
		return "viewPostByID";
	}
	

	@RequestMapping(path="/addforumpost", method= RequestMethod.POST)
    public String addForumPost(Forum forum){
         forumService.addForumPost(forum);
         return "post_success";
    }
	
	@GetMapping("/addpost")
	public String showRegistrationForm(Model model) {
		model.addAttribute("forum", new Forum());
		
		return "addForumPost";
	}
	
	  @GetMapping("/") 
	  public String showHomePage(Model model) {
	  model.addAttribute("forum", new Forum());
	  
	  return "index"; }
	  
	  @GetMapping("/index") 
	  public String showIndexPage(Model model) {
	  model.addAttribute("forum", new Forum());
	  
	  return "index"; }
	 
	
	
}
