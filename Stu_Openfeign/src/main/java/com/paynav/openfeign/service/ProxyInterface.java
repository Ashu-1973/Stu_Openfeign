package com.paynav.openfeign.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.paynav.openfeign.model.Student;



@FeignClient(name="Student", url="http://localhost:8084")
public interface ProxyInterface {
	@GetMapping("/student")
	public List<Student> getAllStudents();
	
	@GetMapping("/student/{id}")
	public Student findStudentById(@PathVariable("id") int id);
	
	@DeleteMapping("/student/{id}")
	public List<Student> deleteStudentById(@PathVariable("id") int id);
	
	@PostMapping("/student")
	public Student addStudent(@RequestBody Student student);
	
	@PatchMapping("/student/{id}")
	public Student updateStudent(@PathVariable("id") int id, @RequestBody Student student);
}
