package com.kavalchuk.vowels.unit.service;

import com.kavalchuk.vowels.model.Word;
import com.kavalchuk.vowels.service.WordService;
import com.kavalchuk.vowels.service.impl.WordServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WordServiceTest {
    private WordService wordService;

    @BeforeEach
    public void init() {
        wordService = new WordServiceImpl();
    }

    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(List.of("Platon made bamboo boats"), List.of(createWord(Set.of('a', 'o'), 6, 5, 2), createWord(Set.of('a', 'o'), 5, 2, 1), createWord(Set.of('a', 'e'), 4, 2, 1))),
                Arguments.of(List.of("Platon made bamboo boats", "my test"), List.of(createWord(Set.of('a', 'o'), 6, 5, 2), createWord(Set.of('a', 'o'), 5, 2, 1), createWord(Set.of('a', 'e'), 4, 2, 1), createWord(Set.of('e'), 4, 1, 1), createWord(Set.of(), 2, 0, 1))),
                Arguments.of(List.of(), List.of())
        );
    }

    @Test
    public void showWordTest() {
        var word = createWord(Set.of('a', 'o'), 6, 2, 1);
        var expected = "({a, o}, 6) -> 2";

        var actual = word.toString();

        assertEquals(expected, actual);
    }

    @ParameterizedTest()
    @MethodSource("testData")
    public void calculateAverageVowelsTest(List<String> input, List<Word> expected) {
        var actual = wordService.calculateAverageVowels(input);

        assertEquals(expected, actual);
    }

    private static Word createWord(final Set<Character> vowels,
                                   final int wordLength,
                                   final int quantityVowel,
                                   final int quantityWord) {
        var word = new Word(wordLength);
        word.setVowels(vowels);
        word.setQuantityVowel(quantityVowel);
        word.setQuantityWord(quantityWord);
        return word;
    }
}