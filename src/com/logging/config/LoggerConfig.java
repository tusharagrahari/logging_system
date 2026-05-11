package com.logging.config;

import com.logging.core.Appender;
import com.logging.core.LogLevel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LoggerConfig {
    private LogLevel minimumLevel;
    private List<Appender> appenders;

    public LoggerConfig(LogLevel minimumLevel){
        this.minimumLevel = minimumLevel;
        this.appenders = new ArrayList<>();
    }

    public void setMinimumLevel(LogLevel minimumLevel){
        this.minimumLevel = minimumLevel;
    }

    public LogLevel getMinimumLevel() {
        return minimumLevel;
    }

    public void addAppender(Appender appender) {
        appenders.add(appender);
    }

    public List<Appender> getAppenders() {
        return Collections.unmodifiableList(appenders);
    }
}
