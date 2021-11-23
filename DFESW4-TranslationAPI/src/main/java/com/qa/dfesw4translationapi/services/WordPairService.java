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
	
	public boolean createWord(WordPair word) {
		this.repo.save(word);
		return true;
	}
	
	public boolean updateWord( WordPair newWordPair, int id) {
		this.repo.findById(id)
				.map(pair -> {
						pair.setLanguage1(newWordPair.getLanguage1());
						pair.setLanguage1Word(newWordPair.getLanguage1Word());
						pair.setLanguage2(newWordPair.getLanguage2());
						pair.setLanguage2Word(newWordPair.getLanguage2Word());
						pair.setField(newWordPair.getField());
						pair.setDateCreated(newWordPair.getDateCreated());
						return this.repo.save(pair);
					}
				)
				.orElseThrow(EntityNotFoundException::new);
		return true;
	}
	
	public boolean deleteWordById(int id) {
		this.repo.deleteById(id);
		return true;
	}
	
	public boolean deleteAllWords() {
		this.repo.deleteAll();
		return true;
	}
}
