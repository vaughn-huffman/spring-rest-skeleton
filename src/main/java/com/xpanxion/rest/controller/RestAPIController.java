package com.xpanxion.rest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xpanxion.rest.service.UserService;
import com.xpanxion.rest.dto.bean.UserBean;

/**
 * Controller for REST API
 * 
 * @author vhuffman
 * 
 */

@Controller
public class RestAPIController {
	//Service for Users activity
	private UserService userService;
	
	@RequestMapping(value = "/")
	public String getMain() {
		return "main";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody UserBean postUserRB(@RequestBody UserBean user) {
		return this.userService.addUser(user);
	}

	/**
	 * PUT action for updating a user
	 * 
	 * @param user to update. Via PathVariable and RequestBody
	 * @return JSON for the updated user
	 * 
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, produces="application/json")
	public @ResponseBody UserBean updateUserRB(@PathVariable("id") long userId, @RequestBody UserBean user) {
		return this.userService.updateUser(userId, user);
	}
	
	
	/**
	 * DELETE action for removing a user
	 * 
	 * @param user to delete. Via PathVariable
	 * @return JSON for the deleted user
	 * 
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, produces="application/json")
	public @ResponseBody UserBean deleteUser(@PathVariable("id") long userId) {
		return this.userService.deleteUser(userId);
	}
	
	/**
	 * GET action for returning all users
	 * 
	 * @return JSON for all users in db
	 * 
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<UserBean> getUsers() {
        return this.userService.getUsers();
	}
	
	/**
	 * GET action for returning a single user via id path variable
	 * 
	 * @param id for the user to return. This is via a path variable
	 * @return JSON for a single user
	 * 
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody UserBean getUser(@PathVariable("id") long userId, Model model) {
		UserBean user = this.userService.getUser(userId);
        if (user != null) {
        	model.addAttribute("user", user);
        	return user;
        }
        else {
        	return new UserBean();
        }
	}

	
	/**
	 * GET action for a single user via id request parameter
	 * 
	 * @return JSON for a single user
	 * 
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody UserBean getUserid(@RequestParam("id") int userId) {
        return this.userService.getUser(userId);
	}
	
	
    /**
     * Sets the service for this controller
     * 
     * @param service the service to use in this controller. 
     */
    @Resource
    public void setUserService(UserService service) {
        this.userService = service;
    }
}