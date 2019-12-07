package com.airbus.hackathon.manager;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractManager<T> {

    public abstract JpaRepository<T, Integer> getRepo();

    public T findById(Integer id) {
        return getRepo().findOne(id);
    }

    public T create(T entity) {
        return getRepo().save(entity);
    }

    public void delete(T entity) {
        getRepo().delete(entity);
    }

    public void deleteList(List<T> entityList) {
        getRepo().delete(entityList);
    }

    public T update(T entity) {
        return getRepo().save(entity);
    }

    public List<T> findAll() { return getRepo().findAll(); }

    public List<T> createList(List<T> entityList) { return getRepo().save(entityList); }

}
