package io.badri.app.rest;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userlogin")
public class FacebookController {

	private ConnectionRepository connectionRepository;

	public FacebookController(ConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}

	@GetMapping("/facebook")
	public String helloFacebook(Model model) {
		
		Connection<Facebook> findPrimaryConnection = connectionRepository.findPrimaryConnection(Facebook.class);
		if (findPrimaryConnection == null) {
			return "redirect:/connect/facebook";
		}
		return "facebookConnect";
		/*
		Facebook facebook = findPrimaryConnection.getApi();
		model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
		PagedList<Post> feed = facebook.feedOperations().getFeed();//fetch posts
		model.addAttribute("feed", feed);
		return "facebookConnected";
		*/
	}

	
	
//	@RequestMapping(value = "feed", method = RequestMethod.GET)
//	public String feed(Model model) {
//		Connection<Facebook> findPrimaryConnection = connectionRepository.findPrimaryConnection(Facebook.class);
//
//		if (findPrimaryConnection == null) {
//			return "redirect:/connect/facebook";
//		}
//
//		Facebook facebook = findPrimaryConnection.getApi();
////		User userProfile = facebook.userOperations().getUserProfile(); //  (#12) bio field is deprecated for versions v2.8 and higher
//		String[] fields = { "id", "email", "first_name", "last_name" };
//		User userProfile = facebook.fetchObject("me", User.class, fields);
//		model.addAttribute("userProfile", userProfile);
//		PagedList<Post> userFeed = facebook.feedOperations().getFeed();
//		model.addAttribute("userFeed", userFeed);
//		return "feed";
//	}
}