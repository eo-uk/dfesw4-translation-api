package com.qa.dfesw4translationapi.main.entities;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WordPairRepository extends JpaRepository<WordPair, Integer> {
	List<WordPair> findWordPairByLanguage1Word(String word);
	List<WordPair> findWordPairByLanguage2Word(String word);
	List<WordPair> findWordPairByLanguage1(String language);
	List<WordPair> findWordPairByLanguage2(String language);
	List<WordPair> findWordPairByField(String field);
}
