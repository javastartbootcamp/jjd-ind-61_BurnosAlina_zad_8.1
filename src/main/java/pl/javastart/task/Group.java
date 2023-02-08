package pl.javastart.task;

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
