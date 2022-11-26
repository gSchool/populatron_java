package com.emeritus;

import com.emeritus.model.PersonModel;
import com.emeritus.reflect.ClassWrapper;
import com.emeritus.reflect.WrapperFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

// Example of mockist school of thought. Mock all of CsvParser's dependencies!
public class CsvParserTest {
    private final Injector injector = Guice.createInjector((Module) binder -> {
                binder.bind(WrapperFactory.class).toInstance(Mockito.mock(WrapperFactory.class));
            }
    );

    @Test
    public void parseLineShouldSplit() {
        // Setup
        CsvParser<PersonModel> parser = injector.getInstance(CsvParser.class);
        String[] expected = new String[] {"firstName", "lastName"};

        // Exercise
        String[] actual = parser.parseLine("firstName,lastName");

        // Assert
        Assert.assertEquals("parseLine() should split on comma", expected, actual);
    }

    @Test
    public void readModelShouldReturnAModel() {
        // Setup
        CsvParser<PersonModel> parser = injector.getInstance(CsvParser.class);
        WrapperFactory factory = injector.getInstance(WrapperFactory.class);
        ClassWrapper<PersonModel> wrapper = Mockito.mock(ClassWrapper.class);
        PersonModel person = new PersonModel();
        BDDMockito.given(factory.wrap(PersonModel.class)).willReturn(wrapper);
        BDDMockito.given(wrapper.createInstance()).willReturn(person);
        String[] headers = new String[] {"firstName", "lastName"};
        String[] values = new String[] {"Homer", "Simpson"};

        // Exercise
        PersonModel actual = parser.readModel(wrapper, headers, values);

        // Assert
        Mockito.verify(wrapper, Mockito.times(1)).setValue(person, "firstName", "Homer");
        Mockito.verify(wrapper, Mockito.times(1)).setValue(person, "lastName", "Simpson");
        Assert.assertEquals("Person should be returned", person, actual);
    }

    @Test
    public void parseShouldCallReadModel() {
        // Setup
        PersonModel person = new PersonModel();
        ClassWrapper<PersonModel> wrapper = Mockito.mock(ClassWrapper.class);
        BDDMockito.given(wrapper.createInstance()).willReturn(person);

        // Mock WrapperFactory
        WrapperFactory factory = injector.getInstance(WrapperFactory.class);
        BDDMockito.given(factory.wrap(PersonModel.class)).willReturn(wrapper);

        // Mock parser methods not under test
        List<String> lines = Arrays.asList("firstName,lastName", "Homer,Simpson");
        CsvParser<PersonModel> parser = injector.getInstance(CsvParser.class);

        // Exercise
        List<PersonModel> actual = parser.parse(lines, PersonModel.class);

        // Assert
        Assert.assertEquals("1 person should be returned", 1, actual.size());
        Assert.assertEquals("Person should be returned", person, actual.get(0));
    }


}
