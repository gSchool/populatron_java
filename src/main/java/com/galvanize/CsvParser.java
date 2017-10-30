package com.galvanize;

import com.galvanize.reflect.ClassWrapper;
import com.galvanize.reflect.WrapperFactory;
import com.google.common.annotations.VisibleForTesting;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CsvParser<T> {

    private final WrapperFactory factory;

    @Inject
    public CsvParser(WrapperFactory factory) {
        this.factory = factory;
    }

    public List<T> parse(List<String> lines, Class<T> model) {
        ClassWrapper<T> clazz = factory.wrap(model);
        String[] headerNames = parseLine(lines.get(0));
        List<T> models = lines.stream()
                .skip(1)
                .map(this::parseLine)
                .map(vals -> readModel(clazz, headerNames, vals))
                .collect(Collectors.toList());
        return models;
    }

    @VisibleForTesting
    T readModel(ClassWrapper<T> wrapper, String[] headers, String[] values) {
        T inst = wrapper.createInstance();
        IntStream.range(0, Math.min(headers.length, values.length))
                .forEach(i -> wrapper.setValue(inst, headers[i], values[i]));
        return inst;
    }

    @VisibleForTesting
    String[] parseLine(String line) {
        return line.split(","); // TODO: escape commas in quotes
    }
}
