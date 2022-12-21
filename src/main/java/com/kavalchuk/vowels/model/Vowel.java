package com.kavalchuk.vowels.model;

public enum Vowel {
    A('a'),
    E('e'),
    I('i'),
    O('o'),
    U('u');

    private final Character value;

    Vowel(Character value) {
        this.value = value;
    }

    public Character getValue() {
        return value;
    }
}
