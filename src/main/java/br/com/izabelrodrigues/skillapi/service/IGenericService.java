package br.com.izabelrodrigues.skillapi.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

public interface IGenericService <T>{


	Optional<T> findById(Long id);

	Iterable<T> findAll();

	ResponseEntity<?> saveOrUpdate(T entity);

	void delete(Long id);

}
