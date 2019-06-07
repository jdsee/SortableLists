package SortableLists.data;

import SortableLists.sort.Comparator;

/**
 * @author joschaseelig
 */
public class Student implements Comparable<Student> {

    public static final Comparator<Student> BY_NAME_COMPARATOR = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            if (o1.getSurname() == null && o2.getSurname() == null)
                return 0;
            if (o1.getSurname() == null)
                return 1;
            if (o2.getSurname() == null)
                return -1;
            return o1.getSurname().compareTo(o2.getSurname());
        }
    };
    public static final Comparator<Student> BY_PRENAME_COMPARATOR = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            if (o1.getPrename() == null && o2.getPrename() == null)
                return 0;
            if (o1.getPrename() == null)
                return 1;
            if (o2.getPrename() == null)
                return -1;
            return o1.getPrename().compareTo(o2.getPrename());
        }
    };
    public static final Comparator<Student> BY_MATR_NR_COMPARATOR = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.getMatrNr() - o2.getMatrNr();
        }
    };
    public static final Comparator<Student> BY_COURSE_COMPARATOR = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.getCourse() - o2.getCourse();
        }
    };

    private String surname;
    private String prename;
    private int matrNr;
    private int course;

    public Student(String surname, String prename, int matriculationNumber, int course) {
        this.surname = surname;
        this.prename = prename;
        this.matrNr = matriculationNumber;
        this.course = course;
    }

    public static Student createEmptyStudent() {
        return new Student("", "", 0, 0);
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(matrNr, o.matrNr);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        Student s = (Student) o;
        if (matrNr != s.matrNr) return false;
        if (surname != null ? !surname.equals(s.surname) : s.surname != null) return false;
        if (prename != null ? !prename.equals(s.prename) : s.prename != null) return false;
        return course == s.course;
    }

    @Override
    public String toString() {
        return "data.Student{" +
                "surname='" + surname + '\'' +
                ", prename='" + prename + '\'' +
                ", matrNr=" + matrNr +
                ", course=" + course +
                '}';
    }

    public String getSurname() {
        return surname;
    }

    public String getPrename() {
        return prename;
    }

    public int getMatrNr() {
        return matrNr;
    }

    public int getCourse() {
        return course;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public void setMatrNr(int matrNr) {
        this.matrNr = matrNr;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}
