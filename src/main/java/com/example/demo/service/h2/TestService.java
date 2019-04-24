package com.example.demo.service.h2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.h2.Test;
import com.example.demo.repositories.h2.H2TestRepository;


@Service
public class TestService {

	@Autowired
	private H2TestRepository h2TestRepository;

	public List<Test> findAll() {
		return h2TestRepository.findAll();
	}
}
