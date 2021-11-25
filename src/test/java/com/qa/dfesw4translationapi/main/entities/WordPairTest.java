package com.qa.dfesw4translationapi.main.entities;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WordPairTest {
	
	@Test
	public void WordPairEntityTest() {
		WordPair pair = new WordPair(1, "english", "tree", "spanish", "arbol", "general", "2021-11-24");
		
		String expected = "WordPair [id=" + pair.getId() + ", language1=" + pair.getLanguage1()
				+ ", language1Word=" + pair.getLanguage1Word() + ", language2=" + pair.getLanguage2()
				+ ", language2Word=" + pair.getLanguage2Word() + "]";
		assertThat(pair.toString()).isEqualTo(expected);
		assertThat(pair.getDateCreated()).isEqualTo("2021-11-24");
		assertThat(pair.getField()).isEqualTo("general");
	}
	
	@Test
	public void WordPairSettersTest() {
		WordPair pair = new WordPair(1, "english", "tree", "spanish", "arbol", "general", "2021-11-24");
		
		pair.setLanguage1("german");
		pair.setLanguage1Word("wagen");
		pair.setLanguage2("italian");
		pair.setLanguage2Word("macchina");
		pair.setField("automotive");
		pair.setDateCreated("2021-11-25");
		pair.setId(2);
		
		
		String expected = "WordPair [id=" + 2 + ", language1=" + "german"
				+ ", language1Word=" + "wagen" + ", language2=" + "italian"
				+ ", language2Word=" + "macchina" + "]";
		assertThat(pair.toString()).isEqualTo(expected);
		assertThat(pair.getDateCreated()).isEqualTo("2021-11-25");
		assertThat(pair.getField()).isEqualTo("automotive");
	}
}
