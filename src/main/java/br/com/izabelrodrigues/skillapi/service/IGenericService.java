package br.com.izabelrodrigues.skillapi.service;

import java.util.Optional;

public interface IGenericService <T>{


	Optional<T> findById(Long id);

	Iterable<T> findAll();

	Optional<T> saveOrUpdate(T entity);

	void delete(Long id);

	T save(T entity);

	T update(T entity);

}
