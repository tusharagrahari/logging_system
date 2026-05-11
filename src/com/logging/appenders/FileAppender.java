package com.logging.appenders;

import com.logging.core.Appender;
import com.logging.core.LogMessage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements Appender {
    private final String filePath;
    private final BufferedWriter writer;

    public FileAppender(String filePath) throws IOException {
        this.filePath = filePath;
        try {
            writer = new BufferedWriter(new FileWriter(filePath, true));
        } catch(IOException e) {
            throw new RuntimeException("Failed to open log file: " + filePath, e);
        }
    }
    @Override
    public synchronized void write(LogMessage message) {
        try {
            writer.write(message.getFormattedMessage());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }

    public synchronized void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("Failed to close log file: " + e.getMessage());
        }
    }
}
