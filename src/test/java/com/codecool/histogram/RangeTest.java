package com.codecool.histogram;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class RangeTest {
    @Test
    public void Range_RangeIsLegal_RangeInstanceWasCreated() {
        // act
        Range range = new Range(0, 1);
        // assert
        assertNotNull(range);
        // TODO można dodać sprawdzenie IllegalArgumentException
    }

    @Test
    public void Range_RangeFromIsLowerThanZero_IllegalArgumentExceptionWasThrown() {
        // arrange
        int from = -5;
        int to = 2;

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> new Range(from, to));
    }

    @Test
    public void Range_RangeToIsSmallerThanFrom_IllegalArgumentExceptionWasThrown() {
        // arrange
        int from = 2;
        int to = 1;

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> new Range(from, to));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "abcdefgh", "abcde"})
    public void isInRange_WordLengthIsInRange_ReturnsTrue(String word) {
        // arrange
        int from = 3;
        int to = 8;

        Range range = new Range(from, to);

        // act
        boolean result = range.isInRange(word);

        // assert
        assertTrue(result);
    }

    @Test
    public void isInRange_WordLengthIsOutOfRange_ReturnsFalse() {
        // arrange
        int from = 3;
        int to = 8;

        Range range = new Range(from, to);

        // act
        boolean result = range.isInRange("Naprawdedługiesłowo");

        // assert
        assertFalse(result);
    }

    @Test
    public void toString_WhenRangeIsOneDigit_ReturnsRangeWithTrailingSpace() {
        // arrange
        int from = 3;
        int to = 8;

        String expected = "3  - 8 ";

        Range range = new Range(from, to);

        // act
        String result = range.toString();

        // assert
        assertEquals(expected, result);
    }

    @Test
    public void toString_WhenRangeIsTwoDigit_ReturnsRange() {
        // arrange
        int from = 30;
        int to = 80;

        String expected = "30 - 80";

        Range range = new Range(from, to);

        // act
        String result = range.toString();

        // assert
        assertEquals(expected, result);
        // TODO Można jeszcze three digits
    }
}
