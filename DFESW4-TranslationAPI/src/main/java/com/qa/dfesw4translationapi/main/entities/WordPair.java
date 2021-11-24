package com.qa.dfesw4translationapi.main.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WordPair {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String language1;
	
	@Column
	private String language1Word;
	
	@Column
	private String language2;
	
	@Column
	private String language2Word;
	
	@Column
	private String field = "General";
	
	@Column
	private String dateCreated = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	
	public WordPair() {
		
	}

	public WordPair(int id, String language1, String language1Word, String language2, String language2Word,
			String field, String dateCreated) {
		super();
		this.id = id;
		this.language1 = language1;
		this.language1Word = language1Word;
		this.language2 = language2;
		this.language2Word = language2Word;
		this.field = field;
		this.dateCreated = dateCreated;
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLanguage1() {
		return language1;
	}

	public void setLanguage1(String language1) {
		this.language1 = language1;
	}

	public String getLanguage1Word() {
		return language1Word;
	}

	public void setLanguage1Word(String language1Word) {
		this.language1Word = language1Word;
	}

	public String getLanguage2() {
		return language2;
	}

	public void setLanguage2(String language2) {
		this.language2 = language2;
	}

	public String getLanguage2Word() {
		return language2Word;
	}

	public void setLanguage2Word(String language2Word) {
		this.language2Word = language2Word;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "WordPair [id=" + id + ", language1=" + language1 + ", language1Word=" + language1Word + ", language2="
				+ language2 + ", language2Word=" + language2Word + "]";
	}
}
