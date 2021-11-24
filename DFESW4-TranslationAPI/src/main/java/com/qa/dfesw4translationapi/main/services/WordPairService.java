package com.qa.dfesw4translationapi.main.services;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.qa.dfesw4translationapi.main.entities.WordPair;
import com.qa.dfesw4translationapi.main.entities.WordPairRepository;
import com.qa.dfesw4translationapi.main.exceptions.WordPairNotFoundException;


@Service
public class WordPairService {
	
	@Autowired
	WordPairRepository repo;
	
	public WordPair getWordById(int id) {
		return this.repo.findById(id)
				.orElseThrow(WordPairNotFoundException::new);
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
				.orElseThrow(WordPairNotFoundException::new);
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
	
	public List<WordPair> searchWords(
			String word,
			@Nullable String sourceLang,
			@Nullable String targetLang,
			@Nullable String field,
			@Nullable String order
	) {
		List<WordPair> results = this.getWordsByWord(word);
		if (field != null) {
			results = results.stream()
				    .filter(pair -> pair.getField().equals(field))
				    .collect(Collectors.toList());
		}
		if (sourceLang != null) {
			results = results.stream()
				    .filter(pair -> pair.getLanguage1().equals(sourceLang)
				    		|| pair.getLanguage2().equals(sourceLang))
				    .collect(Collectors.toList());
		}
		if (targetLang != null) {
			results = results.stream()
				    .filter(pair -> pair.getLanguage1().equals(targetLang)
				    		|| pair.getLanguage2().equals(targetLang))
				    .collect(Collectors.toList());
		}
		if (order != null) {
			// TODO: Alphabetically (word), Alphabetically (sourceLang), Chronologically, 
		}
		return results;
	}
	
	public HashMap<String, String> translateText(
			String text,
			@Nullable String field
	) {
		String source = text;
		// If specific field is set, go through that field's word pairs first
		if (field != null) {
			for (WordPair pair : this.repo.findWordPairByField(field)) {
				//Using regex with word boundaries
				text = text.replaceAll("\\b"+pair.getLanguage1Word()+"\\b", pair.getLanguage2Word());
			}
		}
				
		// Replace general word pairs
		for (WordPair pair : this.repo.findAll()) {
			//Using regex with word boundaries
			text = text.replaceAll("\\b"+pair.getLanguage1Word()+"\\b", pair.getLanguage2Word());
		}
		
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("source", source);
		response.put("target", text);
	    return response;
	}
}
