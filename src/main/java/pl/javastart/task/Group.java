package pl.javastart.task;

import java.util.Arrays;

public class Group {

    private String code;
    private String name;
    private Lecturer lecturer;
    private Student[] students = new Student[10];
    private Grade[] grades = new Grade[10];

    Group(String code, String name, Lecturer lecturer) {
        this.code = code;
        this.name = name;
        this.lecturer = lecturer;
    }

    private void printStudents() {
        for (Student student : students) {
            if (student != null) {
                System.out.println(student.getInfo());
            }
        }
    }

    private void ensureGradesCapacity() {
        if (grades[grades.length - 1] != null) {
            grades = Arrays.copyOf(grades, grades.length * 2);
        }
    }

    void addGrade(Grade grade) {
        ensureGradesCapacity();
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] == null) {
                grades[i] = grade;
                break;
            }
        }
    }

    private void ensureStudentsCapacity() {
        if (grades[grades.length - 1] != null) {
            students = Arrays.copyOf(students, students.length * 2);
        }
    }

    void addStudent(Student student) {
        ensureStudentsCapacity();
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                break;
            }
        }
    }

    boolean isStudentInTheGroup(Student student) {
        for (Student value : students) {
            if (value != null && value == student) {
                return true;
            }
        }
        return false;
    }

    void printInfo() {
        System.out.println("Kod: " + code + "\nNazwa: " + name + "\nProwadzący: "
                + lecturer.getInfo() + "\nUczesticy: ");
        printStudents();
    }

    void printGrades() {
        for (Grade grade : grades) {
            if (grade != null && grade.getGrade() != 0) {
                Student student = grade.getStudent();
                System.out.println(student.getInfo() + ": " + grade.getGrade());
            }
        }
    }

    String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    Lecturer getLecturer() {
        return lecturer;
    }

    void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    Student[] getStudents() {
        return students;
    }

    void setStudents(Student[] students) {
        this.students = students;
    }

    Grade[] getGrades() {
        return grades;
    }

    void setGrades(Grade[] grades) {
        this.grades = grades;
    }
}
