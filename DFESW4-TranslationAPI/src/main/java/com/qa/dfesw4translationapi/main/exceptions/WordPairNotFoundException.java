package com.qa.dfesw4translationapi.main.exceptions;


import javax.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Word pair with the given ID does not exist")
public class WordPairNotFoundException extends EntityNotFoundException {
	
}