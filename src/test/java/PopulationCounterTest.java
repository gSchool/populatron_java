import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PopulationCounter.class)
public class PopulationCounterTest {
    @Test
    public void populationCounterShouldReturnCorrectSum() throws Exception {

        //given
        String expected = "World population is: 3,000,000";
        List<String> fixture = Arrays.asList(
                "Country,City,AccentCity,Region,Population,Latitude,Longitude",
                "US,Denver,Denver,Mountain West,1000000,137.5,37.5",
                "US,Chicago,Chicago,Mid West,2000000,117.5,37.5"
        );
        Path path = Paths.get("./data/worldcitiespop.csv");
        PowerMockito.mockStatic(Files.class);
        BDDMockito.given(Files.readAllLines(path, StandardCharsets.ISO_8859_1)).willReturn(fixture);

        //when
        String actual = new PopulationCounter().count(path);

        //then
        PowerMockito.verifyStatic(Mockito.times(1));
        Assert.assertEquals("PopulationCounter should return correct result", expected, actual);
    }
}
