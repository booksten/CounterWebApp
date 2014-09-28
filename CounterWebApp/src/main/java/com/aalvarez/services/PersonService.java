package com.aalvarez.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aalvarez.dao.PersonDAO;
import com.aalvarez.domain.TSM_Person;
import com.aalvarez.model.Person;
import com.aalvarez.utils.Util;

@Controller
@RequestMapping("/Employees")
public class PersonService {
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<Person> getAllEmployees(HttpServletRequest request) throws Exception {
		Iterator<TSM_Person> i = PersonDAO.instance.getAllEmployees().iterator();
		List<Person> empList = new ArrayList<Person>();
		while (i.hasNext()) {
			TSM_Person tsmemp = i.next();
			Person emp = new Person();
			Util.setPersonData(emp, tsmemp);
			empList.add(emp);
		}
		return empList;
	}
}
