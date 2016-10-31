package com.cls.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cls.entities.Role;
import com.cls.repository.RoleRepository;


@Controller
@RequestMapping(value = "/roles")
@Secured(value={"ROLE_ADMIN"})
public class RoleController {

	@Autowired
	private RoleRepository rr;

	@RequestMapping("/add")
	public String formAngajat(Model model) {
		model.addAttribute("role", new Role());
		return "admin/formular_roles";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String newProdus(@Valid Role role, BindingResult br) {
		if (br.hasErrors()) {
			return "admin/formular_roles";
		}
		rr.save(role);
		return "redirect:/roles/list";
	}

	@RequestMapping("/list")
	public String indexPage(Model model) {
		List<Role> roles = rr.findAll();
		model.addAttribute("roles", roles);
		return "admin/roles";
	}

	@RequestMapping("edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		model.addAttribute("role", rr.findOne(id));
		return "admin/formular_roles";
	}

	@RequestMapping("delete/{id}")
	public String delete(@PathVariable int id) {
		rr.delete(id);
		return "redirect:/roles/list";
	}
}
