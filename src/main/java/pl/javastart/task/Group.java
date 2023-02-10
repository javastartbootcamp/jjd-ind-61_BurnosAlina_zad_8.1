package pl.javastart.task;


import java.util.Arrays;

public class Group {

    private String code;
    private String name;
    private Lecturer lecturer;
    private Student[] students = new Student[10];
    private Grade[] grades = new Grade[10];

    private void printStudents() {
        for (Student student : students) {
            if (student != null) {
                System.out.println(student.getIndex() + " " + student.getFirstName() + " "
                        + student.getLastName());
            }
        }
    }

    private void addGrades(Grade grade) {
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] == null) {
                grades[i] = grade;
                break;
            }
        }
    }

    void addGrade(Grade grade) {
        if (grades[grades.length - 1] != null) {
            grades = Arrays.copyOf(grades, grades.length * 2);
        }
        addGrades(grade);
    }

    private void addStudents(Student student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                break;
            }
        }
    }

    void addStudent(Student student) {
        if (grades[grades.length - 1] != null) {
            students = Arrays.copyOf(students, students.length * 2);
        }
        addStudents(student);
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
                + lecturer.getDegree() + " " + lecturer.getFirstName() + " " +
                lecturer.getLastName() + "\nUczesticy: ");
        printStudents();
    }

    void printGrades() {
        for (Grade grade : grades) {
            if (grade != null && grade.getGrade() != 0) {
                System.out.println(grade.getStudent().getIndex() + " " +
                        grade.getStudent().getFirstName() + " " +
                        grade.getStudent().getLastName() + ": " + grade.getGrade());
            }
        }
    }

    Group(String code, String name, Lecturer lecturer) {
        this.code = code;
        this.name = name;
        this.lecturer = lecturer;
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
