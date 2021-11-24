package com.qa.dfesw4translationapi.main.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qa.dfesw4translationapi.main.entities.WordPair;
import com.qa.dfesw4translationapi.main.services.WordPairService;


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
	
	@GetMapping("/words/search")
	public List<WordPair> getWordsBySearch(
			@RequestParam String word,
			@RequestParam(required=false) String sourceLang,
			@RequestParam(required=false) String targetLang,
			@RequestParam(required=false) String field,
			@RequestParam(required=false) String order
	) {
		return service.searchWords(word, sourceLang, targetLang, field, order);
	}
	
	@GetMapping("/text/translate")
	public HashMap<String, String> translateText(
			@RequestParam String text,
			@RequestParam String language1,
			@RequestParam String language2,
			@RequestParam(required = false) String field
	) {
		return service.translateText(text, language1, language2, field);
	}
	
	@PutMapping("/words/update/{id}")
	public HashMap<String, String> updateWord(
			@RequestBody WordPair newWordPair,
			@PathVariable int id
	) {
		service.updateWord(newWordPair, id);
		return getResponseSuccess();
	}
	
	@PostMapping("/words/add")
	public HashMap<String, String> addWord(@RequestBody WordPair word) {
		service.createWord(word);
		return getResponseSuccess();
	}
	
	@DeleteMapping("/words/delete/{id}")
	public HashMap<String, String> deleteWordById(@PathVariable int id) {
		service.deleteWordById(id);
		return getResponseSuccess();
	}
	
	@DeleteMapping("/words/delete/all")
	public HashMap<String, String> deleteAllWords() {
		service.deleteAllWords();
	    return getResponseSuccess();
	}
	
	private HashMap<String, String> getResponseSuccess() {
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("message", "success");
	    return response;
	}
}