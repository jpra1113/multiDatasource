package com.example.demo.repositories.h2;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.h2.Test;


public interface H2TestRepository extends JpaRepository<Test, Long> {

}