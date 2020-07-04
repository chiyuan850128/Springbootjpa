package com.william.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.william.demo.dao.AlienRepo;
import com.william.demo.model.Alien;

@RestController
public class AlienController {
	
	@Autowired
	AlienRepo repo;
	
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	@PostMapping(path="/alien")
	public Alien addAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
	
	@PutMapping(path="/alien", consumes = {"application/json"})
	public Alien saveUpdatedAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
	
	@GetMapping(path="/aliens", produces = {"application/json"})
	@ResponseBody
	public List<Alien> getAlien() {
		return repo.findAll();
	}
	
	@RequestMapping("/aliens/{aid}")
	public Optional<Alien> getAlien(@PathVariable("aid") int aid) {
		return repo.findById(aid);
	}
	
	@DeleteMapping("/alien/{aid}")
	public String deleteAlien(@PathVariable int aid) {
		Alien a = repo.getOne(aid);
		repo.delete(a);
		return a.toString() + "deleted";
	}
	/*
	@RequestMapping("/getAlien")
	public ModelAndView addAlien(@RequestParam int aid) {
		ModelAndView mv = new ModelAndView("showAlien.jsp");
		Alien alien = repo.findById(aid).orElse(new Alien());
		
		System.out.println(repo.findByTech("Java"));
		System.out.println(repo.findByAidGreaterThan(101));
		System.out.println(repo.findByTechSorted("Java"));
		mv.addObject(alien);
		return mv;
	}
	*/
}
