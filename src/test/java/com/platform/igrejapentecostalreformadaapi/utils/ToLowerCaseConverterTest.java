package com.platform.igrejapentecostalreformadaapi.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class ToLowerCaseConverterTest {

    @InjectMocks
    ToLowerCaseConverter toLowerCaseConverter;

    @DisplayName("JUnit test for LowerCaseConverter converter class convert method")
    @Test
    void testGivenAString_WhenCallConvertMethod_ThenReturnSameStringAsLowerCaseCharacters() {
        String word = "LUCAS PEIXOTO FERNANDES";

        String actual = toLowerCaseConverter.convert(word);
        String expected = "lucas peixoto fernandes";

        assertEquals(expected, actual);
    }
}