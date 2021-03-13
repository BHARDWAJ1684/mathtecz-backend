package com.loginservices.controller;

import java.security.Principal;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loginservices.model.Student;
import com.loginservices.repository.CustomerRepository;
import com.loginservices.repository.StudentRepository;



@RestController
public class LoginController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping("/studentlogin")
	public Student getUserDetailsAfterLogin(Principal user) {
		List<Student> students = studentRepository.findByEmail(user.getName());
		if (students.size() > 0) {
			return students.get(0);
		}else {
			return null;
		}
		
	}
	
	@RequestMapping("/createstudent")
	public void saveUserDetailsAfterSignUpLogin(@RequestBody Student student) {
		student.setPwd(passwordEncoder.encode("puneet123"));
		studentRepository.save(student);
	}
	
	@RequestMapping("/deletestudent")
	public void deleteStudent(@RequestBody Student student) {
		System.out.println("student id:: "+student.getId());
		studentRepository.deleteById(student.getId());
	}
	
	@RequestMapping("/deletestudentbyid/{id}")
	public void deleteStudentById(@PathVariable Integer id) {
		System.out.println("student id:: "+id);
		studentRepository.deleteById(id);
	}

}
