package org.example;

public class Course {
private int id;
    private String Name;
    private String Description;

    public Course(int id,String name, String description) {
        this.id = id;
        this.Name = name;
        this.Description = description;

    }
public int getId() {
        return id;
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
