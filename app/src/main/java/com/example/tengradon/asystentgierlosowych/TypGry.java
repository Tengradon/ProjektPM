package com.example.tengradon.asystentgierlosowych;

/**
 * Created by Rafal on 2016-06-27.
 */
public enum TypGry {
    LOTTO(1), EKSTRA_PENSJA(2), MULTI_MULTI14(3), MULTI_MULTI22(4), MINI_LOTTO(5);
    private final int value;
    TypGry(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
