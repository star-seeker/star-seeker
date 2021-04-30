package com.goose.utils;

import com.goose.enums.DurationUnit;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQuery;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

/**
 * @author zhangyoubao
 * @version 2020/11/16
 * 基于java8线程安全的日期时间工具类
 */
public class DateTimeUtil {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    enum QuarterEnum {
        FIRST, SECOND, THIRD, FOURTH
    }
    public enum FirstDayOfWeek {
        SUNDAY,
        MONDAY
    }
    static class QuarterOfYearQuery implements TemporalQuery<QuarterEnum> {
        @Override
        public QuarterEnum queryFrom(TemporalAccessor temporal) {
            LocalDate now = LocalDate.from(temporal);
            if (now.isBefore(now.with(Month.APRIL).withDayOfMonth(1))) {
                return QuarterEnum.FIRST;
            } else if (now.isBefore(now.with(Month.JULY).withDayOfMonth(1))) {
                return QuarterEnum.SECOND;
            } else if (now.isBefore(now.with(Month.OCTOBER).withDayOfMonth(1))) {
                return QuarterEnum.THIRD;
            } else {
                return QuarterEnum.FOURTH;
            }
        }
    }

    /**
     * 获取当前的年份
     */
    public static int getYear() {
        return LocalDate.now().getYear();
    }

    /**
     * 获取当前的月份, 1-12
     */
    public static int getMonth() {
        return LocalDate.now().getMonthValue();
    }

    /**
     * 获取当前为本月的第几天, 1-31
     */
    public static int getDayOfMonth() {
        return LocalDate.now().getDayOfMonth();
    }

    /**
     * 获取当前为本年的第几天, 1-366
     */
    public static int getDayOfYear() {
        return LocalDate.now().getDayOfYear();
    }

    /**
     * 格式化日期(带时分秒), Date to String with time
     */
    public static String formatWithTime(Date date, String pattern) {
        return date2LocalDateTime(date).format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化日期(不带时分秒), Date to String no time
     */
    public static String formatNoTime(Date date, String pattern) {
        return date2LocalDate(date).format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化日期(带时分秒), String to Date with time
     */
    public static Date parseWithTime(String dateStr, String pattern) {
        return localDateTime2Date(LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern)));
    }

    /**
     * 格式化日期(不带时分秒), String to Date no time
     */
    public static Date parseNoTime(String dateStr, String pattern) {
        return localDate2Date(LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern)));
    }

    /**
     * 类型转换: LocalDate to Date
     */
    public static Date localDate2Date(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 类型转换: Date to LocalDate
     */
    public static LocalDate date2LocalDate(Date date) {
        return date2LocalDateTime(date).toLocalDate();
    }

    /**
     * 类型转换: LocalDateTime to Date
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 类型转换: Date to LocalDateTime
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 增加时间, 间隔为 interval, 单位为 timeUnit
     * 如果需要减少时间, 则 interval传负值即可
     */
    public static Date addInterval(Date date, long interval, ChronoUnit timeUnit) {
        if (interval == 0) {
            return date;
        }
        return localDateTime2Date(date2LocalDateTime(date).plus(interval, timeUnit));
    }

    /**
     * 获取当天的起始时间
     */
    public static Date getStartTime() {
        return localDateTime2Date(LocalDate.now().atStartOfDay());
    }

    /**
     * 获取给定日期的起始时间
     */
    public static Date getSpecificStartTime(Date date) {
        return localDateTime2Date(date2LocalDate(date).atStartOfDay());
    }

    /**
     * 获取当天的结束时间
     */
    public static Date getEndTime() {
        return localDateTime2Date(LocalDateTime.now().with(LocalTime.MAX));
    }

    /**
     * 获取给定日期的结束时间
     */
    public static Date getSpecificEndTime(Date date) {
        return localDateTime2Date(date2LocalDate(date).atTime(23, 59, 59, 999));
    }

    /**
     * 获取给定日期所在周/月/季度/年的第一天
     */
    public static Date firstDayOfDuration(Date date, DurationUnit durationUnit) {
        switch (durationUnit) {
            case WEEK:
                return localDate2Date(LocalDate.now().with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1));
            case MONTH:
                return localDate2Date(date2LocalDate(date).with(TemporalAdjusters.firstDayOfMonth()));
            case Quarter:
                int quarter = getQuarter(date);
                int year = date2LocalDate(date).getYear();
                switch (quarter) {
                    case 1:
                        return localDate2Date(LocalDate.of(year, 1, 1));
                    case 2:
                        return localDate2Date(LocalDate.of(year, 4, 1));
                    case 3:
                        return localDate2Date(LocalDate.of(year, 7, 1));
                    case 4:
                        return localDate2Date(LocalDate.of(year, 10, 1));
                    default:
                        throw new RuntimeException("quarter range [1-4]");
                }
            case YEAR:
                return localDate2Date(date2LocalDate(date).with(TemporalAdjusters.firstDayOfYear()));
            default:
                throw new RuntimeException("wrong duration unit " + durationUnit.getName());
        }
    }

    /**
     * 获取给定日期所在周/月/季度/年的最后一天
     */
    public static Date lastDayOfDuration(Date date, DurationUnit durationUnit) {
        switch (durationUnit) {
            case WEEK:
                return localDate2Date(LocalDate.now().with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 7));
            case MONTH:
                return localDate2Date(date2LocalDate(date).with(TemporalAdjusters.lastDayOfMonth()));
            case Quarter:
                int quarter = getQuarter(date);
                int year = date2LocalDate(date).getYear();
                switch (quarter) {
                    case 1:
                        return localDate2Date(LocalDate.of(year, 3, 31));
                    case 2:
                        return localDate2Date(LocalDate.of(year, 6, 30));
                    case 3:
                        return localDate2Date(LocalDate.of(year, 9, 30));
                    case 4:
                        return localDate2Date(LocalDate.of(year, 12, 31));
                    default:
                        throw new RuntimeException("quarter range [1-4]");
                }
            case YEAR:
                return localDate2Date(date2LocalDate(date).with(TemporalAdjusters.lastDayOfYear()));
            default:
                throw new RuntimeException("wrong duration unit " + durationUnit.getName());
        }
    }

    private static int getQuarter(Date date) {
        LocalDate localDate = date2LocalDate(date);
        TemporalQuery<QuarterEnum> quarterOfYearQuery = new QuarterOfYearQuery();
        QuarterEnum quarter = localDate.query(quarterOfYearQuery);
        switch (quarter) {
            case FIRST:
                return 1;
            case SECOND:
                return 2;
            case THIRD:
                return 3;
            case FOURTH:
                return 4;
        }
        return 0;
    }

    /**
     * 计算两个时间的差值
     * @param beginDate 起始时间
     * @param endDate   结束时间
     * @param timeUnit  差值单位, 如毫秒/秒/分钟/小时
     */
    public static long calDiff(Date beginDate, Date endDate, ChronoUnit timeUnit) {
        return timeUnit.between(date2LocalDateTime(beginDate).atZone(ZoneId.systemDefault()).toInstant(),
                date2LocalDateTime(endDate).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 计算给定日期所在周的第一天
     * @param date  给定日期
     * @param firstDayOfWeek  周第一天 SUNDAY 或 MONDAY
     */
    public static Date firstDayOfWeek(Date date, FirstDayOfWeek firstDayOfWeek) {
        if (firstDayOfWeek == FirstDayOfWeek.SUNDAY) {
            return localDate2Date(date2LocalDate(date).with(WeekFields.of(Locale.US).dayOfWeek(), 1));
        } else if (firstDayOfWeek == FirstDayOfWeek.MONDAY) {
            return localDate2Date(date2LocalDate(date).with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1));
        } else {
            throw new RuntimeException("wrong param " + firstDayOfWeek);
        }
    }

    /**
     * 计算给定日期所在周的最后一天
     * @param date  给定日期
     * @param firstDayOfWeek  周第一天 SUNDAY 或 MONDAY
     */
    public static Date lastDayOfWeek(Date date, FirstDayOfWeek firstDayOfWeek) {
        if (firstDayOfWeek == FirstDayOfWeek.SUNDAY) {
            return localDate2Date(date2LocalDate(date).with(WeekFields.of(Locale.US).dayOfWeek(), 7));
        } else if (firstDayOfWeek == FirstDayOfWeek.MONDAY) {
            return localDate2Date(date2LocalDate(date).with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 7));
        } else {
            throw new RuntimeException("wrong param " + firstDayOfWeek);
        }
    }

    /**
     * 获取当前时间的标准格式化
     */
    public static String standardNowTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT));
    }

    /**
     * 获取当前日期的标准格式化
     */
    public static String standardNowDay() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
    }
}
