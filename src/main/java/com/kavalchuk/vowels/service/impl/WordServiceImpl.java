package com.kavalchuk.vowels.service.impl;

import com.kavalchuk.vowels.model.Vowel;
import com.kavalchuk.vowels.model.Word;
import com.kavalchuk.vowels.service.WordService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordServiceImpl implements WordService {
    private static final String SPLIT_EXPRESSION = "\\W+";

    @Override
    public List<Word> calculateAverageVowels(final List<String> allFile) {
        List<Word> result = new ArrayList<>();
        separateWord(allFile).forEach(s -> {
            var quantityVowel = 0;
            var word = new Word(s.length());
            countVowel(result, s, quantityVowel, word);
        });
        return sortedResult(result);
    }

    private List<String> separateWord(final List<String> allFile) {
        return allFile.stream()
                .flatMap(word -> Arrays.stream(word.trim().split(SPLIT_EXPRESSION)))
                .collect(Collectors.toList());
    }

    private void countVowel(List<Word> result, String s, int quantityVowel, Word word) {
        for (var c : s.toCharArray()) {
            if (c == Vowel.A.getValue() || c == Vowel.E.getValue() || c == Vowel.I.getValue() || c == Vowel.O.getValue()
                    || c == Vowel.U.getValue()) {
                word.addVowel(c);
                quantityVowel++;
            }
        }
        if (result.contains(word)) {
            for (var w : result) {
                if (w.equals(word)) {
                    w.addQuantityVowel(quantityVowel);
                    break;
                }
            }
        } else {
            result.add(word);
        }
    }

    private List<Word> sortedResult(List<Word> result) {
        return result.stream()
                .sorted(Comparator.comparing(Word::getWordLength).reversed())
                .collect(Collectors.toList());
    }
}
