package com.emeritus.reflect;

import java.lang.reflect.Method;

public class MethodWrapper {
    private final Method method;
    private final Class<?> type;

    public MethodWrapper(Method method) {
        this.method = method;
        this.type = method.getParameterTypes()[0];
    }

    public Method getMethod() {
        return method;
    }

    public Class<?> getType() {
        return type;
    }
}
