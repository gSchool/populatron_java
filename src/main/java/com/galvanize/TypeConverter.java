package com.galvanize;

public class TypeConverter {
    public Object convert(String value, Class<?> type) {
        if (type == String.class) return value;
        if (type == int.class) return value.length() == 0 ? 0 : Integer.parseInt(value); // TODO: nullability
        if (type == double.class) return Double.parseDouble(value);
        throw new IllegalArgumentException("Unknown type: " + type);
    }
}
