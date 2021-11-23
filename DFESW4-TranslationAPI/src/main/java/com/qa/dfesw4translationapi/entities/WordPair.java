package com.qa.dfesw4translationapi.entities;

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
	private String dateCreated = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	
}
