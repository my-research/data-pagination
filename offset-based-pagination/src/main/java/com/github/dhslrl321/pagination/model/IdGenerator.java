package com.github.dhslrl321.pagination.model;

import java.time.Instant;
import java.util.Random;

public class IdGenerator {
    private static final long EPOCH_OFFSET = 1631078400000L; // 2021-09-09 00:00:00 UTC in milliseconds

    public static Long newId() {
        long epochMillis = Instant.now().toEpochMilli() - EPOCH_OFFSET;
        String randomDigits = generateRandomDigits();

        return Long.parseLong(epochMillis + randomDigits);
    }

    private static String generateRandomDigits() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            stringBuilder.append(random.nextInt(10)); // 0 to 9
        }
        return stringBuilder.toString();
    }
}
