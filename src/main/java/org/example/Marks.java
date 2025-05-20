package org.example;

public class Marks {
    private  int Student_Id;
    private  int Course_Id;
    private  float Marks;

    public Marks(int student_Id, int course_Id, float marks) {
        Student_Id = student_Id;
        Course_Id = course_Id;
        Marks = marks;
    }

    public int getStudent_Id() {
        return Student_Id;
    }

    public int getCourse_Id() {
        return Course_Id;
    }

    public float getMarks() {
        return Marks;
    }

    public void setStudent_Id(int student_Id) {
        Student_Id = student_Id;
    }

    public void setCourse_Id(int course_Id) {
        Course_Id = course_Id;
    }

    public void setMarks(float marks) {
        Marks = marks;
    }

    @Override
    public String toString() {
        return "Marks{" +
                "Student_Id=" + Student_Id +
                ", Course_Id=" + Course_Id +
                ", Marks=" + Marks +
                '}';
    }
}
