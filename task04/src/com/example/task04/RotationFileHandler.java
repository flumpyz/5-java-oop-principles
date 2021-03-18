package com.example.task04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;

public class RotationFileHandler extends Logger
        implements MessageHandler {

    private Duration duration;

    public RotationFileHandler(String name, Duration rotation) {
        super(name);
        this.duration = rotation;
    }

    public void setDuration(Duration duration) { this.duration = duration; }

    public Duration getDuration() { return duration; }

    @Override
    public void log(Level logLevel, String message) {
        File file = new File("D://projects", String.format("RotationFileHandlerLogs %s.txt", duration.toHours()));
        try {
            FileWriter writer = new FileWriter(file, true);
            Date now = new Date();
            SimpleDateFormat nowFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
            writer.write("[" + logLevel + "] " + nowFormat.format(now) + " " + getName() + " - " + message + "\n");

            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void log(Level logLevel, String template, Object... args) {
        log(logLevel, String.format(template, args));
    }

}
