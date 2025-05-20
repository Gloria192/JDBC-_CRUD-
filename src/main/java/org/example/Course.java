package org.example;

public class Course {
    private String Name;
    private String Description;

    public Course(String name, String description) {
        Name = name;
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "Course{" +
                "Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
