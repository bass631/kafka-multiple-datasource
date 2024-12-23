package ru.multiple.datasource.util;

@FunctionalInterface
public interface ValueConditionPredicate {
    boolean matches(int value);
}