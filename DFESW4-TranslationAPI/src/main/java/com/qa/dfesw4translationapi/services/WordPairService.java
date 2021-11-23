package com.qa.dfesw4translationapi.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.dfesw4translationapi.entities.WordPair;
import com.qa.dfesw4translationapi.entities.WordPairRepository;


@Service
public class WordPairService {
	
	@Autowired
	WordPairRepository repo;
	
	public WordPair getWordById(int id) {
		return this.repo.findById(id)
				.orElseThrow(EntityNotFoundException::new);
	}
	
	public List<WordPair> getAllWords() {
			return repo.findAll();
	}
	
	public List<WordPair> getWordsByField(String field) {
			return repo.findWordPairByField(field);
	}
	
	public List<WordPair> getWordsByWord(String word) {
		List<WordPair> results = repo.findWordPairByLanguage1Word(word);
		results.addAll(repo.findWordPairByLanguage2Word(word));
		return results;
	}
	
	public List<WordPair> getWordsByLanguage(String language) {
		List<WordPair> results = repo.findWordPairByLanguage1(language);
		results.addAll(repo.findWordPairByLanguage2(language));
		return results;
	}
}
