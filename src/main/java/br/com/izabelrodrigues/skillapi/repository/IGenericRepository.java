package br.com.izabelrodrigues.skillapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGenericRepository <T> extends CrudRepository<T, Long>{

}
