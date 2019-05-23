package com.demo.lostandfound.service.utils.mapping;

public class ConfigurationMapper implements TypeMapper {
    private final org.dozer.Mapper beanMapper;

    public ConfigurationMapper(org.dozer.Mapper beanMapper) {
        this.beanMapper = beanMapper;
    }

    @Override
    public <T> T map(Class<T> targetType, Object source) {

        return beanMapper.map(source, targetType);
    }
}
