package com.cls.controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cls.entities.Role;
import com.cls.entities.User;
import com.cls.repository.RoleRepository;
import com.cls.repository.UserRepository;

@Controller
@RequestMapping(value = "/users")
@Secured(value={"ROLE_ADMIN"})
public class UserController {

	@Autowired
	private UserRepository ur;
	
	@Autowired
	private RoleRepository rr;

	@RequestMapping("/add")
	public String formAngajat(Model model) {
 		model.addAttribute("user", new User());
		model.addAttribute("roles", rr.findAll());
		return "admin/formular_users";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String newProdus(@Valid User user, BindingResult br,@RequestParam String isAdmin)  {
		if (br.hasErrors()) {
			return "admin/formular_users";
		}
		Collection<Role> roles = new ArrayList<>();
		roles.add(rr.findByRoleName("USER"));
		if(isAdmin.equalsIgnoreCase("admin")){
			roles.add(rr.findByRoleName("ADMIN"));
		}
		user.setRoles(roles);
		ur.save(user);
		return "redirect:/users/list";
	}

	@RequestMapping("/list")
	public String indexPage(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
		Page<User> users = ur.findAll(new PageRequest(page, 8));
		model.addAttribute("users", users);
		model.addAttribute("paginaCurenta", page);
		int[] pages = new int[users.getTotalPages()];
		for (int i = 0; i < pages.length; i++)
			pages[i] = i;
		model.addAttribute("pages", pages);
		return "admin/users";
	}

	@RequestMapping("edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("user", ur.findOne(id));
		model.addAttribute("roles", ur.findOne(id).getRoles());
		return "admin/formular_users";
	}

	@RequestMapping("delete/{id}")
	public String delete(@PathVariable Long id) {
		ur.delete(id);
		return "redirect:/users/list";
	}
}
