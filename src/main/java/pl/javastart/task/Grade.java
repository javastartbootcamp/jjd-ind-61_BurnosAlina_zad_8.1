package pl.javastart.task;

public class Grade {

    private double grade;
    private Student student;
    private Group group;

    Grade(double grades, Student student, Group group) {
        this.grade = grades;
        this.student = student;
        this.group = group;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
