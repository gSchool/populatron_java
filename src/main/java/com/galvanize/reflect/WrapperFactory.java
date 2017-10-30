package com.galvanize.reflect;

import com.galvanize.TypeConverter;
import com.galvanize.reflect.ClassWrapper;

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
