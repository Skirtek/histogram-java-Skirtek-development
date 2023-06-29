package com.codecool.histogram;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TextReaderTest {
    @Test
    public void read_FileNotExists_ThrowsFileNotFoundException() {
        // act
        TextReader textReader = new TextReader("/.rtu.txt");

        // assert
        assertThrows(FileNotFoundException.class, textReader::read);
    }

    @Test
    public void read_FileHasOnlyOneLine_ReturnsExpectedResult() throws IOException {
        // arrange
        int expectedLines = 1;
        String expectedLine = "Harry Potter and the Sorcerer's Stone";

        TextReader textReader = new TextReader("src/test/resources/test.txt");

        // act
        String[] result = textReader.read().split(System.lineSeparator());

        // assert
        // PAMIĘTAĆ ŻE ZAWSZE NAJPIERW EXPECTED
        assertEquals(expectedLines, result.length);
        assertEquals(expectedLine, result[0]);
    }

    @Test
    public void read_FileIsEmpty_ReturnsExpectedResult() throws IOException {
        // arrange
        int expectedLines = 1;
        String expectedLine = "";

        TextReader textReader = new TextReader("src/test/resources/empty.txt");

        // act
        String[] result = textReader.read().split(System.lineSeparator());

        // assert
        assertEquals(expectedLines, result.length);
        assertEquals(expectedLine, result[0]);
    }

    @Test
    public void read_FileIsMultiLines_ReturnsExpectedLinesAmount() throws IOException {
        // arrange
        int expectedLines = 33;

        TextReader textReader = new TextReader("src/test/resources/text.txt");

        // act
        String[] result = textReader.read().split(System.lineSeparator());

        // assert
        assertEquals(expectedLines, result.length);
    }
}
