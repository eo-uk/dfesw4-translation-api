package com.qa.dfesw4translationapi.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PutMapping("/words/update/{id}")
	public HashMap<String, String> updateWord(
			@RequestBody WordPair newWordPair,
			@PathVariable int id
	) {
		service.updateWord(newWordPair, id);
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("message", "success");
		return response;
	}
	
	@PostMapping("/words/add")
	public HashMap<String, String> addWord(@RequestBody WordPair word) {
		service.createWord(word);
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("message", "success");
	    return response;
	}
	
	@DeleteMapping("/words/delete/{id}")
	void deleteWordById(@PathVariable int id) {
		service.deleteWordById(id);
	}
	
	@DeleteMapping("/words/delete/all")
	void deleteAllWords() {
		service.deleteAllWords();
	}
	
}
