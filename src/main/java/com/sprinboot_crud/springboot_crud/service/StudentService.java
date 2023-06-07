package com.sprinboot_crud.springboot_crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.sprinboot_crud.springboot_crud.model.Student;
import com.sprinboot_crud.springboot_crud.repository.StudentRepository;

public class StudentService {
	  @Autowired
	    private StudentRepository studentRepository;

	    public List<Student> getAllStudents() {
	        return studentRepository.findAll();
	    }

	    public Optional<Student> getStudentById(Long id) {
	        return studentRepository.findById(id);
	    }

	    public Student createStudent(Student student) {
	        return studentRepository.save(student);
	    }

	    public Optional<Student> updateStudent(Long id, Student updatedStudent) {
	        Optional<Student> existingStudent = studentRepository.findById(id);
	        if (existingStudent.isPresent()) {
	            updatedStudent.setId(existingStudent.get().getId());
	            return Optional.of(studentRepository.save(updatedStudent));
	        }
	        return Optional.empty();
	    }

	    public boolean deleteStudent(Long id) {
	        Optional<Student> existingStudent = studentRepository.findById(id);
	        if (existingStudent.isPresent()) {
	            studentRepository.delete(existingStudent.get());
	            return true;
	        }
	        return false;
	    }
}
