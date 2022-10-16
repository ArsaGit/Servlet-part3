package com.example.servlet;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.lang.System.out;

public class Item {
    private String path;
    private String name;
    private String size;
    private String date;
    private boolean isDirectory;

    public Item(Path path) {
        this.path = path.toString().replace('\\', '/');
        name = path.getFileName().toString();
        try {
            size = FileUtils.byteCountToDisplaySize(Files.size(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            date = Files.getLastModifiedTime(path)
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        isDirectory = Files.isDirectory(path);
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getDate() {
        return date;
    }

    public String getPath() {
        return path;
    }

    public boolean isDirectory() {
        return isDirectory;
    }
}
