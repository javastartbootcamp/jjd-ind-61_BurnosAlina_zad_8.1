package pl.javastart.task;

import java.util.Arrays;

public class UniversityApp {

    private Lecturer[] lecturers = new Lecturer[10];
    private Group[] groups = new Group[10];
    private int groupCounter = 0;
    private Student[] students = new Student[10];
    private Grade[] grades = new Grade[10];

    /**
     * Tworzy prowadzącego zajęcia.
     * W przypadku gdy prowadzący z zadanym id już istnieje, wyświetlany jest komunikat:
     * "Prowadzący z id [id_prowadzacego] już istnieje"
     *
     * @param id        - unikalny identyfikator prowadzącego
     * @param degree    - stopień naukowy prowadzącego
     * @param firstName - imię prowadzącego
     * @param lastName  - nazwisko prowadzącego
     */

    public void createLecturer(int id, String degree, String firstName, String lastName) {
        if (findLecturer(id) != null) {
            System.out.println("Prowadzący z id " + id + " już istnieje");
        } else {
            if (lecturers[lecturers.length - 1] != null) {
                lecturers = Arrays.copyOf(lecturers, lecturers.length * 2);
            }
            addLecturerToLecturerList(id, degree, firstName, lastName);
        }
    }

    void addLecturerToLecturerList(int id, String degree, String firstName, String lastName) {
        for (int i = 0; i < lecturers.length; i++) {
            if (lecturers[i] == null) {
                Lecturer lecturer = new Lecturer(id, degree, firstName, lastName);
                lecturers[i] = lecturer;
                break;
            }
        }
    }

    Lecturer findLecturer(int lecturerId) {
        for (Lecturer lecturer : lecturers) {
            if (lecturer != null && lecturerId == lecturer.getId()) {
                return lecturer;
            }
        }
        return null;
    }

    Group findGroup(String groupCode) {
        for (Group group : groups) {
            if (group != null && groupCode.equals(group.getCode())) {
                return group;
            }
        }
        return null;
    }

    /**
     * Tworzy grupę zajęciową.
     * W przypadku gdy grupa z zadanym kodem już istnieje, wyświetla się komunikat:
     * "Grupa [kod grupy] już istnieje"
     * W przypadku gdy prowadzący ze wskazanym id nie istnieje wyświetla się komunikat:
     * "Prowadzący o id [id prowadzacego] nie istnieje"
     *
     * @param code       - unikalny kod grupy
     * @param name       - nazwa przedmiotu (np. "Podstawy programowania")
     * @param lecturerId - identyfikator prowadzącego. Musi zostać wcześniej utworzony za pomocą metody {@link #createLecturer(int, String, String, String)}
     */

    public void createGroup(String code, String name, int lecturerId) {
        Lecturer lecturer = findLecturer(lecturerId);
        if (findGroup(code) != null) {
            System.out.println("Grupa " + code + " już istnieje");
            return;
        }

        if (lecturer == null) {
            System.out.println("Prowadzący o id " + lecturerId + " nie istnieje");
            return;
        }

        if (groups[groups.length - 1] != null) {
            groups = Arrays.copyOf(groups, groups.length * 2);
        }
        addGroupToGroupList(code, name, lecturer);
    }

    private void addGroupToGroupList(String code, String name, Lecturer lecturer) {
        Group group = new Group(code, name, lecturer);
        groups[groupCounter] = group;
        groupCounter++;
    }

    /**
     * Dodaje studenta do grupy zajęciowej.
     * W przypadku gdy grupa zajęciowa nie istnieje wyświetlany jest komunikat:
     * "Grupa [kod grupy] nie istnieje
     *
     * @param index     - unikalny numer indeksu studenta
     * @param groupCode - kod grupy utworzonej wcześniej za pomocą {@link #createGroup(String, String, int)}
     * @param firstName - imię studenta
     * @param lastName  - nazwisko studenta
     */

    public void addStudentToGroup(int index, String groupCode, String firstName, String lastName) {
        Group group = findGroup(groupCode);
        Student student = findStudent(index);
        if (group == null) {
            System.out.println("Grupa " + groupCode + " nie istnieje");
        } else if (student == null) {
            student = new Student(index, firstName, lastName);
            group.addStudent(student);
            addStudentToStudentList(student);
            student.addGroup(group);
        } else if (!group.isStudentInTheGroup(student)) {
            group.addStudent(student);
            student.addGroup(group);
        } else if (group.isStudentInTheGroup(student)) {
            System.out.println("Student o indeksie " + index + " jest już w grupie " + groupCode);
        }
    }

    private void ensureStudentsCapacity() {
        if (students[students.length - 1] != null) {
            students = Arrays.copyOf(students, students.length * 2);
        }
    }

    private void addStudentToStudentList(Student student) {
        ensureStudentsCapacity();
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                break;
            }
        }
    }

    /**
     * Wyświetla informacje o grupie w zadanym formacie.
     * Oczekiwany format:
     * Kod: [kod_grupy]
     * Nazwa: [nazwa przedmiotu]
     * Prowadzący: [stopień naukowy] [imię] [nazwisko]
     * Uczestnicy:
     * [nr indeksu] [imie] [nazwisko]
     * [nr indeksu] [imie] [nazwisko]
     * [nr indeksu] [imie] [nazwisko]
     * W przypadku gdy grupa nie istnieje, wyświetlany jest komunikat w postaci: "Grupa [kod] nie znaleziona"
     *
     * @param groupCode - kod grupy, dla której wyświetlić informacje
     */

    public void printGroupInfo(String groupCode) {
        Group group = findGroup(groupCode);
        if (group == null) {
            System.out.println("Grupa " + groupCode + " nie znaleziona");
        } else {
            group.printInfo();
        }
    }

    /**
     * Dodaje ocenę końcową dla wskazanego studenta i grupy.
     * Student musi być wcześniej zapisany do grupy za pomocą {@link #addStudentToGroup(int, String, String, String)}
     * W przypadku, gdy grupa o wskazanym kodzie nie istnieje, wyświetlany jest komunikat postaci:
     * "Grupa pp-2022 nie istnieje"
     * W przypadku gdy student nie jest zapisany do grupy, wyświetlany jest komunikat w
     * postaci: "Student o indeksie 179128 nie jest zapisany do grupy pp-2022"
     * W przypadku gdy ocena końcowa już istnieje, wyświetlany jest komunikat w postaci:
     * "Student o indeksie 179128 ma już wystawioną ocenę dla grupy pp-2022"
     *
     * @param studentIndex - numer indeksu studenta
     * @param groupCode    - kod grupy
     * @param grade        - ocena
     */

    public void addGrade(int studentIndex, String groupCode, double grade) {
        Group group = findGroup(groupCode);
        Student student = findStudent(studentIndex);
        if (group == null) {
            System.out.println("Grupa " + groupCode + " nie istnieje");
        } else if (!group.isStudentInTheGroup(student)) {
            System.out.println("Student o indeksie " + studentIndex + " nie jest zapisany do grupy " + groupCode);
        } else if (isGradeAlreadyExist(student, group)) {
            System.out.println("Student o indeksie " + studentIndex + " ma już wystawioną ocenę dla grupy " + groupCode);
        } else if (student != null) {
            Grade grade1 = new Grade(grade, student, group);
            addGradeToGradeList(grade1);
            student.addGrade(grade1);
            group.addGrade(grade1);
        }
    }

    private void ensureGradesCapacity() {
        if (grades[grades.length - 1] != null) {
            grades = Arrays.copyOf(grades, grades.length * 2);
        }
    }

    private void addGradeToGradeList(Grade grade) {
        ensureGradesCapacity();
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] == null) {
                grades[i] = grade;
                break;
            }
        }
    }

    private boolean isGradeAlreadyExist(Student student, Group group) {
        for (Grade grade : grades) {
            if (grade != null && grade.getGroup() == group && grade.getStudent() == student) {
                return true;
            }
        }
        return false;
    }

    private Student findStudent(int index) {
        for (Student student : students) {
            if (student != null && student.getIndex() == index) {
                return student;
            }
        }
        return null;
    }

    /**
     * Wyświetla wszystkie oceny studenta.
     * Przykładowy wydruk:
     * Podstawy programowania: 5.0
     * Programowanie obiektowe: 5.5
     *
     * @param index - numer indesku studenta dla którego wyświetlić oceny
     */

    public void printGradesForStudent(int index) {
        Student student = findStudent(index);
        if (student != null) {
            student.printGrades();
        }
    }

    /**
     * Wyświetla oceny studentów dla wskazanej grupy.
     * Przykładowy wydruk:
     * 179128 Marcin Abacki: 5.0
     * 179234 Dawid Donald: 4.5
     * 189521 Anna Kowalska: 5.5
     *
     * @param groupCode - kod grupy, dla której wyświetlić oceny
     */

    public void printGradesForGroup(String groupCode) {
        Group group = findGroup(groupCode);
        if (group != null) {
            group.printGrades();
        } else {
            System.out.println("Grupa " + groupCode + " nie istnieje");
        }
    }

    /**
     * Wyświetla wszystkich studentów. Każdy student powinien zostać wyświetlony tylko raz.
     * Każdy student drukowany jest w nowej linii w formacie [nr_indesku] [imie] [nazwisko]
     * Przykładowy wydruk:
     * 179128 Marcin Abacki
     * 179234 Dawid Donald
     * 189521 Anna Kowalska
     */

    public void printAllStudents() {
        for (Student student : students) {
            if (student != null) {
                System.out.println(student.getInfo());
            }
        }
    }
}
