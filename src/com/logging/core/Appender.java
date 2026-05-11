package com.logging.core;

public interface Appender {
    void write(LogMessage message);
}
