package com.example.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restservice.model.Student;

public interface StudentRepository extends JpaRepository<Student, String> {}
