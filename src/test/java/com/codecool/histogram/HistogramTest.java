package com.codecool.histogram;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HistogramTest {
    private Histogram histogram;

    @BeforeEach
    public void init() {
        // initializacja przed każdym testem wywoływana, tworzymy nowy obiekt żeby jeżeli zmodyfikowaliśmy
        // coś w histogramie, to nie wpłyniemy na inne testy
        histogram = new Histogram();
    }

    @Test
    @DisplayName("This test will generate range with negative integer")
    public void generateRanges_StepIsNegativeInteger_ThrowsIllegalArgumentExceptionWithExpectedMessage() {
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> histogram.generateRanges(-3, 1), "Step must be positive.");
    }

    @Test
    public void generateRanges_AmountIsNegativeInteger_ThrowsIllegalArgumentExceptionWithExpectedMessage() {
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> histogram.generateRanges(3, -1), "Amount must be positive.");
    }

    @Test
    public void generateRanges_BothParametersArePositive_ReturnsExpectedRangeList() {
        // arrange
        List<Range> expectedList = new ArrayList<>();
        expectedList.add(new Range(1, 3));
        expectedList.add(new Range(4, 6));

        // act
        List<Range> result = histogram.generateRanges(3, 2);

        // assert
        assertEquals(expectedList, result);
    }

    @Test
    public void generate_InputIsNull_ThrowsIllegalArgumentException() {
        // arrange
        List<Range> ranges = List.of(new Range(0, 1));

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> histogram.generate(null, ranges));
    }

    @Test
    public void generate_RangeIsNull_ThrowsIllegalArgumentException() {
        // arrange
        String text = "abc";

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> histogram.generate(text, null));
    }

    @Test
    public void generate_InputIsEmpty_ReturnsEmptyHashMap() {
        // arrange
        String text = "";
        List<Range> ranges = List.of(new Range(0, 1));
        HashMap<Range, Integer> expectedMap = new HashMap<>();

        // act
        var result = histogram.generate(text, ranges);

        // assert
        assertEquals(result, expectedMap);
    }

    @Test
    public void generate_AllWordsInOneOfGivenRange_ReturnsExpectedHashMap() {
        // arrange
        String text = "Green angry doggo";
        List<Range> ranges = List.of(new Range(0, 1), new Range(3, 5));
        HashMap<Range, Integer> expectedMap = new HashMap<>();
        expectedMap.put(new Range(3, 5), 3);

        // act
        var result = histogram.generate(text, ranges);

        // assert
        assertEquals(result, expectedMap);
    }

    @Test
    public void generate_WordsAreInDifferentRanges_ReturnsExpectedHashMap() {
        // arrange
        String text = "Quick orange and white fox";
        List<Range> ranges = List.of(new Range(1, 2), new Range(3, 4), new Range(5, 6));
        HashMap<Range, Integer> expectedMap = new HashMap<>();
        expectedMap.put(new Range(3, 4), 2);
        expectedMap.put(new Range(5, 6), 3);

        // act
        var result = histogram.generate(text, ranges);

        // assert
        assertEquals(result, expectedMap);
    }

    @Test
    public void generate_WasCalledBeforeGenerate_ReturnsEmptyMap() {
        // arrange
        HashMap<Range, Integer> expectedMap = new HashMap<>();

        // act
        var result = histogram.getHistogram();

        // assert
        assertEquals(result, expectedMap);
    }

    @Test
    public void generate_WasCalledAfterGenerate_ReturnsEmptyMap() {
        // arrange
        String text = "Quick orange and white fox";
        List<Range> ranges = List.of(new Range(1, 2), new Range(3, 4), new Range(5, 6));
        HashMap<Range, Integer> expectedMap = new HashMap<>();
        expectedMap.put(new Range(3, 4), 2);
        expectedMap.put(new Range(5, 6), 3);
        histogram.generate(text, ranges);

        // act
        var result = histogram.getHistogram();

        // assert
        assertEquals(result, expectedMap);
    }

    @Test
    public void toString_WasCalledBeforeGenerate_ReturnsEmptyString() {
        // act
        var result = histogram.toString();

        // assert
        assertEquals("", result);
    }

    @Test
    public void toString_WasCalledAfterGenerate_ReturnsExpectedString() {
        // arrange
        String text = "Quick orange and white fox";
        List<Range> ranges = List.of(new Range(1, 2), new Range(3, 4), new Range(5, 6));
        histogram.generate(text, ranges);

        StringBuilder builder = new StringBuilder();

        builder.append("5  - 6 | ***")
                .append(System.lineSeparator())
                .append("3  - 4 | **")
                .append(System.lineSeparator());

        // act
        var result = histogram.toString();

        // assert
        assertEquals(builder.toString(), result);
    }
}
