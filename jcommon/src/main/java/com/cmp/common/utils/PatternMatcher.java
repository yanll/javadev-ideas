package com.cmp.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by YLL on 2015/07/09.
 */
public class PatternMatcher {

    public static List<String> extract(String txt, String regex) throws IOException {
        Pattern pattern = Pattern.compile(regex);
        List<String> urls = new ArrayList<String>();
        Matcher matcher = pattern.matcher(txt);
        String matchecText = null;
        while (matcher.find()) {
            matchecText = matcher.group();
            if (!urls.contains(matchecText)) {
                urls.add(matchecText);
            }
        }
        return urls;
    }

    public static boolean matches(String txt, String regex) throws IOException {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(txt);
        return matcher.matches();
    }
}
