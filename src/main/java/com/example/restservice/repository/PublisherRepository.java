package com.example.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restservice.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {}
