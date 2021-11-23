package com.qa.dfesw4translationapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.dfesw4translationapi.services.WordPairService;


@RestController
@RequestMapping("/api")
public class WordPairController {
	
	@Autowired
	private WordPairService service;
	
}
