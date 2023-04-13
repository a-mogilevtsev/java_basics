package com.skillbox.mongodemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by a.sosnina on 3/28/2022.
 */
public class CsvFileReader {
    private String path;
    public CsvFileReader(String path) {
        this.path = path;
    }

    public List<Student> parseFile() {
        List<Student> students = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while (br.ready()) {
                String line = br.readLine();
                String[] lineParts = line.split(",");
                List<String> courses = new ArrayList<>();
                if(lineParts.length > 2) {
                   for(int i = 2; i < lineParts.length; i++)
                       courses.add(lineParts[i]);
                }
                students.add(new Student(lineParts[0], Integer.parseInt(lineParts[1]), courses));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<String> parseCourses(String line) {
        List<String> courses = new ArrayList<>();
        String[] lineParts = line.split(",");
        Collections.addAll(courses, lineParts);
        return courses;
    }
}
