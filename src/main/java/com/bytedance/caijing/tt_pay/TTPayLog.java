package com.bytedance.caijing.tt_pay;

// TODO 日志优化:1.日志打印时间，2.性能优化
public class TTPayLog {
    public static volatile LogLevel logLevel;

    public static void debug(String caller, String log) {
        if (LogLevel.debug.compareTo(logLevel) >= 0) {
            System.out.println(caller + " debug " + log);
        }
    }

    public static void info(String caller, String log) {
        if (LogLevel.info.compareTo(logLevel) >= 0) {
            System.out.println(caller + " info " + log);
        }
    }

    public static void warn(String caller, String log) {
        if (LogLevel.warn.compareTo(logLevel) >= 0) {
            System.out.println(caller + " warn " + log);
        }
    }

    public static void error(String caller, String log) {
        if (LogLevel.error.compareTo(logLevel) >= 0) {
            System.out.println(caller + " error " + log);
        }
    }

    public enum LogLevel {
        debug,
        info,
        warn,
        error
    }
}

