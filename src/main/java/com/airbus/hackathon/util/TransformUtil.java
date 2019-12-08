package com.airbus.hackathon.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FastByteArrayOutputStream;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransformUtil {

    private static final Logger log = LoggerFactory.getLogger(TransformUtil.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static Map<String, Map<String, String>> LOCALE_WISE_TRANSLATION_MAP = new HashMap<String, Map<String, String>>();

    public static void init(File translationFile) {
        try {
            log.info("Going to init file {}, {}", translationFile.toString(), translationFile);
            LOCALE_WISE_TRANSLATION_MAP = new HashMap<String, Map<String, String>>();
            LOCALE_WISE_TRANSLATION_MAP = (Map<String, Map<String, String>>) fromJson(translationFile, LOCALE_WISE_TRANSLATION_MAP.getClass());
        } catch (Exception e) {
            log.error("Exception in transforming map from Json: {}", e);
        }
    }

    public static String getLocaleWiseTranslatedString(String locale, String translateFrom) {
        String translateTo;
        Map<String, String> m = LOCALE_WISE_TRANSLATION_MAP.get(locale);
        if (m != null) {
            translateTo = m.get(translateFrom);
        } else {
            translateTo = translateFrom;
        }
        if (translateTo == null) {
            translateTo = translateFrom;
        }
        return translateTo;
    }


    public static <T> Object fromJson(File file, Class<?> valueType) {
        try {
            if (file != null) {
                return objectMapper.readValue(file, valueType);
            }
        } catch (Exception e) {
            log.error("Error in fromJson(), file path : " + file.toString() + " ; Exception: " + e.getMessage());
        }
        return null;
    }

    /**
     * @param arrayOfStrings - <strong>Array</strong> of {@link String}
     * @return {@link List} of {@link Integer}
     */
    public static List<Integer> stringToNumberList(String[] arrayOfStrings) {
        List<Integer> results = null;
        if (arrayOfStrings != null) {
            results = new ArrayList<Integer>();
            for (int i = 0; i < arrayOfStrings.length; i++) {
                try {
                    results.add(Integer.parseInt(arrayOfStrings[i]));
                } catch (NumberFormatException nfe) {
                    results.add(null);
                }
            }
        }
        return results;
    }

    public static List<LocalDate> stringToLocalDateList(List<String> listOfDateStrings) {
        List<LocalDate> results = null;
        if (listOfDateStrings != null) {
            results = new ArrayList<LocalDate>();
            for (String dateStr : listOfDateStrings) {
                try {
                    results.add(DateUtil.toDate(dateStr));
                } catch (Exception e) {
                    results.add(null);
                }
            }
        }
        return results;
    }

    /**
     * Returns a {@link List} of {@link Integer} from a {@link String} of
     * numbers separated by <strong>comma</strong>, after removing specific
     * characters like [], before splitting by <strong>comma</strong>
     *
     * @param commaSeparatedNumbersString - {@link String}
     * @return {@link List} of {@link Integer}
     */
    public static List<Integer> toArray(String commaSeparatedNumbersString) {
        List<Integer> results = null;
        commaSeparatedNumbersString = removeExtraCharactersForNumber(commaSeparatedNumbersString, false);
        if (commaSeparatedNumbersString != null) {
            results = stringToNumberList(commaSeparatedNumbersString.split(","));
        }
        return results;
    }

    public static List<String> toArrayString(String commaSeparatedString) {
        List<String> results = null;
        String[] stringArray = null;
        if (commaSeparatedString != null) {
            stringArray = commaSeparatedString.split("\\,");
        }
        return Arrays.asList(stringArray);
    }

    /**
     * Removes specific characters like [] including commas
     *
     * @param fullStringNumber - {@link String}
     * @return {@link String} - reducedString
     */
    public static String removeExtraCharactersForNumber(String fullStringNumber) {
        return removeExtraCharactersForNumber(fullStringNumber, true);
    }

    /**
     * Returns after removing specific characters like []
     *
     * @param fullStringNumber - {@link String}
     * @param removeCommas     - {@link boolean} - <strong>true</strong> if commas
     *                         also are to be removed, else <strong>false</strong>
     * @return {@link String} - reducedString
     */
    public static String removeExtraCharactersForNumber(String fullStringNumber, boolean removeCommas) {
        String reducedString = null;
        if (fullStringNumber != null) {
            reducedString = fullStringNumber.replaceAll("[\\[\\]\\s]", "");
            if (removeCommas) {
                reducedString = reducedString.replaceAll(",", "");
            }
        }
        return reducedString;
    }

    /**
     * Returns an <strong>Array</strong> of {@link String Strings} from a
     * {@link Collection} of {@link String Strings}
     *
     * @param collectionOfStrings - {@link Collection} of {@link String Strings}
     * @return <strong>Array</strong> of {@link String Strings}
     */
    public static String[] collectionOfStringsToArrayOfStrings(Collection<String> collectionOfStrings) {
        String[] arrayOfStrings = null;
        if (collectionOfStrings != null) {
            arrayOfStrings = collectionOfStrings.stream().toArray(String[]::new);
        }
        return arrayOfStrings;
    }

    /**
     * @param listOfStrings - {@link List} of {@link String}
     * @return {@link List} of {@link Integer}
     */
    public static List<Integer> stringToNumberList(List<String> listOfStrings) {
        List<Integer> results = null;
        if (listOfStrings != null) {
            results = stringToNumberList(collectionOfStringsToArrayOfStrings(listOfStrings));
        }
        return results;
    }

    /**
     * @param setOfStrings - {@link Set} of {@link String}
     * @return {@link List} of {@link Integer}
     */
    public static List<Integer> stringToNumberList(Set<String> setOfStrings) {
        List<Integer> results = null;
        if (setOfStrings != null) {
            results = stringToNumberList(collectionOfStringsToArrayOfStrings(setOfStrings));
        }
        return results;
    }

    /**
     * @param list      {@link List} of {@link T}
     * @param batchSize - {@link int}
     * @return {@link List}<{@link List}<{@link T}>> - {@link List} of
     * {@link List} of {@link T}, in which every batch will have
     * <strong>batchSize</strong> elements, the last one might have less
     * than <strong>batchSize</strong> elements
     */
    public static <T> List<List<T>> inBatches(List<T> list, int batchSize) {
        if (list == null) {
            return null;
        }
        if (batchSize < 1) {
            batchSize = 100;
        }
        List<List<T>> batches = new ArrayList<List<T>>();
        int start = 0;
        while (start < list.size()) {
            int end = Math.min(start + batchSize, list.size());
            batches.add(list.subList(start, end));
            start = end;
        }
        return batches;
    }

    /**
     * Returns a JSON {@link String} from the <strong>obj</strong> provided
     * using {@link ObjectMapper}
     *
     * @param obj - {@link Object}
     * @return {@link String} - JSON string
     */
    public static String toJson(Object obj) {
        try {
            if (obj != null) {
                return objectMapper.writeValueAsString(obj);
            }
        } catch (JsonProcessingException e) {
            log.error("Error in toJson(), obj: " + obj + " ; Exception: " + e.getMessage());
        }
        return null;
    }

    /**
     * Returns the parsed {@link Object} from the {@link String jsonString}
     * provided using {@link ObjectMapper} - will need a type cast
     *
     * @param jsonString - {@link String}
     * @param valueType  - {@link Class}
     * @return {@link Object}
     */
    public static <T> T fromJson(String jsonString, Class<T> valueType) {
        try {
            if (jsonString != null) {
                return objectMapper.readValue(jsonString, valueType);
            }
        } catch (Exception e) {
            log.error("Error in fromJson(), jsonString: " + jsonString + " ; Exception: " + e.getMessage());
        }
        return null;
    }

    /**
     * Returns the parsed {@link Object} from the {@link String jsonString}
     * provided using {@link ObjectMapper} - will need a type cast
     *
     * @param <T>
     * @param jsonString - {@link String}
     * @param valueType  - {@link TypeReference}
     * @return {@link Object}
     */
    public static <T> Object fromJson(String jsonString, TypeReference<T> valueType) {
        try {
            if (jsonString != null) {
                return objectMapper.readValue(jsonString, valueType);
            }
        } catch (Exception e) {
            log.error("Error in fromJson(), jsonString: " + jsonString + " ; Exception: " + e.getMessage());
        }
        return null;
    }

    /**
     * @param original - <{@link T} extends {@link Serializable}>
     * @return {@link T} - a copy of the original with new reference address
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deepCopy(T original) {
        if (original == null) {
            return null;
        }
        T copy = null;
        try {
            FastByteArrayOutputStream fbos = new FastByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(fbos);
            out.writeObject(original);
            out.flush();
            out.close();
            ObjectInputStream in = new ObjectInputStream(fbos.getInputStream());
            Object obj = in.readObject();
            copy = (T) obj;
        } catch (Exception e) {
            log.error("Error in deepCopy for {}, Exception: {}", original.getClass().getName(), e.getMessage());
        }
        return copy;
    }

    /**
     * return mapping of given list according to given function
     *
     * @param list1 - {@link List} of {@link T}
     * @param fun   - {@link Function} of {@link T} and {@link U}
     * @return {@link List} of {@link U}
     */
    public static <T, U> List<U> map(List<T> list1, Function<T, U> fun) {
        if (CollectionUtils.isEmpty(list1)) {
            return null;
        }
        List<U> list2 = list1.stream().map(fun).collect(Collectors.toList());
        return list2;
    }

    public static String capitalizeFirstLetter(String str) {
        if (str != null) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return null;
    }

    /**
     * Zips two array lists into one using passed bifunction
     *
     * @param list1 - {@link List}
     * @param list2 - {@link List}
     * @param fun   - {@link BiFunction}
     * @return {@link List}
     */
    public static <T, U, V> List<V> zip(List<T> list1, List<U> list2, BiFunction<T, U, V> fun) {
        if (list1.size() != list2.size()) {
            return null;
        }
        List<V> list3 = new ArrayList<V>();
        for (int index = 0; index < list1.size(); index++) {
            T elem1 = list1.get(index);
            U elem2 = list2.get(index);
            V elem3 = fun.apply(elem1, elem2);
            list3.add(elem3);
        }
        return list3;
    }

    /**
     * @param list                    {@link List} of {@link T}
     * @param batchSize               - {@link int}
     * @param fillValue               - {@link T}
     * @param ascendingfillBatchSizes - {@link int[]}
     * @return {@link List}<{@link List}<{@link T}>> - {@link List} of
     * {@link List} of {@link T}, in which every batch will have
     * <strong>batchSize</strong> elements, the last one might have less
     * than <strong>batchSize</strong> or if {@link T fillValue} is not
     * NULL, it fills with it till the nearest limit in
     * <strong>ascendingfillBatchSizes</strong> if it's NOT NULL or
     * <strong>batchSize</strong> otherwise
     */
    public static <T> List<List<T>> inBatches(List<T> list, int batchSize, T fillValue, int[] ascendingfillBatchSizes) {
        if (list == null) {
            return null;
        }
        if (batchSize < 1) {
            batchSize = 100;
        }
        List<List<T>> batches = new ArrayList<List<T>>();
        int start = 0;
        while (start < list.size()) {
            int end = Math.min(start + batchSize, list.size());
            batches.add(list.subList(start, end));
            start = end;
        }
        if (fillValue != null && batches.size() > 0) {
            List<T> lastBatch = batches.get(batches.size() - 1);
            if (ascendingfillBatchSizes == null || ascendingfillBatchSizes.length == 0) {
                ascendingfillBatchSizes = new int[]{batchSize};
            }
            int lastBatchSize = lastBatch.size();
            for (int j = 0; j < ascendingfillBatchSizes.length; ++j) {
                int fillBatchSize = ascendingfillBatchSizes[j];
                if (lastBatchSize == fillBatchSize) {
                    break;
                } else if (lastBatchSize < fillBatchSize) {
                    List<T> fillList = new ArrayList<T>();
                    int remainingSize = fillBatchSize - lastBatchSize;
                    for (int i = 0; i < remainingSize; ++i) {
                        fillList.add(fillValue);
                    }
                    List<T> filledLastBatch = new ArrayList<T>();
                    filledLastBatch.addAll(lastBatch);
                    filledLastBatch.addAll(fillList);
                    batches.set(batches.size() - 1, filledLastBatch);
                    break;
                }
            }
        }
        return batches;
    }

    public static int getBatchSize(int noOfHotelIds, int desiredConfigSizeValue) {
        int batchSize = desiredConfigSizeValue;
        if (noOfHotelIds <= desiredConfigSizeValue) {
            batchSize = desiredConfigSizeValue / 4;
        } else if (noOfHotelIds <= desiredConfigSizeValue * 2) {
            batchSize = desiredConfigSizeValue / 2;
        }
        return batchSize;
    }

}
