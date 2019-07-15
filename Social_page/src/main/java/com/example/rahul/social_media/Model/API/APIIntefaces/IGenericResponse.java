package com.example.rahul.social_media.Model.API.APIIntefaces;

public interface IGenericResponse<T> {
    void success(T out);
    void fail(String description);
}
