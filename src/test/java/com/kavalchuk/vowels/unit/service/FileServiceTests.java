package com.kavalchuk.vowels.unit.service;

import com.kavalchuk.vowels.service.FileService;
import com.kavalchuk.vowels.service.impl.FileServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileServiceTests {
    private FileService fileService;

    @BeforeEach
    public void init() {
        fileService = new FileServiceImpl();
    }

    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of("data/test-input.txt", List.of("Platon made bamboo boats")),
                Arguments.of("data/test-input2.txt", List.of("Platon made bamboo boats", "my test")),
                Arguments.of("data/test-input3.txt", List.of())

        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void readTest(String fileName, List<String> expected) throws IOException {
        var actual = fileService.read(getFileName(fileName));

        assertEquals(expected, actual);
    }

    @Test
    void writeTest() throws IOException {
        var outputFileName = "test_output.txt";
        var actual = fileService.write(outputFileName, anyList());

        assertTrue(actual);
        assertTrue(Files.deleteIfExists(Path.of(outputFileName)));
    }

    private static String getFileName(String fileName) throws IOException {
        return new ClassPathResource(Path.of(fileName).toString()).getFile().getPath();
    }
}
