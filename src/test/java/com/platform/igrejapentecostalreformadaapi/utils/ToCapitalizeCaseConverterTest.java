package com.platform.igrejapentecostalreformadaapi.utils;

import com.platform.igrejapentecostalreformadaapi.utils.converters.ToCapitalizeCaseConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class ToCapitalizeCaseConverterTest {

    @Spy
    private ToCapitalizeCaseConverter toCapitalizeCaseConverter;

    @DisplayName("JUnit test for CapitalizeCaseConverter converter class convert method")
    @Test
    void testGivenAString_WhenCallConvertMethod_ThenReturnSameStringAsCapitalizeCaseCharacters() {
        String word = "LUCAS PEIXOTO FERNANDES";

        String actual = toCapitalizeCaseConverter.convert(word);
        String expected = "Lucas Peixoto Fernandes";

        assertEquals(expected, actual);
    }
}