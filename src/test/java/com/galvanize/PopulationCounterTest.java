package com.galvanize;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class PopulationCounterTest {
    private final Injector injector = Guice.createInjector((Module) binder -> {
                binder.bind(FileReader.class).toInstance(Mockito.mock(FileReader.class));
            }
    );

    @Test
    public void populationCounterShouldReturnCorrectSum() throws Exception {
        PopulationCounter popCount = injector.getInstance(PopulationCounter.class);
        FileReader reader = injector.getInstance(FileReader.class);

        // Setup
        String expected = "World population is: 3,000,000";
        List<String> fixture = Arrays.asList(
                "Country,City,AccentCity,Region,Population,Latitude,Longitude",
                "US,Denver,Denver,Mountain West,1000000,137.5,37.5",
                "US,Chicago,Chicago,Mid West,2000000,117.5,37.5"
        );
        Path path = Paths.get(".");
        BDDMockito.given(reader.readAllLines(path)).willReturn(fixture);

        //when
        String actual = popCount.count(path);

        //then
        Mockito.verify(reader, Mockito.times(1)).readAllLines(path);
        Assert.assertEquals("PopulationCounter should return correct result", expected, actual);
    }
}
