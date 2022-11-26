package com.emeritus.reflect;

import com.emeritus.TypeConverter;
import com.google.common.annotations.VisibleForTesting;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ClassWrapper<T> {
    private final Class<T> model;
    private final Map<String, MethodWrapper> methods;
    private final TypeConverter converter;

    public ClassWrapper(Class<T> model, TypeConverter converter) {
        this.model = model;
        this.converter = converter;
        this.methods = getMethods(model);
    }

    public void setValue(T inst, String fieldName, String value) {
        try {
            MethodWrapper method = methods.get("set" + fieldName);
            Object val = converter.convert(value, method.getType());
            method.getMethod().invoke(inst, val);
        } catch (Exception ex) {
            throw new IllegalStateException("Error setting " + fieldName, ex);
        }
    }

    public T createInstance() {
        try {
            return model.newInstance();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot instantiate class: " + model.getName(), ex);
        }
    }

    @VisibleForTesting
    Map<String, MethodWrapper> getMethods(Class<T> model) {
        return Arrays.stream(model.getMethods())
                .filter(m -> m.getName().startsWith("set"))
                .filter(m -> m.getParameterCount() == 1)
                .filter(m -> m.getReturnType() == void.class)
                .collect(Collectors.toMap(Method::getName, MethodWrapper::new));
    }

}
