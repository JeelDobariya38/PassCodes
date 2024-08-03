package com.jeeldobariya.passcodes.utils;

import android.util.Log;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static final String TAG = "PassCodes";
    private static boolean writeToLogFile = false;
    private static File logFile = null;

    public static void init(File logFile, boolean writeToFile) {
        Logger.logFile = logFile;
        Logger.writeToLogFile = writeToFile;
    }

    public static void info(String tag, String message) {
        Log.i(tag, message);
        writeToFile("INFO", tag, message);
    }

    public static void warn(String tag, String message) {
        Log.w(tag, message);
        writeToFile("WARN", tag, message);
    }

    public static void error(String tag, String message) {
        Log.e(tag, message);
        writeToFile("ERROR", tag, message);
    }

    private static void writeToFile(String level, String tag, String message) {
        if (writeToLogFile && logFile != null) {
            try (FileWriter writer = new FileWriter(logFile, true)) {
                writer.append(level).append("/").append(tag).append(": ").append(message).append("\n");
            } catch (IOException e) {
                Log.e(TAG, "Error writing log to file", e);
            }
        }
    }
}
