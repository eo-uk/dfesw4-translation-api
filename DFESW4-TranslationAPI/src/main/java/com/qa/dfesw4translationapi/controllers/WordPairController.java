package com.qa.dfesw4translationapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.dfesw4translationapi.entities.WordPair;
import com.qa.dfesw4translationapi.services.WordPairService;


@RestController
@RequestMapping("/api")
public class WordPairController {
	
	@Autowired
	private WordPairService service;
	
	@GetMapping("/words")
	public List<WordPair> getAllWords() {
		return service.getAllWords();
	}
	
	@GetMapping("/words/field/{field}")
	public List<WordPair> getWordsByField (@PathVariable String field){
		return service.getWordsByField(field);
	}
	
	@GetMapping("/words/id/{id}")
	public WordPair getWordById(@PathVariable int id) {
		return service.getWordById(id);
	}
	
	@GetMapping("/words/word/{word}")
	public List<WordPair> getWordsByWord(@PathVariable String word) {
		return service.getWordsByWord(word);
	}
	
	@GetMapping("/words/language/{language}")
	public List<WordPair> getWordsByLanguage(@PathVariable String language) {
		return service.getWordsByLanguage(language);
	}
	
}
