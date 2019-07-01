package com.example.rahul.ounceadmin.Model.API.APIIntefaces;

public interface IGenericResponse<T> {
    void success(T out);
    void fail(String description);
}
