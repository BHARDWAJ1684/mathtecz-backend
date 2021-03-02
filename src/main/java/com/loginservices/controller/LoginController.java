package com.loginservices.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loginservices.model.Customer;
import com.loginservices.model.Student;
import com.loginservices.repository.CustomerRepository;
import com.loginservices.repository.StudentRepository;



@RestController
public class LoginController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
//	@RequestMapping("/user")
//	public Customer getUserDetailsAfterLogin(Principal user) {
//		List<Customer> customers = customerRepository.findByEmail(user.getName());
//		if (customers.size() > 0) {
//			return customers.get(0);
//		}else {
//			return null;
//		}
//		
//	}
	
	@RequestMapping("/student")
	public Student getUserDetailsAfterLogin(Principal user) {
		List<Student> students = studentRepository.findByEmail(user.getName());
		if (students.size() > 0) {
			return students.get(0);
		}else {
			return null;
		}
		
	}

}
