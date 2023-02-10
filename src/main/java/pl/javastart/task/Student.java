package pl.javastart.task;

import java.util.Arrays;

public class Student {
    private int index;
    private String firstName;
    private String lastName;
    private Grade[] grades = new Grade[10];
    private Group[] groups = new Group[10];

    public void printGrades() {
        for (Grade grade : grades) {
            if (grade != null && grade.getGrade() != 0) {
                System.out.println(grade.getGroup().getName() + ": " +
                        grade.getGrade());
            }
        }
    }

    private void addGroups(Group group) {
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] == null) {
                groups[i] = group;
                break;
            }
        }
    }

    void addGroup(Group group) {
        if (groups[groups.length - 1] != null) {
            groups = Arrays.copyOf(groups, groups.length * 2);
        }
        addGroups(group);
    }

    private void addGrades(Grade grade) {
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] == null) {
                grades[i] = grade;
                break;
            }
        }
    }

    void printInfo() {
        System.out.println(index + " " + firstName + " " + lastName);
    }

    void addGrade(Grade grade) {
        if (grades[grades.length - 1] != null) {
            groups = Arrays.copyOf(groups, groups.length * 2);
        }
        addGrades(grade);
    }

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
