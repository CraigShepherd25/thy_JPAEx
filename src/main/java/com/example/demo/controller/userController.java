package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.userRepository;
import com.example.demo.entity.user;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class userController {
	@Autowired
	userRepository ur;
	@Autowired
	HttpServletResponse response;
	
	@RequestMapping("/add")
	public void add(String name,String address) throws IOException
	{
		user u=new user(name,address);
		
		ur.save(u);
		
		
		response.sendRedirect("/success.html");
	}
	
	@RequestMapping("/queryAll")
	public ModelAndView queryAll(Model m)
	{
		List<user> l=ur.findAll();
		m.addAttribute("USER", l);
		ModelAndView modelandview=new ModelAndView("query");
		
		return modelandview;
	}
	
	@RequestMapping("/queryId")
	public user queryId(int id)
	{
		Optional<user> o=ur.findById(id);
		user u=null;
		if(o.isPresent())
		{
			u=o.get();
		}
		
		return u;
	}
	
	
	@RequestMapping("/update")
	public ModelAndView update(int id,String name,String address)
	{
		user u=queryId(id);
		u.setName(name);
		u.setAddress(address);
		
		ur.save(u);
		
		ModelAndView modelandview =new ModelAndView("index");
		return modelandview;
		
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(int id)
	{	
			ur.deleteById(id);
			
			ModelAndView modelandview = new ModelAndView("index");
			return modelandview;
		
		
	}
	

}