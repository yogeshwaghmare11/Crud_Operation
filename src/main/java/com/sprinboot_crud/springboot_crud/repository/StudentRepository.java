package com.sprinboot_crud.springboot_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprinboot_crud.springboot_crud.model.Student;


public interface StudentRepository extends JpaRepository<Student, Long> {

}
