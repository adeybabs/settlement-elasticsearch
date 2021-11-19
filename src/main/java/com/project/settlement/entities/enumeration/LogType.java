package com.project.settlement.entities.enumeration;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum LogType {
    VISA, MASTERCARD, INTERAFFILIATE, ECOMMERCE, MASTERPASS, MVISA, XPRESSCASH, CUP, CBA;


    private static final List<LogType> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static LogType type() {
        return VALUES.get(RANDOM.nextInt(SIZE));

    }
    }