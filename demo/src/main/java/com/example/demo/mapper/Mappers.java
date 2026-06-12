package com.example.demo.mapper;

import java.util.List;
import java.util.function.Function;

public class Mappers {
    public static <T, R> List<R> toDto(List<T> entity, Function<T,R> mapper){
        return entity.stream().map(mapper).toList();
    }
}
