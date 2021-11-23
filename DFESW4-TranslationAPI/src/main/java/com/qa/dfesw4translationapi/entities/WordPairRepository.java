package com.qa.dfesw4translationapi.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WordPairRepository extends JpaRepository<WordPair, Integer> {

}
