package com.airbus.hackathon.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseSysLogService {

    public static String getParsedResponse(String tuple) {
        String rx1 = "^[A-Za-z]{3,6}\\s*\\d{1,2}\\s*\\d\\d:\\d\\d:\\d\\d ip\\-\\d{1,3}\\-\\d{1,3}\\-\\d{1,3}\\-\\d{1,3} [A-Za-z_-]+\\[\\d{1,100}\\]:\\s*";
        tuple = findAndReplace(tuple, rx1);
        String rx2 = "^\\s*(\\[\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\]\\s*\\[[a-zA-Z0-9_-]+\\])?\\s*";
        tuple = findAndReplace(tuple, rx2);
        return tuple;
    }

    private static String findAndReplace(String tuple, String rx) {
        Pattern ptrn = Pattern.compile(rx);
        Matcher m = ptrn.matcher(tuple);
        if (m.find()) {
            tuple = tuple.replace(m.group(0), "");
        }
        return tuple;
    }
}
