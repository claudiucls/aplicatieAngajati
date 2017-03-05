package com.cls.controllers;

import java.sql.Date;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cls.entities.Angajat;
import com.cls.repository.AngajatRepository;
import com.cls.utility.LocalDateAttributeConverter;

@Controller
@RequestMapping(value = "/angajati")
public class AngajatController {

	@Autowired
	private AngajatRepository ar;

	@RequestMapping("/add")
	public String formAngajat(Model model) {
		model.addAttribute("angajat", new Angajat());
		return "users/formular_angajati";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String newAngajat(@Valid Angajat angajat, BindingResult br) {
		if (br.hasErrors()) {
			return "users/formular_angajati";
		}
		angajat.setDataNasterii(getDob(angajat));
		ar.save(angajat);
		return "redirect:/angajati/list";
	}
	

	private Date getDob(Angajat angajat) {
		String datee = angajat.getCnp().substring(1, 7);
		int zi=Integer.parseInt(datee.substring(4));
		int luna = Integer.parseInt(datee.substring(2,4));
		int an = Integer.parseInt("19"+datee.substring(0,2));
		LocalDate datan=LocalDate.of(an,luna,zi);
		return new LocalDateAttributeConverter().convertToDatabaseColumn(datan);
	}

	@RequestMapping("/list")
	public String indexPage(Model model, @RequestParam(value="cauta",defaultValue="") String cauta,
			@RequestParam(value = "page", defaultValue = "0") int page) {
		Page<Angajat> angajati = ar.findByNumeOrPrenume(cauta,(new PageRequest(page, 10)));
		model.addAttribute("angajati", angajati);
		model.addAttribute("paginaCurenta", page);
		int[] pages = new int[angajati.getTotalPages()];
		for (int i = 0; i < pages.length; i++)
			pages[i] = i;
		model.addAttribute("pages", pages);
		return "users/angajati";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("angajat", ar.findOne(id));
		return "users/formular_angajati";
	}

	@RequestMapping("delete/{id}")
	public String delete(@PathVariable Long id) {
		ar.delete(id);
		return "redirect:/angajati/list";
	}
}
