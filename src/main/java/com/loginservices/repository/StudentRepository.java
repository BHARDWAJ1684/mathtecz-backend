package com.loginservices.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.loginservices.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

	List<Student> findByEmail(String email);
	
	//Student save(Student student);
	
}
