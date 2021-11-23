package com.qa.dfesw4translationapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.dfesw4translationapi.entities.WordPairRepository;


@Service
public class WordPairService {
	
	@Autowired
	WordPairRepository repo;
	
}
