package SortableLists;

import SortableLists.data.Student;
import SortableLists.lists.DoublyLinkedList;
import SortableLists.lists.Listable;
import SortableLists.lists.SinglyLinkedList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author joschaseelig
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest {

    private Listable<String> createStringList() {
        return new DoublyLinkedList<>();
    }

    private Listable<Student> createStudentList() {
        return new SinglyLinkedList<>();
    }

    private Student createStudent(String name, String prename, int matrNr, int course) {
        return new Student(name, prename, matrNr, course);
    }

    @Test
    public void addAndGet_GoodCase01() throws NullPointerException {
        Listable<String> list = this.createStringList();

        String stringIn = "Test";
        list.add(stringIn);
        String stringOut = list.get(0);

        Assert.assertEquals(stringIn, stringOut);
    }

    @Test(expected = NullPointerException.class)
    public void add_BadCase01() throws NullPointerException {
        Listable<String> list = this.createStringList();

        String string = null;
        list.add(string);
    }

    @Test
    public void insertAtAndGet_GoodCase01() {
        Listable<String> list = this.createStringList();

        String s1, s2, s3;
        s1 = "s1";
        s2 = "s2";
        s3 = "s3";
        list.add(s1);
        list.add(s2);
        list.insertAt(1, s3);

        Assert.assertEquals(s3, list.get(1));
        Assert.assertEquals(s2, list.get(2));
    }

    @Test
    public void insertAtAndGet_GoodCase02() {
        Listable<String> list = this.createStringList();

        String s1, s2, s3;
        s1 = "s1";
        s2 = "s2";
        s3 = "s3";
        list.add(s1);
        list.add(s2);
        list.insertAt(2, s3);

        Assert.assertEquals(s3, list.get(2));
        Assert.assertEquals(s2, list.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void insertAt_BadCase02() {
        Listable<String> list = this.createStringList();

        String s1, s2, s3;
        s1 = "s1";
        s2 = "s2";
        s3 = "s3";
        list.add(s1);
        list.add(s2);

        list.insertAt(3, s3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void insertAt_BadCase03() {
        Listable<String> list = this.createStringList();

        String s1, s2, s3;
        s1 = "s1";
        s2 = "s2";
        s3 = "s3";
        list.add(s1);
        list.add(s2);

        list.insertAt(-1, s3);
    }

    @Test(expected = NullPointerException.class)
    public void insertAt_BadCase04() {
        Listable<String> list = this.createStringList();

        String s1, s2, s3;
        s1 = "s1";
        s2 = "s2";
        s3 = null;
        list.add(s1);
        list.add(s2);

        list.insertAt(1, s3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_BadCase01() throws IndexOutOfBoundsException {
        Listable<String> list = this.createStringList();

        String string = "String";

        list.get(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_BadCase02() throws IndexOutOfBoundsException {
        Listable<String> list = this.createStringList();

        String string = "String";

        list.get(-1);
    }

    @Test
    public void getSizeAndRemove_GoodCase01() {
        Listable<String> list = this.createStringList();

        String s1, s2, s3;
        s1 = "s1";
        s2 = "s2";
        s3 = "s3";
        list.add(s1);
        list.add(s2);
        list.add(s3);

        Assert.assertEquals(3, list.size());
        list.remove(1);
        Assert.assertEquals(2, list.size());
        Assert.assertEquals("s3", list.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void remove_BadCase01() throws IndexOutOfBoundsException {
        Listable<String> list = this.createStringList();

        list.remove(0);
    }

    @Test
    public void clear_GoodCase01() {
        Listable<String> list = this.createStringList();

        String s1, s2, s3;
        s1 = "s1";
        s2 = "s2";
        s3 = "s3";
        list.add(s1);
        list.add(s2);
        list.add(s3);

        Assert.assertEquals(3, list.size());
        Assert.assertFalse(list.isEmpty());

        list.clear();

        Assert.assertEquals(0, list.size());
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void exchange_GoodCase01() {
        Listable<String> list = this.createStringList();

        list.add("s0");
        list.add("s1");
        list.add("s2");
        list.add("s3");
        list.add("s4");
        list.add("s5");

        list.swap(1, 4);

        Assert.assertEquals(list.get(1), "s4");
        Assert.assertEquals(list.get(4), "s1");
    }

    @Test
    public void exchange_GoodCase02() {
        Listable<String> list = this.createStringList();

        list.add("s0");
        list.add("s1");
        list.add("s2");
        list.add("s3");
        list.add("s4");
        list.add("s5");

        list.swap(5, 2);

        Assert.assertEquals(list.get(2), "s5");
        Assert.assertEquals(list.get(5), "s2");
    }

    @Test
    public void exchange_BorderCase01() {
        Listable<String> list = this.createStringList();

        list.add("s0");
        list.add("s1");
        list.add("s2");
        list.add("s3");
        list.add("s4");
        list.add("s5");

        list.swap(5, 0);

        Assert.assertEquals(list.get(5), "s0");
        Assert.assertEquals(list.get(0), "s5");
    }

    @Test
    public void exchange_BorderCase02() {
        Listable<String> list = this.createStringList();

        list.add("s0");
        list.add("s1");
        list.add("s2");
        list.add("s3");
        list.add("s4");
        list.add("s5");

        list.swap(0, 5);

        Assert.assertEquals(list.get(5), "s0");
        Assert.assertEquals(list.get(0), "s5");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void exchange_BadCase01() {
        Listable<String> list = this.createStringList();

        list.add("s0");
        list.add("s1");
        list.add("s2");
        list.add("s3");
        list.add("s4");
        list.add("s5");

        list.swap(0, 6);
    }

    @Test
    public void exchange_BadCase02() {
        Listable<String> list = this.createStringList();

        list.add("s0");
        list.add("s1");
        list.add("s2");
        list.add("s3");
        list.add("s4");
        list.add("s5");

        list.swap(4, 4);

        Assert.assertEquals(list.get(4), "s4");
    }

    @Test
    public void exchange_BadCase03() {
        Listable<String> list = this.createStringList();

        list.add("s0");
        list.add("s1");
        list.add("s2");
        list.add("s3");
        list.add("s4");
        list.add("s5");

        list.swap(1, 4);

        Assert.assertNotEquals(list.get(1), "s1");
        Assert.assertNotEquals(list.get(4), "s4");
    }

    @Test
    public void clear_GoodCase02() {
        Listable<String> list = this.createStringList();

        list.clear();

        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void heapSort_GoodCase01() {
        Listable<Student> students = this.createStudentList();

        Student s1 = createStudent("aa", "aa", 99, 5);
        Student s2 = createStudent("zz", "b", 88, 5);
        Student s3 = createStudent("ee", "lol", 77, 2);
        Student s4 = createStudent("ab", "aa", 66, 1);
        Student s5 = createStudent("bb", "aa", 55, 4);
        Student s6 = createStudent("ca", "aa", 44, 3);
        Student s7 = createStudent("ca", "az", 33, 3);
        Student s8 = createStudent("aa", "ab", 22, 4);
        Student s9 = createStudent("yz", "aa", 11, 5);

        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        students.add(s6);
        students.add(s7);
        students.add(s8);
        students.add(s9);

        students.heapsort(Student.BY_MATR_NR_COMPARATOR);

        Assert.assertEquals(s9, students.get(0));
        Assert.assertEquals(s8, students.get(1));
        Assert.assertEquals(s7, students.get(2));
        Assert.assertEquals(s6, students.get(3));
        Assert.assertEquals(s5, students.get(4));
        Assert.assertEquals(s4, students.get(5));
        Assert.assertEquals(s3, students.get(6));
        Assert.assertEquals(s2, students.get(7));
        Assert.assertEquals(s1, students.get(8));
    }

    @Test
    public void heapSort_GoodCase02() {
        Listable<Student> students = this.createStudentList();

        Student s1 = createStudent("aa", "aa", 99, 5);
        Student s2 = createStudent("zz", "b", 88, 5);
        Student s3 = createStudent("ee", "lol", 77, 2);
        Student s4 = createStudent("ab", "aa", 66, 1);
        Student s5 = createStudent("bb", "aa", 55, 4);
        Student s6 = createStudent("ca", "aa", 44, 3);
        Student s7 = createStudent("ca", "az", 33, 3);
        Student s8 = createStudent("aa", "ab", 22, 4);
        Student s9 = createStudent("yz", "aa", 11, 5);

        students.add(s3);
        students.add(s8);
        students.add(s2);
        students.add(s9);
        students.add(s5);
        students.add(s7);
        students.add(s1);
        students.add(s4);
        students.add(s6);

        students.heapsort(Student.BY_MATR_NR_COMPARATOR);

        Assert.assertEquals(s9, students.get(0));
        Assert.assertEquals(s8, students.get(1));
        Assert.assertEquals(s7, students.get(2));
        Assert.assertEquals(s6, students.get(3));
        Assert.assertEquals(s5, students.get(4));
        Assert.assertEquals(s4, students.get(5));
        Assert.assertEquals(s3, students.get(6));
        Assert.assertEquals(s2, students.get(7));
        Assert.assertEquals(s1, students.get(8));
    }

    @Test
    public void heapSortByName_GoodCase01() {
        Listable<Student> students = this.createStudentList();

        Student s1 = createStudent("aa", "aa", 99, 5);
        Student s2 = createStudent("zz", "b", 88, 5);
        Student s3 = createStudent("ee", "lol", 77, 2);
        Student s4 = createStudent("ab", "aa", 66, 1);
        Student s5 = createStudent("bb", "aa", 55, 4);
        Student s6 = createStudent("ca", "aa", 44, 3);
        Student s7 = createStudent("ca", "az", 33, 3);
        Student s8 = createStudent("aa", "ab", 22, 4);
        Student s9 = createStudent("yz", "aa", 11, 5);

        students.add(s3);
        students.add(s8);
        students.add(s2);
        students.add(s9);
        students.add(s5);
        students.add(s7);
        students.add(s1);
        students.add(s4);
        students.add(s6);

        students.heapsort(Student.BY_NAME_COMPARATOR);

        Assert.assertEquals(s1, students.get(0));
        Assert.assertEquals(s8, students.get(1));
        Assert.assertEquals(s4, students.get(2));
        Assert.assertEquals(s5, students.get(3));
        Assert.assertEquals(s6, students.get(4));
        Assert.assertEquals(s7, students.get(5));
        Assert.assertEquals(s3, students.get(6));
        Assert.assertEquals(s9, students.get(7));
        Assert.assertEquals(s2, students.get(8));
    }

    @Test(expected = NullPointerException.class)
    public void heapSortByName_BadCase01() {
        Listable<Student> students = this.createStudentList();

        Student s1 = createStudent("aa", "aa", 99, 5);
        Student s2 = null;
        Student s3 = createStudent("ee", "lol", 77, 2);
        Student s4 = createStudent("ab", "aa", 66, 1);
        Student s5 = createStudent("bb", "aa", 55, 4);
        Student s6 = createStudent("ca", "aa", 44, 3);
        Student s7 = createStudent("ca", "az", 33, 3);
        Student s8 = createStudent("aa", "ab", 22, 4);
        Student s9 = createStudent("yz", "aa", 11, 5);

        students.add(s3);
        students.add(s8);
        students.add(s2);
        students.add(s9);
        students.add(s5);
        students.add(s7);
        students.add(s1);
        students.add(s4);
        students.add(s6);

        students.heapsort(Student.BY_NAME_COMPARATOR);
    }

    @Test
    public void heapSortByCourse_GoodCase01() {
        Listable<Student> students = this.createStudentList();

        Student s1 = createStudent("aa", "aa", 99, 5);
        Student s2 = createStudent("zz", "b", 88, 5);
        Student s3 = createStudent("ee", "lol", 77, 2);
        Student s4 = createStudent("ab", "aa", 66, 1);
        Student s5 = createStudent("bb", "aa", 55, 4);
        Student s6 = createStudent("ca", "aa", 44, 6);
        Student s7 = createStudent("ca", "az", 33, 6);
        Student s8 = createStudent("aa", "ab", 22, 4);
        Student s9 = createStudent("yz", "aa", 11, 5);

        students.add(s3);
        students.add(s8);
        students.add(s2);
        students.add(s9);
        students.add(s5);
        students.add(s7);
        students.add(s1);
        students.add(s4);
        students.add(s6);

        students.heapsort(Student.BY_COURSE_COMPARATOR);

        Assert.assertEquals(s4, students.get(0));
        Assert.assertEquals(s3, students.get(1));
        Assert.assertEquals(s8, students.get(2));
        Assert.assertEquals(s5, students.get(3));
        Assert.assertEquals(s9, students.get(4));
        Assert.assertEquals(s2, students.get(5));
        Assert.assertEquals(s1, students.get(6));
        Assert.assertEquals(s7, students.get(7));
        Assert.assertEquals(s6, students.get(8));
    }

    @Test
    public void search_GoodCase01() {
        Listable<Student> list = this.createStudentList();

        Student s1 = new Student("lustig", "peter", 12345, 1);
        Student s2 = new Student("Langstrumpf", "Pipi", 12345, 1);
        Student s3 = new Student("zwegat", "peter", 54321, 2);
        list.add(s1);
        list.add(s2);
        list.add(s3);

        String searchTerm = "peter";
        Student wrappedTerm = Student.createEmptyStudent();
        wrappedTerm.setPrename(searchTerm);
        Listable<Student> findings = list.search(wrappedTerm, Student.BY_PRENAME_COMPARATOR);

        Assert.assertEquals(2, findings.size());
        Assert.assertEquals(s1, findings.get(0));
        Assert.assertEquals(s3, findings.get(1));
    }

    @Test
    public void search_GoodCase02() {
        Listable<Student> students = this.createStudentList();

        Student s1 = createStudent("aa", "aa", 99, 5);
        Student s2 = createStudent("zz", "b", 88, 5);
        Student s3 = createStudent("ee", "lol", 77, 2);
        Student s4 = createStudent("ab", "aa", 66, 1);
        Student s5 = createStudent("bb", "aa", 55, 4);
        Student s6 = createStudent("ca", "aa", 44, 6);
        Student s7 = createStudent("ca", "az", 33, 6);
        Student s8 = createStudent("aa", "ab", 22, 4);
        Student s9 = createStudent("yz", "aa", 11, 5);

        students.add(s3);
        students.add(s8);
        students.add(s2);
        students.add(s9);
        students.add(s5);
        students.add(s7);
        students.add(s1);
        students.add(s4);
        students.add(s6);

        students.heapsort(Student.BY_NAME_COMPARATOR);

        String searchTermString = "aa";
        Student searchTerm = Student.createEmptyStudent();
        searchTerm.setSurname(searchTermString);
        Listable<Student> res = students.search(searchTerm, Student.BY_NAME_COMPARATOR);

        Assert.assertEquals(2, res.size());
        Assert.assertEquals(s8, res.get(0));
        Assert.assertEquals(s1, res.get(1));
    }

    @Test
    public void search_GoodCase03() {
        Listable<Student> students = this.createStudentList();

        Student s1 = createStudent("aa", "aa", 99, 5);

        students.add(s1);

        String searchTermString = "aa";
        Student searchTerm = Student.createEmptyStudent();
        searchTerm.setSurname(searchTermString);
        Listable<Student> res = students.search(searchTerm, Student.BY_NAME_COMPARATOR);

        Assert.assertEquals(s1, res.get(0));
    }

    @Test
    public void search_GoodCase04() {
        Listable<Student> students = this.createStudentList();

        Student s1 = createStudent("aa", "aa", 99, 5);
        Student s2 = createStudent("zz", "b", 88, 5);

        students.add(s1);
        students.add(s2);

        String searchTermString = "aa";
        Student searchTerm = Student.createEmptyStudent();
        searchTerm.setSurname(searchTermString);
        Listable<Student> res = students.search(searchTerm, Student.BY_NAME_COMPARATOR);

        Assert.assertEquals(s1, res.get(0));
    }
}