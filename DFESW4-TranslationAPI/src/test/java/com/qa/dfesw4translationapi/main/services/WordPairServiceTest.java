package com.qa.dfesw4translationapi.main.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
	
	@Test
	public void getWordsByIdTest() {
		WordPair expected = new WordPair(1, "english", "tree", "spanish", "arbol", "general", "2021-11-24");
		
		Mockito.when(repo.findById(1)).thenReturn(Optional.of(expected));
		assertThat(expected).isEqualTo(service.getWordById(1));
	}
	
	@Test
	public void getWordsByFieldTest() {
		WordPair pair = new WordPair(1, "english", "doctor", "spanish", "medico", "medical", "2021-11-24");
		List<WordPair> expected = new ArrayList<>();
		expected.add(pair);
		
		Mockito.when(repo.findWordPairByField("medical")).thenReturn(expected);
		assertThat(expected).isEqualTo(service.getWordsByField("medical"));
	}
	
	@Test
	public void getWordsByLanguageTest() {
		WordPair pair1 = new WordPair(1, "english", "doctor", "spanish", "medico", "medical", "2021-11-24");
		WordPair pair2 = new WordPair(2, "english", "nurse", "spanish", "enfermero", "medical", "2021-11-24");
		List<WordPair> expected = new ArrayList<>();
		expected.add(pair1);
		expected.add(pair2);
		
		Mockito.when(repo.findWordPairByLanguage1("english")).thenReturn(expected);
		assertThat(expected).isEqualTo(service.getWordsByLanguage("english"));
	}
	
	@Test
	public void getWordsByWordTest() {
		WordPair pair1 = new WordPair(1, "english", "doctor", "spanish", "medico", "medical", "2021-11-24");
		WordPair pair2 = new WordPair(2, "english", "doctor", "turkish", "doktor", "medical", "2021-11-24");
		List<WordPair> expected = new ArrayList<>();
		expected.add(pair1);
		expected.add(pair2);
		
		Mockito.when(repo.findWordPairByLanguage1Word("doctor")).thenReturn(expected);
		assertThat(expected).isEqualTo(service.getWordsByWord("doctor"));
	}
	
	@Test
	public void createWordTest() {
		WordPair pair = new WordPair(1, "english", "doctor", "turkish", "doktor", "medical", "2021-11-24");
		when(repo.save(pair)).thenReturn(pair);
		assertThat(service.createWord(pair)).isEqualTo(pair);
		verify(this.repo, Mockito.times(1)).save(pair);
	}
	
	@Test
	public void deleteWordByIdTest() {
		WordPair pair = new WordPair(1, "english", "doctor", "turkish", "doktor", "medical", "2021-11-24");
		repo.save(pair);
		doNothing().when(repo).deleteById(1);
		
		service.deleteWordById(1);
		assertThat(repo.count()).isEqualTo(0);
	}
	
	@Test
	public void deleteAllWordsTest() {
		WordPair pair = new WordPair(1, "english", "doctor", "turkish", "doktor", "medical", "2021-11-24");
		repo.save(pair);
		doNothing().when(repo).deleteAll();
		
		service.deleteAllWords();
		assertThat(repo.count()).isEqualTo(0);
	}
	
	@Test
	public void updateWordTest() {
		WordPair oldPair = new WordPair(1, "english", "doctor", "turkish", "doktor", "medical", "2021-11-24");
		WordPair newPair = new WordPair(1, "english", "doctor", "spanish", "medico", "medical", "2021-11-24");
		when(repo.findById(1)).thenReturn(Optional.of(oldPair));
		when(repo.save(oldPair)).thenReturn(oldPair);
		
		assertThat(service.updateWord(newPair, 1)).isEqualTo(oldPair);
	}
	
	@Test
	public void searchWordsTest() {
		WordPair pair1 = new WordPair(1, "english", "nurse", "spanish", "enfermero", "medical", "2021-11-24");
		WordPair pair2 = new WordPair(2, "english", "doctor", "spanish", "medico", "medical", "2021-11-24");
		List<WordPair> pairs = new ArrayList<>();
		pairs.add(pair1);
		pairs.add(pair2);
		when(service.getWordsByWord("doctor")).thenReturn(pairs);
		
		assertThat(service.searchWords("doctor", "english", "spanish", "medical")).isEqualTo(pairs);
	}
	
	@Test
	public void translateTextTest() {
		WordPair pair1 = new WordPair(1, "english", "nurse", "spanish", "enfermero", "medical", "2021-11-24");
		WordPair pair2 = new WordPair(2, "spanish", "medico", "english", "doctor", "medical", "2021-11-24");
		WordPair pair3 = new WordPair(1, "spanish", "manzana", "english", "apple", "general", "2021-11-24");
		WordPair pair4 = new WordPair(2, "spanish", "platano", "english", "banana", "general", "2021-11-24");
		List<WordPair> pairs1 = new ArrayList<>();
		pairs1.add(pair1);
		pairs1.add(pair2);
		pairs1.add(pair3);
		pairs1.add(pair4);

		String text = "hello doctor banana";
		String expected = "hello medico platano";
		
		when(service.getWordsByLanguage("english")).thenReturn(pairs1);
		assertThat(service.translateText(text, "english", "spanish", "medical")).isEqualTo(expected);
	}
}



	
	