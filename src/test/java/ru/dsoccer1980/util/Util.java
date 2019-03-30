package ru.dsoccer1980.util;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class Util {

    public static String getData(ByteArrayOutputStream out) {
        return new String(out.toByteArray(), StandardCharsets.UTF_8).replaceAll("[\\r\\n]+", "");
    }
}
