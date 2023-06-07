package com.sprinboot_crud.springboot_crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprinboot_crud.springboot_crud.model.Student;
import com.sprinboot_crud.springboot_crud.repository.StudentRepository;

 

@RestController
@RequestMapping("/api")
public class StudentController {
	 @Autowired
	    private StudentRepository studentRepository;

	    @GetMapping
	    public List<Student> getAllStudents() {
	        return studentRepository.findAll();
	    }

	    @GetMapping("/student/{id}")
	    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
	        Optional<Student> student = studentRepository.findById(id);
	        return student.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	    }

	    @PostMapping("/student")
	    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
	        Student createdStudent = studentRepository.save(student);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
	    }

	    @PutMapping("/student/{id}")
	    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
	        Optional<Student> existingStudent = studentRepository.findById(id);
	        if (existingStudent.isPresent()) {
	            student.setId(id);
	            Student updatedStudent = studentRepository.save(student);
	            return ResponseEntity.ok(updatedStudent);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @DeleteMapping("/student/{id}")
	    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
	        Optional<Student> student = studentRepository.findById(id);
	        if (student.isPresent()) {
	            studentRepository.delete(student.get());
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
}
