package com.emeritus.reflect;

import com.emeritus.TypeConverter;
import com.emeritus.reflect.ClassWrapper;

import javax.inject.Inject;

public class WrapperFactory {
    private final TypeConverter converter;

    @Inject
    WrapperFactory(TypeConverter converter) {
        this.converter = converter;
    }

    public <T> ClassWrapper<T> wrap(Class<T> model) {
        return new ClassWrapper<T>(model, converter);
    }

}
