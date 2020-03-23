package com.chen.bookmanager.model;


public class Student {

    private String name;
    private String id;

    private String classId;

    public Student(){}
    public Student(String name) {
        System.out.println("创建学生");
        this.name = name;
    }

    public void setMyName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", classId='" + classId + '\'' +
                '}';
    }
}
