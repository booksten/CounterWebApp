package com.aalvarez.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aalvarez.dao.PersonDAO;
import com.aalvarez.dao.UserInfoDAO;
import com.aalvarez.domain.TSM_Person;
import com.aalvarez.domain.TSM_UserInfo;
import com.aalvarez.model.Person;
import com.aalvarez.utils.Util;


@Controller
public class BaseController {
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(ModelMap model, HttpServletRequest request) throws Exception {
		return new ModelAndView("login");
 
	}
	
	@RequestMapping(value = "/Users", method = RequestMethod.GET)
	public ModelAndView users(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAttribute("message", "Maven Web Project + Spring 3 MVC - welcome()");
		List<TSM_Person> tsmperson = PersonDAO.instance.getAllEmployees();
		List<Person> personlist = new ArrayList<Person>();
 		
		for(TSM_Person tsmper : tsmperson){
			Person per = new Person();
			Util.setPersonData(per, tsmper);
			personlist.add(per);
			System.out.println(per.getFirstName() + " " + per.getLastName());	
		}
		request.setAttribute("persons", personlist );
		//Spring uses InternalResourceViewResolver and return back index.jsp
		return new ModelAndView("Users");
	}
	
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public ModelAndView welcome(ModelMap model, HttpServletRequest request) throws Exception {
 
		model.addAttribute("message", "Maven Web Project + Spring 3 MVC - welcome()");
		
		List<TSM_Person> tsmperson = PersonDAO.instance.getAllEmployees();
		List<Person> personlist = new ArrayList<Person>();
 		
		for(TSM_Person tsmper : tsmperson){
			Person per = new Person();
			Util.setPersonData(per, tsmper);
			personlist.add(per);	
		}
		request.setAttribute("persons", personlist );
		//Spring uses InternalResourceViewResolver and return back index.jsp
		return new ModelAndView("index");
 
	}
 	
	@RequestMapping(value = "/getEmployees", method = RequestMethod.GET)
	public 
	@ResponseBody
	List<Person> getEmployees(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<TSM_Person> tsmperson = PersonDAO.instance.getAllEmployees();
		List<Person> personlist = new ArrayList<Person>();
 		
		for(TSM_Person tsmper : tsmperson){
			Person per = new Person();
			Util.setPersonData(per, tsmper);
			personlist.add(per);
		}	
		return personlist;
	}
	
	@RequestMapping(value = "/createEmployee", method = RequestMethod.POST)
	public 
	@ResponseBody
	Person createEmployee(HttpServletRequest request, HttpServletResponse response, Person person) throws Exception{
		TSM_Person tsmPerson = new TSM_Person();
		Util.setPersonData(tsmPerson, person);
		PersonDAO.instance.save(tsmPerson);
		
		TSM_UserInfo tsmUI = new TSM_UserInfo();
		tsmUI.setUserName(tsmPerson.getFirstName().toLowerCase()+"."+tsmPerson.getLastName().toLowerCase());
		tsmUI.setPassword(tsmPerson.getLastName().toLowerCase());
		tsmUI.setId(tsmPerson.getId());
		UserInfoDAO.instance.save(tsmUI);
		
		TSM_Person tsmPer = (TSM_Person) PersonDAO.instance.findById(tsmPerson.getId());
		Person per = new Person();
		Util.setPersonData(per, tsmPer);
		
		return per;
	}
	
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	public 
	@ResponseBody
	Person updateEmployee(HttpServletRequest request, HttpServletResponse response, Person person) throws Exception{	
		TSM_Person tsmPerson = (TSM_Person) PersonDAO.instance.findById(person.getId());
		tsmPerson.setFirstName(person.getFirstName());
		tsmPerson.setMiddleName(person.getMiddleName());
		tsmPerson.setLastName(person.getLastName());
		tsmPerson.setAddress(person.getAddress());
		tsmPerson.setCity(person.getCity());
		tsmPerson.setState(person.getState());
		tsmPerson.setZip(person.getZipCode());
		PersonDAO.instance.update(tsmPerson);
		
		TSM_Person tsmPer = (TSM_Person) PersonDAO.instance.findById(person.getId());
		Person per = new Person();
		Util.setPersonData(per, tsmPer);
		
		return per;
	}
	
	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.POST)
	public 
	@ResponseBody
	String deleteEmployee(HttpServletRequest request, HttpServletResponse response, Person person) throws Exception{	
		TSM_Person tsmPerson = (TSM_Person) PersonDAO.instance.findById(person.getId());
		TSM_UserInfo tsmUserInfo = (TSM_UserInfo) UserInfoDAO.instance.getUserInfoById(tsmPerson.getId());
		if(tsmUserInfo != null)
			UserInfoDAO.instance.delete(tsmUserInfo);
		
		PersonDAO.instance.delete(tsmPerson);
		
		return "Success";
	}
}
