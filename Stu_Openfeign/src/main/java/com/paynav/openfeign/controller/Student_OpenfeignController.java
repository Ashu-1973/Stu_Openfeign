package com.paynav.openfeign.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.paynav.openfeign.model.Student;
import com.paynav.openfeign.service.ProxyInterface;

@RestController
@EnableHystrix
public class Student_OpenfeignController {
	
	@Autowired
	private ProxyInterface proxy;
	
	@GetMapping("/feignstudents")
	public List<Student> getAllStudents(){
		
		return proxy.getAllStudents();
	}
	
	@GetMapping("/feignstudent/{id}")
	@HystrixCommand(fallbackMethod="defaultStudent", commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000")})
	public Student findStudentyId(@PathVariable("id") int id){
		return proxy.findStudentById(id);
	}
	
	public Student defaultStudent(int id) {
		Student student = new Student();
		student.setStu_id(0);
		student.setSu_name("Unavailable");
		return student; 
		
	}
	
	
	
	@DeleteMapping("/feignDeleteStudent/{id}")
	public List<Student> deleteStudentById(@PathVariable("id") int id){
		return proxy.deleteStudentById(id);
	}
	
	@PostMapping("/feignAddStudent")
	public Student addStudent(@RequestBody Student student) {
		return proxy.addStudent(student);
	}
	
	@PatchMapping("/feignstudent/{id}")
	public Student updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
		return proxy.updateStudent(id, student);
	}
}