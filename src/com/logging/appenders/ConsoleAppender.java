package com.logging.appenders;

import com.logging.core.Appender;
import com.logging.core.LogLevel;
import com.logging.core.LogMessage;

public class ConsoleAppender implements Appender {
    @Override
    public void write(LogMessage message){
        if(message.getLevel() == LogLevel.ERROR || message.getLevel() == LogLevel.FATAL){
            System.err.println(message.getFormattedMessage());
        } else {
            System.out.println(message.getFormattedMessage());
        }
    }
}
