package com.jajm.vinhos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jajm.vinhos.model.Vinho;

public interface Vinhos extends JpaRepository<Vinho, Long> {

}
