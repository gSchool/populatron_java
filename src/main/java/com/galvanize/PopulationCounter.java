package com.galvanize;

import com.galvanize.model.City;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

class PopulationCounter {

    private final FileReader reader;
    private final CsvParser<City> parser;

    @Inject
    PopulationCounter(FileReader reader, CsvParser<City> parser) {
        this.reader = reader;
        this.parser = parser;
    }

    String count(Path path) throws IOException {
        List<String> lines = reader.readAllLines(path);
        List<City> cities = parser.parse(lines, City.class);
        long sum = cities.stream()
                .map(c -> (long)c.getPopulation())
                .reduce(0l, (a,b) -> a + b);
        String num = NumberFormat.getInstance(Locale.getDefault()).format(sum);
        String result = String.format("World population is: %s", num);
        return result;
    }
}
