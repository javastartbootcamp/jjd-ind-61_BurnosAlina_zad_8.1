package pl.javastart.task;

public class UniversityApp {

    private Lecturer[] lecturers = new Lecturer[10];
    private Group[] groups = new Group[10];
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
            for (int i = 0; i < lecturers.length; i++) {
                if (lecturers[i] == null) {
                    Lecturer lecturer = new Lecturer(id, degree, firstName, lastName);
                    lecturers[i] = lecturer;
                    break;
                }
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
        } else if (lecturer == null) {
            System.out.println("Prowadzący o id " + lecturerId + " nie istnieje");
        } else {
            for (int i = 0; i < groups.length; i++) {
                if (groups[i] == null) {
                    Group group = new Group(code, name, lecturer);
                    groups[i] = group;
                    break;
                }
            }
        }
    }

    private void addGroupToStudent(Group group, Student student) {
        for (int i = 0; i < student.getGroups().length; i++) {
            if (student.getGroups()[i] == null) {
                student.getGroups()[i] = group;
                break;
            }
        }
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
            Student student1 = new Student(index, firstName, lastName);
            group.addStudent(student1);
            addStudentToStudentList(student1);
            addGroupToStudent(group, student1);
        } else if (!isStudentInTheGroup(student, group)) {
            group.addStudent(student);
            addGroupToStudent(group, student);
        } else if (isStudentInTheGroup(student, group)) {
            System.out.println("Student o indeksie " + index + " jest już w grupie " + groupCode);
        }
    }

    private boolean isStudentInTheGroup(Student student, Group group) {
        for (int i = 0; i < group.getStudents().length; i++) {
            if (group.getStudents()[i] != null && group.getStudents()[i] == student) {
                return true;
            }
        }
        return false;
    }

    private void addStudentToStudentList(Student student) {
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
            System.out.println("Kod: " + group.getCode() + "\nNazwa: " + group.getName() + "\nProwadzący: "
                    + group.getLecturer().getDegree() + " " + group.getLecturer().getFirstName() + " " +
                    group.getLecturer().getLastName() + "\nUczesticy: ");
            printStudentsInGroup(group);
        }
    }

    private void printStudentsInGroup(Group group) {
        group.printStudents();
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
        } else if (!isStudentInTheGroup(student, group)) {
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

    private void addGradeToGradeList(Grade grade) {
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
            for (int i = 0; i < group.getGrades().length; i++) {
                if (group.getGrades()[i] != null && group.getGrades()[i].getGrade() != 0) {
                    System.out.println(group.getGrades()[i].getStudent().getIndex() + " " +
                            group.getGrades()[i].getStudent().getFirstName() + " " +
                            group.getGrades()[i].getStudent().getLastName() + ": " + group.getGrades()[i].getGrade());
                }
            }
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
                System.out.println(student.getIndex() + " " + student.getFirstName() + " " +
                        student.getLastName());
            }
        }
    }
}
