package com.wangtao.sharding.util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author wangtao
 * Created at 2023/9/7 20:37
 */
public final class DateUtils {

    private static final DateTimeFormatter STANDARD_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter SHORT_DATE = DateTimeFormatter.ofPattern("yyyyMMdd");

    private DateUtils() {

    }

    public static LocalDate parseDate(String dataStr) {
        if (StringUtils.isBlank(dataStr)) {
            return null;
        }
        return LocalDate.parse(dataStr, STANDARD_DATE);
    }

    public static LocalDate parseIntegerDate(Integer date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return LocalDate.parse(String.valueOf(date), SHORT_DATE);
    }

    public static LocalDate parseIntegerDate(String dataStr) {
        if (StringUtils.isBlank(dataStr)) {
            return null;
        }
        return LocalDate.parse(dataStr, SHORT_DATE);
    }

    public static String formatInteger(LocalDate date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return date.format(SHORT_DATE);
    }
}
