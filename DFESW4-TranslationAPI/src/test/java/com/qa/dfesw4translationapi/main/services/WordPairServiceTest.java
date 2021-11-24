package com.qa.dfesw4translationapi.main.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qa.dfesw4translationapi.main.entities.WordPair;
import com.qa.dfesw4translationapi.main.entities.WordPairRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
public class WordPairServiceTest {
	
	@Autowired
	private WordPairService service;
	
	@MockBean
	private WordPairRepository repo; 
	
	@Test
	public void getAllWordsTest() {
		WordPair pair = new WordPair(1, "english", "tree", "spanish", "arbol", "general", "2021-11-24");
		List<WordPair> expected = new ArrayList<>();
		expected.add(pair);
		
		Mockito.when(repo.findAll()).thenReturn(expected);
		assertThat(expected).isEqualTo(service.getAllWords());
	 }
	
}



	
	