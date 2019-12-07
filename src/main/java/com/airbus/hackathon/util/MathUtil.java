package com.airbus.hackathon.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MathUtil {

    private static Random random = new Random(System.currentTimeMillis());

    /**
     * Returns the max using <strong>compareTo</strong> method of two
     * {@link Comparable} objects
     *
     * @param d1 - <{@link T} extends {@link Comparable}>
     * @param d2 - <{@link T} extends {@link Comparable}>
     * @return {@link T} - the max using <strong>compareTo</strong> method
     */
    public static <T extends Comparable<? super T>> T max(T d1, T d2) {
        if (d1.compareTo(d2) >= 0)
            return d1;
        return d2;
    }

    /**
     * Returns the min using <strong>compareTo</strong> method of two
     * {@link Comparable} objects
     *
     * @param d1 - <{@link T} extends {@link Comparable}>
     * @param d2 - <{@link T} extends {@link Comparable}>
     * @return {@link T} - the min using <strong>compareTo</strong> method
     */
    public static <T extends Comparable<? super T>> T min(T d1, T d2) {
        if (d1.compareTo(d2) <= 0)
            return d1;
        return d2;
    }

    /**
     * Returns the minMax - <strong>2</strong>-sized array for a {@link List} of
     * {@link T}
     *
     * @param list - {@link List} of {@link T}
     * @return {@link []} - {min, max}
     */
    public static <T extends Comparable<? super T>> Object[] minMax(Collection<T> list) {
        T min = null, max = null;
        if (list != null) {
            for (T t : list) {
                if (min == null || min.compareTo(t) <= 0) {
                    min = t;
                }
                if (max == null || max.compareTo(t) >= 0) {
                    max = t;
                }
            }
        }
        return new Object[]{min, max};
    }

    /**
     * Returns the median {@link T} which is the left bound in case of even
     * sized {@link List} of {@link T}
     *
     * @param list - {@link List} of {@link T}
     * @return {@link T} - median
     */
    public static <T> T median(List<T> list) {
        Integer size = list.size();
        Integer medianIndex = (size + 1) / 2;
        try {
            return list.get(medianIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Returns the closest {@link Integer multiple} of the {@link Integer
     * factor}, which is less than or equal to the {@link Integer value}
     * provided
     *
     * @param val    - {@link Integer}
     * @param factor - {@link Integer}
     * @return {@link Integer}
     */
    public static Integer roundTo(Integer val, Integer factor) {
        return (val + factor / 2) / factor * factor;
    }

    /**
     * Returns the closest {@link Integer} ending with <strong>49</strong> or
     * <strong>99</strong>, which is less than or equal to the {@link Integer
     * value} provided
     *
     * @param val - {@link Integer}
     * @return {@link Integer}
     */
    public static Integer to99(Integer val) {
        return roundTo(val, 50) - 1;
    }

    /**
     * Uses {@link Integer#parseInt(String)} after removing specific characters
     * like [,], and gracefully handles <strong>null</strong> and other
     * exceptions - returns <strong>null</strong> if {@link String numberStr} is
     * null or throws or an exception in parsing.
     *
     * @param numberStr - {@link String}
     * @return {@link Integer}
     */
    public static Integer parseInt(String numberStr) {
        return parseInt(numberStr, null);
    }

    /**
     * Uses {@link Integer#parseInt(String)} after removing specific characters
     * like [,], and gracefully handles <strong>null</strong> and other
     * exceptions - gives {@link Integer defaultValue} if {@link String
     * numberStr} is null or throws or an exception in parsing.
     *
     * @param numberStr    - {@link String}
     * @param defaultValue - {@link Integer}
     * @return {@link Integer}
     */
    public static Integer parseInt(String numberStr, Integer defaultValue) {
        Integer number = defaultValue;
        numberStr = TransformUtil.removeExtraCharactersForNumber(numberStr);
        if (numberStr != null) {
            try {
                number = Integer.parseInt(numberStr);
            } catch (NumberFormatException e) {
                number = defaultValue;
            }
        }
        return number;
    }

    /**
     * Uses {@link Double#parseDouble(String)} after removing specific
     * characters like [,] and gracefully handles <strong>null</strong> and
     * other exceptions
     *
     * @param numberStr - {@link String}
     * @return {@link Double}
     */
    public static Double parseDouble(String numberStr) {
        Double number = null;
        numberStr = TransformUtil.removeExtraCharactersForNumber(numberStr);
        if (numberStr != null) {
            try {
                number = Double.parseDouble(numberStr);
            } catch (NumberFormatException e) {
                number = null;
            }
        }
        return number;
    }

    /**
     * Returns a <strong>positive</strong> random <strong>integer</strong>
     *
     * @return {@link int}
     */
    public static int randomPositiveInt() {
        return Math.abs(random.nextInt()) + 1;
    }

    /**
     * Returns the {@link double} number rounded off to the
     * <strong>decimalPlaces</strong> provided
     *
     * @param number        - {@link double}
     * @param decimalPlaces - {@link int}
     * @return {@link double}
     */
    public static double round(double number, int decimalPlaces) {
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Returns <strong>true</strong> if the difference between the two floats is
     * infinitesimally small
     *
     * @param f1 - {@link float}
     * @param f2 - {@link float}
     * @return {@link boolean}
     */
    public static boolean equal(float f1, float f2) {
        return Math.abs(f1 - f2) < 0.0000000000000000000000000000001f;
    }

    public static <T> Set<T> notInSet(Collection<T> collection, Set<T> set) {
        if (collection == null) {
            return null;
        }
        Set<T> resultSet = new HashSet<T>();
        if (set == null) {
            resultSet.addAll(collection);
            return resultSet;
        }
        for (T t : collection) {
            if (!set.contains(t)) {
                resultSet.add(t);
            }
        }
        return resultSet;
    }

}
