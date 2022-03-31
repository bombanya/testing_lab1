package com.bombanya.lab1.hitchhiking;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class Achievement {

    public enum Purpose {
        HAVE_FUN, MAKE_MONEY, CONQUER_THE_WORLD, EXPRESS_YOURSELF
    }

    private final String name;
    private final Purpose purpose;
    private final long profit;
}
