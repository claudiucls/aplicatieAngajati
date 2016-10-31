package com.cls.controllers;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

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

import com.cls.entities.Calcul;
import com.cls.repository.AngajatRepository;
import com.cls.repository.CalculRepository;
import com.cls.utility.MounthOfYear;

@Controller
@RequestMapping(value = "/calc")
public class CalculController {

	@Autowired
	private CalculRepository cr;
	
	@Autowired
	private AngajatRepository ar;

	@RequestMapping("/add")
	public String formCalc(Model model) {
		model.addAttribute("calcul", new Calcul());
		model.addAttribute("angajati", ar.findAll());
		return "users/formular_calcul";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String newProdus(@Valid Calcul calcul, BindingResult br) {
		if (br.hasErrors()) {
			return "users/formular_calcul";
		}
		cr.save(calcul);
		return "redirect:/calc/list";
	}
	
	@RequestMapping(value = "/trans")
	public String newProdus(Model model) {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		String lunaCurenta = MounthOfYear.getMountYear(date);
		String lunaPrecedenta = MounthOfYear.getMountYearPrecedent(date);
		List<Calcul> calcule = cr.findByLunaCurenta(lunaCurenta);
		List<Calcul> calculePrec = cr.findByLunaCurenta(lunaPrecedenta);
		if(calcule.isEmpty()){
			for (Calcul calcul : calculePrec) {
				Calcul calculN = new Calcul();
				calculN.setAngajat(calcul.getAngajat());
				calculN.setPersoaneIntretinere(calcul.getPersoaneIntretinere());
				calculN.setSalariuBrut(calcul.getSalariuBrut());
				calculN.setZileLucrate(calcul.getZileLucrate());
				calculN.setLunaCurenta(lunaCurenta);
				cr.save(calculN);
			}
			model.addAttribute("mes", "S-a importat totul pentru luna Curenta!");
		}
		model.addAttribute("mes","Nimic de importat!");
		return "redirect:/calc/list";
	}
	

	@RequestMapping("/list")
	public String indexPage(Model model, @RequestParam(value="cauta",defaultValue="") String cauta,
			@RequestParam(value = "page", defaultValue = "0") int page) {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		if(cauta.equals(""))
			cauta = MounthOfYear.getMountYear(date);
		String lunaPrecedenta = MounthOfYear.getMountYearPrecedent(date);
		Page<Calcul> calcule = cr.listAllByDate(cauta, new PageRequest(page, 8));
		if(!calcule.hasContent()) calcule=cr.listAllByDate(lunaPrecedenta,new PageRequest(page, 8));
		double totalSalarii = 0;
		short count = 0;
		int totalTM =0;
		StringBuilder lunaActiva = null;
		for (Calcul calcul : calcule) {
			lunaActiva=new StringBuilder(calcul.getLunaCurenta());
		}
		String luna=lunaActiva.toString();
		
		if(calcule.hasContent()){
			for (Calcul calcul : cr.findByLunaCurenta(cauta)) {
				totalSalarii +=calcul.getSalariuBrut();
				count++;
				totalTM +=calcul.getZileLucrate()*9.41;
			}
		} else {
			calcule = cr.listAllByDate(lunaPrecedenta, new PageRequest(page, 8));
			for (Calcul calcul : cr.findByLunaCurenta(lunaPrecedenta)) {
				totalSalarii +=calcul.getSalariuBrut();
				count++;
				totalTM +=calcul.getZileLucrate()*9.41;
			}
		}
		
		model.addAttribute("luna", luna);
		model.addAttribute("totalTM",totalTM);
		model.addAttribute("totalSalarii",totalSalarii);
		model.addAttribute("count",count);
		model.addAttribute("calculeAll",cr.listAllUnique());
		model.addAttribute("calcule", calcule);
		model.addAttribute("paginaCurenta", page);
		int[] pages = new int[calcule.getTotalPages()];
		for (int i = 0; i < pages.length; i++)
			pages[i] = i;
		model.addAttribute("pages", pages);
		return "users/calcule";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("calcul", cr.findOne(id));
		model.addAttribute("angajati",ar.findOne(cr.findOne(id).getAngajat().getId()));
		return "users/formular_calcul";
	}

	@RequestMapping("delete/{id}")
	public String delete(@PathVariable Long id) {
		cr.findOne(id).setAngajat(null);
		cr.delete(id);
		return "redirect:/calc/list";
	}
}
