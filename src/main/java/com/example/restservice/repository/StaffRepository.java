package com.example.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restservice.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, String> {}
