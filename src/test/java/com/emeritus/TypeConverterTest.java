package com.emeritus;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TypeConverterTest {
    @Test
    public void testUnknownType() {
        Exception actual = null;
        try {
            new TypeConverter().convert("test", TypeConverterTest.class);
        } catch (Exception ex) {
            actual = ex;
        }
        Assert.assertNotNull("Exception should have been thrown", actual);
        Assert.assertEquals("Exception should be IllegalArgumentException", IllegalArgumentException.class, actual.getClass());
        Assert.assertEquals("Message should be useful", actual.getMessage(), "Unknown type: class com.galvanize.TypeConverterTest");
    }

    @Test
    public void testInt() {
        int actual = (int)new TypeConverter().convert("1", int.class);
        Assert.assertEquals("int should convert", 1, actual);
    }

    @Test
    public void testDouble() {
        double actual = (double)new TypeConverter().convert("1", double.class);
        Assert.assertEquals("double should convert", 1.0, actual, 0.1);
    }

    @Test
    public void testString() {
        String actual = (String)new TypeConverter().convert("1", String.class);
        Assert.assertEquals("String should convert", "1", actual);
    }
}
