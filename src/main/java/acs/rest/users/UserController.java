package acs.rest.users;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import acs.logic.user.UserService;

@RestController
public class UserController {
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	

	// login valid user and retrieve user details
		@RequestMapping(path = "/acs/users/login/{userDomain}/{userEmail}", 
				method = RequestMethod.GET, 
				produces = MediaType.APPLICATION_JSON_VALUE)
		public UserBoundary login(@PathVariable("userDomain") String userDomain,
				@PathVariable("userEmail") String userEmail) {
			return this.userService.login(userDomain, userEmail);
		}

		// create new user to the data base
		@RequestMapping(path = "/acs/users", 
				method = RequestMethod.POST, 
				produces = MediaType.APPLICATION_JSON_VALUE, 
				consumes = MediaType.APPLICATION_JSON_VALUE)
		public UserBoundary createUser(@RequestBody UserNewDetails user) {
			Map<String, Object> userId = new HashMap<>();
			userId.put("domain", null);
			userId.put("email", user.getEmail());
			UserBoundary userBoundary = new UserBoundary(userId, user.getUserName(), user.getRole(), user.getAvatar());
			return this.userService.createUser(userBoundary);
		}

		// update user details
		@RequestMapping(path = "/acs/users/{userDomain}/{userEmail}",
				method = RequestMethod.PUT,
				consumes = MediaType.APPLICATION_JSON_VALUE)
		public void updateUser(@PathVariable("userDomain") String userDomain, @PathVariable("userEmail") String userEmail,
				@RequestBody UserBoundary update) {

			this.userService.updateUser(userDomain, userEmail, update);

		}

}
