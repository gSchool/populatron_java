package com.galvanize;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Launcher {
    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {

            }
        });
        PopulationCounter popCount = injector.getInstance(PopulationCounter.class);

        Path path = Paths.get(args[0]);
        String result = popCount.count(path);
        System.out.println(result);
    }
}
