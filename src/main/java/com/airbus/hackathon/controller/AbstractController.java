package com.airbus.hackathon.controller;



import com.airbus.hackathon.response.BaseObjectResponse;
import com.airbus.hackathon.response.ServiceResponse;
import com.airbus.hackathon.service.AbstractService;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractController<T, U, V> {



    public abstract AbstractService<T> getService();

    public abstract T fromRequest(U data);

    public abstract V toResponse(T data);

    public ServiceResponse<BaseObjectResponse<V>> findById(Integer id) {
        T data = getService().findById(id);
        return new ServiceResponse<BaseObjectResponse<V>>(new BaseObjectResponse<V>(toResponse(data)));
    }

    public ServiceResponse<BaseObjectResponse<V>> create(U payload) {
        T entity = fromRequest(payload);
        T data = getService().create(entity);
        return new ServiceResponse<BaseObjectResponse<V>>(new BaseObjectResponse<V>(toResponse(data)));
    }

    public ServiceResponse<BaseObjectResponse<V>> update(U payload, Integer id) {
        T entity = fromRequest(payload);
        T data = getService().update(entity, id);
        return new ServiceResponse<BaseObjectResponse<V>>(new BaseObjectResponse<V>(toResponse(data)));
    }

    public List<V> toResponse(List<T> data) {
        List<V> responseList = new ArrayList<V>();
        data.forEach((responseObject) -> {
            responseList.add(toResponse(responseObject));
        });
        return responseList;
    }
}
