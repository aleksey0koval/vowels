package com.kavalchuk.vowels.service;

import com.kavalchuk.vowels.model.Word;

import java.util.List;

/**
 * Service for working with letters of words.
 */
public interface WordService {
    /**
     * Calculating average count of vowels.
     *
     * @param file - file with words.
     * @return list with info about average count of vowels.
     */
    List<Word> calculateAverageVowels(List<String> file);
}
