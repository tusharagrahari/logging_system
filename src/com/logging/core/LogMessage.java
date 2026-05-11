package com.logging.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogMessage {
    private LogLevel level;
    private LocalDateTime time;
    private String content;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LogMessage(LogLevel level, String content) {
        this.content = content;
        this.level = level;
        this.time = LocalDateTime.now();
    }

    public String getContent() {
        return content;
    }

    public LogLevel getLevel() {
        return level;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getFormattedMessage() {
        return "[" + time.format(formatter) + "]" + "[" + level.name() + "]" + content;
    }
}
