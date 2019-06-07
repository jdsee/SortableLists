package SortableLists;

import SortableLists.data.Student;
import SortableLists.lists.DoublyLinkedList;
import SortableLists.lists.Listable;
import SortableLists.sort.Comparator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

/**
 * @author joschaseelig
 */
public class ConsoleApplication {

    private final BufferedReader in;
    private final Listable<Student> students;

    public ConsoleApplication(InputStream is) {
        this.in = new BufferedReader(new InputStreamReader(is));
        this.students = new DoublyLinkedList<>();
    }

    private static final String ADD_STRING = "add";
    private static final String GET_STRING = "get";
    private static final String INSERT_AT_STRING = "insert";
    private static final String REMOVE_STRING = "remove";
    private static final String CLEAR_STRING = "clear";
    private static final String SIZE_STRING = "size";
    private static final String IS_EMPTY_STRING = "empty";
    private static final String HEAPSORT_STRING = "sort";
    private static final String SEARCH_STRING = "search";
    private static final String PRINT_ALL_STRING = "print";
    private static final String PRINT_TASKS_STRING = "tasks";
    private static final String EXIT_STRING = "exit";

    private static final String NAME_CRIT_STRING = "name";
    private static final String PRENAME_CRIT_STRING = "prename";
    private static final String MATR_NR_CRIT_STRING = "matrNr";
    private static final String COURSE_CRIT_STRING = "course";

    public static void main(String[] args) {
        new ConsoleApplication(System.in).startUserDialogue();
    }

    public void startUserDialogue() {
        System.out.println(
                "\n" +
                        "Welcome!\n\n" +
                        "This application is a representation of the datatype Linked-List.\n" +
                        "As example, there will be used a student data type that can be saved in the students.\n" +
                        "\n-> students can be added directly by typing the data behind the command in the following order:\n" +
                        "[command] [index if necessary] [name] [surname] [matriculation-number] [course]\n");

        printTasks();
        while (true) {
            String command = readCommand();
            utilizeCommand(command);
        }
    }

    private void printTasks() {
        System.out.println("\n" +
                "TASKS:\n" +
                "----\n" +
                ADD_STRING + "        -> Creates a new student and adds it to the list.\n" +
                INSERT_AT_STRING + "     -> Creates a new student and adds it to the list at the specified coordinates.\n" +
                GET_STRING + "        -> Prints the item at the specified index.\n" +
                REMOVE_STRING + "     -> Removes the item at the specified index.\n" +
                SIZE_STRING + "       -> Prints the actual amount of items in the list.\n" +
                IS_EMPTY_STRING + "      -> Prints 'yes' if the stack is empty - 'no' otherwise.\n" +
                HEAPSORT_STRING + "       -> Sorts the list and prints all items in the new order.\n" +
                SEARCH_STRING + "     -> Searches for students matching the search term and prints the findings.\n" +
                PRINT_ALL_STRING + "      -> Prints all items of the list.\n" +
                "\n" +
                PRINT_TASKS_STRING + "      -> Prints possible tasks.\n" +
                EXIT_STRING + "       -> Exits the application.\n" +
                "----\n");
    }

    private String readCommand() {
        while (true) {
            System.out.print("-> ");
            try {
                return in.readLine().trim();
            } catch (IOException | NullPointerException e) {
                System.out.println("\nProblem with input stream: " + e.getMessage());
                System.out.println("Try again.\n");
            }
        }
    }

    private void utilizeCommand(String userInput) {
        String command, params;
        int splitIndex = userInput.indexOf(' ');
        if (splitIndex > 0) {
            command = userInput.substring(0, splitIndex);
            params = userInput.substring(splitIndex).trim();
        } else {
            command = userInput;
            params = null;
        }

        try {
            switch (command) {
                case ADD_STRING:
                    doAdd(params);
                    break;
                case GET_STRING:
                    checkForElements();
                    doGet(params);
                    break;
                case INSERT_AT_STRING:
                    doInsertAt(params);
                    break;
                case REMOVE_STRING:
                    checkForElements();
                    doRemove(params);
                    break;
                case CLEAR_STRING:
                    doClear();
                    break;
                case SIZE_STRING:
                    doSize();
                    break;
                case IS_EMPTY_STRING:
                    doIsEmpty();
                    break;
                case PRINT_ALL_STRING:
                    doPrintAll();
                    break;
                case HEAPSORT_STRING:
                    checkForElements();
                    doHeapSort(params);
                    break;
                case SEARCH_STRING:
                    checkForElements();
                    doSearch(params);
                    break;
                case "-h":
                case "--help":
                case PRINT_TASKS_STRING:
                    printTasks();
                    break;
                case "-q":
                case EXIT_STRING:
                    System.exit(0);
                default:
                    System.out.println("\nInvalid command! Please use the syntax from the tasks-list [-h || --help]. \n");
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid command! Please use the syntax from the tasks-list.\n");
        } catch (NullPointerException | IndexOutOfBoundsException iob) {
            System.out.println("\nThe specified arguments are invalid!\n" +
                    "Try again.\n");
        } catch (NoSuchElementException ne) {
            System.out.println("This is option is not available for empty lists.\n");
        } catch (IOException e) {
            System.out.println("\nProblem with input stream: " + e.getMessage() +
                    "\nTry again.\n");
        }
    }

    private void checkForElements() throws NoSuchElementException {
        if (students.isEmpty())
            throw new NoSuchElementException();
    }

    private int parseStringToIndex(String param) throws NumberFormatException {
        int splitIndex = param.indexOf(' ');
        return Integer.parseInt(param.substring(0, splitIndex));
    }

    private int readIndex() throws IOException {
        int index;
        int tries = 0;
        while (true) {
            System.out.print("Please specify the index: ");
            try {
                index = Integer.parseInt(in.readLine());
                if (index < 0 || index >= students.size() && index != 0)
                    throw new IndexOutOfBoundsException();
                return index;
            } catch (IndexOutOfBoundsException iob) {
                if (++tries == 3) {
                    System.out.println();
                    throw new IndexOutOfBoundsException();
                }
                System.out.println("\nIndex out of bounds.\n" +
                        "Try again.\n");
            }
        }
    }

    private Student readStudent() throws IOException, NumberFormatException {
        String name, prename;
        int matrNr, course;

        System.out.println("Please type in the required data of the student.");

        name = readName();
        prename = readPrename();
        matrNr = readMatrNr();
        course = readCourse();

        return new Student(name, prename, matrNr, course);

    }

    private String readPrename() throws IOException {
        System.out.print("Prename: ");
        return in.readLine().trim();
    }

    private String readName() throws IOException {
        System.out.print("Name: ");
        return in.readLine().trim();
    }

    private int readCourse() throws IOException {
        int tries = 0;
        while (true) {
            try {
                System.out.print("Course: ");
                String courseString = in.readLine().trim();
                int splitIndex = courseString.indexOf(' ');
                courseString = splitIndex > 0 ? courseString.substring(0, splitIndex) : courseString;
                return Integer.parseInt(courseString);
            } catch (NumberFormatException nfe) {
                if (++tries == 3) {
                    System.out.println();
                    throw new NumberFormatException();
                }
                System.out.println("\nCourse have to be integers!\n" +
                        "Try again.\n");
            }
        }
    }

    private int readMatrNr() throws IOException, NumberFormatException {
        int tries = 0;
        while (true) {
            try {
                System.out.print("Matriculation-Number: ");
                String matrNrString = in.readLine().trim();
                int splitIndex = matrNrString.indexOf(' ');
                matrNrString = splitIndex > 0 ? matrNrString.substring(0, splitIndex) : matrNrString;
                return Integer.parseInt(matrNrString);
            } catch (NumberFormatException nfe) {
                if (++tries == 3) {
                    System.out.println();
                    throw new NumberFormatException();
                }
                System.out.println("\nMatriculation-Number have to be integer!\n" +
                        "Try again.\n");
            }
        }
    }

    private Student parseStringToStudent(String params)
            throws NumberFormatException, StringIndexOutOfBoundsException {
        params = params.trim();
        int splitIndex01 = params.indexOf(' ');
        String name = params.substring(0, splitIndex01).trim();

        int splitIndex02 = params.indexOf(' ', splitIndex01 + 1);
        String prename = params.substring(splitIndex01, splitIndex02).trim();

        splitIndex01 = params.indexOf(' ', splitIndex02 + 1);
        String matrNrString = params.substring(splitIndex02, splitIndex01).trim();
        int matrNr = Integer.parseInt(matrNrString);

        splitIndex02 = params.length();
        String courseString = params.substring(splitIndex01, splitIndex02).trim();
        int course = Integer.parseInt(courseString);
        return new Student(name, prename, matrNr, course);

    }

    private void doPrintAll() {
        if (students.isEmpty())
            System.out.println("There are no items to print in the list.\n");
        else {
            System.out.println("\nList-Items:");
            students.printAll();
        }
    }

    private void doAdd(String params) throws IOException {
        Student student = params != null ? parseStringToStudent(params) : readStudent();
        students.add(student);
        System.out.printf("\nThe following student has been added to the list:%n%s%n%n", student);
    }

    private void doGet(String params) throws IOException {
        int index = params != null ? parseStringToIndex(params) : readIndex();
        Student student = students.get(index);
        System.out.printf("\nThe following item is stored at index %d:%n%s%n%n", index, student);
    }

    private void doInsertAt(String params) throws IOException {
        String indexParam = null, studentParams = null;
        if (params != null) {
            int splitIndex = params.indexOf(' ');
            indexParam = splitIndex >= 0 ? params.substring(0, splitIndex) : null;
            studentParams = splitIndex >= 0 ? params.substring(splitIndex).trim() : null;
        }
        int index = indexParam != null ? parseStringToIndex(indexParam) : readIndex();
        Student student = studentParams != null && !studentParams.isEmpty() ?
                parseStringToStudent(params) : readStudent();
        students.insertAt(index, student);
        System.out.println("The following student has been added to the list:\n" + student + "\n");
    }

    private void doRemove(String params) throws IOException {
        int index = params != null ? parseStringToIndex(params) : readIndex();
        Student student = students.get(students.size() - 1);
        students.remove(index);
        System.out.println("\nThe following student has been removed from the list:\n" + student + "\n");
    }

    private void doClear() {
        students.clear();
        System.out.println("\nAll items have been removed from the list.\n");
    }

    private void doSize() {
        System.out.println("\nThe list contains " + students.size() + " items\n");
    }

    private void doIsEmpty() {
        String answer = students.isEmpty() ? "empty." : "not empty.";
        System.out.println("\nThe students is " + answer + "\n");
    }

    private void doHeapSort(String params) throws IOException, NullPointerException {
        if (students.size() < 2) {
            System.out.println("The list needs to contain at least 2 items to get sorted.\n");
            return;
        }
        String comparatorString = params == null ? readComparatorString("comparison") : params;
        Comparator<Student> comparator = parseStringToComparator(comparatorString, true);

        students.heapsort(comparator);
        System.out.println("\nThe list has been sorted. The list has the following order now:\n");
        students.printAll();
    }

    private void doSearch(String params) throws IOException {
        String comparatorString = null;
        String searchTermString = null;
        if (params != null) {
            int splitIndex = params.indexOf(' ');
            comparatorString = splitIndex >= 0 ? params.substring(0, splitIndex) : null;
            searchTermString = splitIndex >= 0 ? params.substring(splitIndex).trim() : null;
        }
        comparatorString = comparatorString == null ? readComparatorString("search") : comparatorString;
        Comparator<Student> comparator = parseStringToComparator(comparatorString, false);
        Student searchTerm = parseStringToSearchTerm(comparatorString, searchTermString);

        students.heapsort(comparator);
        Listable<Student> res = students.search(searchTerm, comparator);

        System.out.print("\n");
        if (res.size() > 0) {
            System.out.println("Following items have been found:\n");
            res.printAll();
            System.out.print("\n");
        } else {
            System.out.println("\nThere are no matching items in the list for the specified arguments.\n");
        }
    }

    private String readComparatorString(String usage) throws IOException {
        System.out.printf("%nSpecify the %s criterion. [name || prename || matriculationNr || course]%n", usage);
        System.out.print("-> ");
        String userInput = in.readLine();

        return userInput.toLowerCase().trim();
    }

    private Comparator<Student> parseStringToComparator(String params, Boolean toSort)
            throws NullPointerException, IllegalArgumentException {
        switch (params) {
            case "n":
            case NAME_CRIT_STRING:
                return Student.BY_NAME_COMPARATOR;
            case "p":
            case PRENAME_CRIT_STRING:
                return Student.BY_PRENAME_COMPARATOR;
            case "m":
            case MATR_NR_CRIT_STRING:
                return Student.BY_MATR_NR_COMPARATOR;
            case "c":
            case COURSE_CRIT_STRING:
                return Student.BY_COURSE_COMPARATOR;
            default:
                throw new IllegalArgumentException();
        }
    }

    private Student parseStringToSearchTerm(String comparatorString, String searchTermString)
            throws IOException, IllegalArgumentException {
        Student res = Student.createEmptyStudent();
        switch (comparatorString) {
            case "n":
            case NAME_CRIT_STRING:
                res.setSurname(searchTermString != null ? searchTermString : readName());
                break;
            case "p":
            case PRENAME_CRIT_STRING:
                res.setPrename(searchTermString != null ? searchTermString : readPrename());
                break;
            case "m":
            case MATR_NR_CRIT_STRING:
                res.setMatrNr(searchTermString != null ? Integer.parseInt(searchTermString) : readMatrNr());
                break;
            case "c":
            case COURSE_CRIT_STRING:
                res.setCourse(searchTermString != null ? Integer.parseInt(searchTermString) : readCourse());
                break;
            default:
                throw new IllegalArgumentException();
        }
        return res;
    }
}

