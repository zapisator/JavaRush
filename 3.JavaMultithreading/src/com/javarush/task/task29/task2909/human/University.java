package com.javarush.task.task29.task2909.human;

import java.util.LinkedList;
import java.util.List;

public class University {

    private String name;
    private int age;
    private List<Student> students = new LinkedList<>();

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        return students.stream()
                .filter(student -> student.getAverageGrade() == averageGrade)
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public Student getStudentWithMaxAverageGrade() {
        return getStudentWithAverageGrade(
                students.stream()
                        .map(Student::getAverageGrade)
                        .max(Double::compareTo)
                        .orElseThrow(RuntimeException::new)
        );
    }

    public Student getStudentWithMinAverageGrade() {
        return getStudentWithAverageGrade(
                students.stream()
                        .map(Student::getAverageGrade)
                        .min(Double::compareTo)
                        .orElseThrow(RuntimeException::new)
        );
    }

    public void expel(Student student) {
        students.removeIf(student::equals);
    }
}