package com.example.demo.service.h2;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceTest {

	@Autowired
	private TestService testService;
	
	@Test
	public void testH2FindAll() {
		List<com.example.demo.entity.h2.Test> list = testService.findAll();
		System.err.println("List<com.example.demo.entity.h2.Test> list size = " + list.size());
	}
}
