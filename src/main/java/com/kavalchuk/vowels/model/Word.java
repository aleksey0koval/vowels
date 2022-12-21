package com.kavalchuk.vowels.model;

import java.text.DecimalFormat;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Word {
    private Set<Character> vowels;
    private Integer wordLength;
    private Integer quantityVowel;
    private Integer quantityWord;

    public Word(final int length) {
        this.vowels = new TreeSet<>();
        this.wordLength = length;
        this.quantityVowel = 0;
        this.quantityWord = 1;
    }

    public Set<Character> getVowels() {
        return vowels;
    }

    public void setVowels(Set<Character> vowels) {
        this.vowels = vowels;
    }

    public void setWordLength(Integer wordLength) {
        this.wordLength = wordLength;
    }

    public Integer getQuantityVowel() {
        return quantityVowel;
    }

    public void setQuantityVowel(Integer quantityVowel) {
        this.quantityVowel = quantityVowel;
    }

    public Integer getQuantityWord() {
        return quantityWord;
    }

    public void setQuantityWord(Integer quantityWord) {
        this.quantityWord = quantityWord;
    }

    public Integer getWordLength() {
        return wordLength;
    }

    public void addVowel(final char c) {
        this.vowels.add(c);
        this.quantityVowel++;
    }

    public void addQuantityVowel(final int quantityVowel) {
        this.quantityVowel += quantityVowel;
        this.quantityWord++;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(vowels, word.vowels) && Objects.equals(wordLength, word.wordLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vowels, wordLength);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s) -> %s", showVowels(), wordLength, showAverageQuantityVowel());
    }

    private String showVowels() {
        var stringBuilder = new StringBuilder("{");
        if (!vowels.isEmpty()) {
            vowels = vowels.stream()
                    .sorted(Character::compareTo)
                    .collect(Collectors.toCollection(LinkedHashSet::new));
            var iterator = vowels.iterator();
            if (!iterator.hasNext()) {
                stringBuilder.append("}");
            } else {
                while (true) {
                    stringBuilder.append(iterator.next());
                    if (!iterator.hasNext()) {
                        break;
                    }
                    stringBuilder.append(", ");
                }
            }
        }
        return stringBuilder.append("}").toString();
    }

    private String showAverageQuantityVowel() {
        return new DecimalFormat("#.##").format((double) quantityVowel / quantityWord);
    }
}
