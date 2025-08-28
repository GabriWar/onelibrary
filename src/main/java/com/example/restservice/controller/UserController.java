package com.example.restservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.restservice.model.Student;
import com.example.restservice.model.Professor;
import com.example.restservice.model.Staff;
import com.example.restservice.repository.StudentRepository;
import com.example.restservice.repository.ProfessorRepository;
import com.example.restservice.repository.StaffRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final StudentRepository studentRepo;
    private final ProfessorRepository professorRepo;
    private final StaffRepository staffRepo;

    public UserController(StudentRepository studentRepo, ProfessorRepository professorRepo, StaffRepository staffRepo) {
        this.studentRepo = studentRepo;
        this.professorRepo = professorRepo;
        this.staffRepo = staffRepo;
    }

    @GetMapping("/students")
    public List<Student> listStudents() { return studentRepo.findAll(); }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student s) {
        return ResponseEntity.ok(studentRepo.save(s));
    }

    @GetMapping("/professors")
    public List<Professor> listProfessors() { return professorRepo.findAll(); }

    @PostMapping("/professors")
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor p) {
        return ResponseEntity.ok(professorRepo.save(p));
    }

    @GetMapping("/staff")
    public List<Staff> listStaff() { return staffRepo.findAll(); }

    @PostMapping("/staff")
    public ResponseEntity<Staff> createStaff(@RequestBody Staff s) {
        return ResponseEntity.ok(staffRepo.save(s));
    }
}
