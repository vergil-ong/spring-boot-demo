package com.github.ong.util;

import java.util.UUID;

public class StringUtil {

    public static final String BLANK = "";

    public static String randomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
