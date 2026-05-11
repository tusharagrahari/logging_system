package com.logging.core;

import com.logging.config.LoggerConfig;

public class Logger {
    private static Logger instance = null;
    private LoggerConfig config;

    private Logger() {}

    public static synchronized Logger getInstance() {
        if (instance==null){
            instance = new Logger();
        }

        return instance;
    }

    public synchronized void configure(LoggerConfig config) {
        this.config = config;
    }

    public synchronized void log(LogLevel level, String message){
        if(config==null){
            System.err.println("Logger not configured");
            return;
        }

        if(level.getPriority()<config.getMinimumLevel().getPriority()){
            return;
        }

        LogMessage logMessage = new LogMessage(level, message);

        for(Appender appender : config.getAppenders()){
            appender.write(logMessage);
        }
    }

    public synchronized void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public synchronized void info(String message) {
        log(LogLevel.INFO, message);
    }

    public synchronized void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    public synchronized void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public synchronized void fatal(String message) {
        log(LogLevel.FATAL, message);
    }
}
