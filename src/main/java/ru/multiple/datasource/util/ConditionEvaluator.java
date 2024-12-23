package ru.multiple.datasource.util;

public final class ConditionEvaluator {

    private ConditionEvaluator() {
    }

    public static boolean checkConditionDs1(final int value) {
        return value <= 10;
    }

    public static boolean checkConditionDs2(final int value) {
        return value >= 11 && value <= 20;
    }

    public static boolean checkConditionDs3(final int value) {
        return value >= 21 && value <= 30;
    }

    public static boolean checkConditionDs4(final int value) {
        return value >= 31 && value <= 40;
    }

    public static boolean checkConditionDs5(final int value) {
        return value >= 41 && value <= 50;
    }
}
