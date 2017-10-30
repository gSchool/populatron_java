package com.galvanize;

import com.galvanize.CsvParser;
import com.galvanize.model.City;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class CsvParserIT {
    private final Injector injector;

    public CsvParserIT() {
        injector = Guice.createInjector(new Module() {
            @Override
            public void configure(Binder binder) {
                
            }
        });
    }

    @Test
    public void csvParserShouldParse() {
        CsvParser<City> parser = injector.getInstance(CsvParser.class);
        List<City> expected = Arrays.asList(
                new City("US", "Denver", "Denver", "Mountain West", 1000000, 137.5, 37.5),
                new City("US", "Chicago", "Chicago", "Mountain West", 2000000, 117.5, 37.5)
        );

        List<String> fixture = Arrays.asList(
                "Country,City,AccentCity,Region,Population,Latitude,Longitude",
                expected.get(0).toString(),
                expected.get(1).toString()
        );

        // Exercise
        List<City> actual = parser.parse(fixture, City.class);

        // Assert
        Assert.assertEquals("CsvParser should return same output as input", expected, actual);
    }
}
