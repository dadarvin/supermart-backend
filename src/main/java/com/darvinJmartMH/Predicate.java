package com.darvinJmartMH;

/**
 * Sebagai functional interface untuk melakuakn pengecekan kondisi dan memberikan hasil berupa boolean
 * @param <T>
 */
@FunctionalInterface
public interface Predicate<T> {
    public boolean predicate(T arg);
}
