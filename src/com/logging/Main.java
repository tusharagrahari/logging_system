package com.logging;

import com.logging.appenders.ConsoleAppender;
import com.logging.appenders.FileAppender;
import com.logging.config.LoggerConfig;
import com.logging.core.LogLevel;
import com.logging.core.Logger;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("========================================");
        System.out.println("         LOGGING FRAMEWORK DEMO         ");
        System.out.println("========================================\n");

        // ── Step 1: Build the config ──────────────────────────────
        LoggerConfig config = new LoggerConfig(LogLevel.DEBUG);

        // ── Step 2: Create and register appenders ─────────────────
        ConsoleAppender consoleAppender = new ConsoleAppender();
        FileAppender fileAppender = new FileAppender("app.log");

        config.addAppender(consoleAppender);
        config.addAppender(fileAppender);

        // ── Step 3: Configure the Logger ──────────────────────────
        Logger logger = Logger.getInstance();
        logger.configure(config);

        // ── Step 4: Fire logs at all levels ───────────────────────
        System.out.println("\n--- Logging at all levels (min: DEBUG) ---\n");
        logger.debug("Initializing payment service");
        logger.info("Server started on port 8080");
        logger.warning("High memory usage detected: 85%");
        logger.error("Payment failed for user ID 1234");
        logger.fatal("Database connection lost — system shutting down");

        // ── Step 5: Demonstrate level filtering ───────────────────
        System.out.println("\n--- Changing minimum level to ERROR ---\n");
        config.setMinimumLevel(LogLevel.ERROR);

        logger.debug("This DEBUG should be filtered out");
        logger.info("This INFO should be filtered out");
        logger.warning("This WARNING should be filtered out");
        logger.error("This ERROR should pass through");
        logger.fatal("This FATAL should pass through");

        // ── Step 6: Demonstrate thread safety ─────────────────────
        System.out.println("\n--- Thread safety demo ---\n");
        config.setMinimumLevel(LogLevel.DEBUG);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                logger.info("Thread 1 — log entry " + i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                logger.info("Thread 2 — log entry " + i);
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            logger.error("Thread interrupted: " + e.getMessage());
        }

        // ── Step 8: Clean up ──────────────────────────────────────
        fileAppender.close();
        System.out.println("\n========================================");
        System.out.println("              DEMO COMPLETE             ");
        System.out.println("========================================");
    }
}
