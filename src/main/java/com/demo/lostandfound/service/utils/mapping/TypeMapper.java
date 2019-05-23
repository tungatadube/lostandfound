package com.demo.lostandfound.service.utils.mapping;

public interface TypeMapper {
    <T> T map(Class<T> targetType, Object source);
}
