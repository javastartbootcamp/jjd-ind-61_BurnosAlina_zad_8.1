package pl.javastart.task;

public class Student {
    private int index;
    private String firstName;
    private String lastName;
    private Grade[] grades = new Grade[10];
    private Group[] groups = new Group[10];

    Student(int index, String firstName, String lastName) {
        this.index = index;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    int getIndex() {
        return index;
    }

    void setIndex(int index) {
        this.index = index;
    }

    String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    Grade[] getGrades() {
        return grades;
    }

    void setGrades(Grade[] grades) {
        this.grades = grades;
    }

    Group[] getGroups() {
        return groups;
    }

    void setGroups(Group[] groups) {
        this.groups = groups;
    }
}
