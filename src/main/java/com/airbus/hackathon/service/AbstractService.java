package com.airbus.hackathon.service;

import com.airbus.hackathon.entity.AbstractEntity;
import com.airbus.hackathon.exception.InternalServerException;
import com.airbus.hackathon.exception.NotFoundException;
import com.airbus.hackathon.manager.AbstractManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class AbstractService<T> {

    public abstract AbstractManager<T> getManager();

    public T copyNonNullValues(T entity, T oldEntity) {
        ((AbstractEntity<?>) entity).setCreatedAt(((AbstractEntity<?>) oldEntity).getCreatedAt());
        return entity;
    }

    public T findById(Integer id) {
        T entity;
        entity = getManager().findById(id);
        if (entity == null)
            throw new NotFoundException("Could not find entity in the database with id " + id.toString());
        return entity;
    }

    public T create(T entity) {
        T newEntity;
        try {
            newEntity = getManager().create(entity);
        } catch (Exception e) {
            throw new InternalServerException("Could not create entity in the database " + entity.toString());
        }
        return newEntity;
    }

    public T update(T entity, Integer id) {
        T updatedEntity, oldEntity;
        oldEntity = getManager().findById(id);
        if (oldEntity == null)
            throw new NotFoundException("Could not find entity in the database with id " + id.toString());
        updatedEntity = copyNonNullValues(entity, oldEntity);
        try {
            updatedEntity = getManager().update(updatedEntity);
        } catch (Exception e) {
            throw new InternalServerException("Could not update entity in the database " + entity.toString());
        }
        return updatedEntity;
    }

    public List<T> findAll() {
        return getManager().findAll();
    }

}
