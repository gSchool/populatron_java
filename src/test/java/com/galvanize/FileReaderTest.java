package com.galvanize;

import com.galvanize.FileReader;
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
import java.util.Arrays;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FileReader.class)
public class FileReaderTest {
    @Test
    public void fileReaderShouldReturnAllLinesInFile() throws Exception {

        //given
        List<String> expected = Arrays.asList(
                "Line1",
                "Line2",
                "Line3"
        );
        Path path = Paths.get(".");
        PowerMockito.mockStatic(Files.class);
        BDDMockito.given(Files.readAllLines(path, StandardCharsets.ISO_8859_1)).willReturn(expected);

        //when
        List<String> actual = new FileReader().readAllLines(path);

        //then
        PowerMockito.verifyStatic(Mockito.times(1));
        Assert.assertEquals("FileReader should return all lines", expected, actual);
    }
}
