package SortableLists;

import SortableLists.data.Student;
import SortableLists.lists.DoublyLinkedList;
import SortableLists.lists.Listable;
import SortableLists.lists.SinglyLinkedList;
import SortableLists.sort.*;

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
    private Listable<Student> students;

    public ConsoleApplication(InputStream is) {
        this.in = new BufferedReader(new InputStreamReader(is));
    }

    private static final String ADD_STRING = "add";
    private static final String ADD_NR = "1";
    private static final String INSERT_AT_STRING = "insert";
    private static final String INSERT_AT_NR = "2";
    private static final String GET_STRING = "get";
    private static final String GET_NR = "3";
    private static final String REMOVE_STRING = "remove";
    private static final String REMOVE_NR = "4";
    private static final String CLEAR_STRING = "clear";
    private static final String CLEAR_NR = "5";
    private static final String SIZE_STRING = "size";
    private static final String SIZE_NR = "6";
    private static final String IS_EMPTY_STRING = "empty";
    private static final String IS_EMPTY_NR = "7";
    private static final String SORT_STRING = "sort";
    private static final String SORT_NR = "8";
    private static final String SEARCH_STRING = "search";
    private static final String SEARCH_NR = "9";
    private static final String PRINT_ALL_STRING = "print";
    private static final String PRINT_ALL_NR = "10";
    private static final String PRINT_TASKS_STRING = "tasks";
    private static final String PRINT_TASKS_NR = "11";
    private static final String EXIT_STRING = "exit";
    private static final String EXIT_NR = "0";

    private static final String SURNAME_CRIT_STRING = "surname";
    private static final String PRENAME_CRIT_STRING = "prename";
    private static final String MATR_NR_CRIT_STRING = "matrNr";
    private static final String COURSE_CRIT_STRING = "course";

    public static void main(String[] args) {
        new ConsoleApplication(System.in).startUserDialogue();
    }

    public void startUserDialogue() {
        printHeader();
        System.out.println("\n" +
                "Welcome!\n\n" +
                "This application is a representation of the datatype Linked-List.\n" +
                "As example, there will be used a student data type that can be stored in the list.\n"
        );

        int decision = this.readListType();
        this.students = decision == 1 ? new SinglyLinkedList<>() : new DoublyLinkedList<>();

        while (true) {
            this.printTasks();
            String command = readCommand();
            utilizeCommand(command);
        }
    }

    private int readListType() {
        int repeat = 3;
        while (repeat > 0) {
            try {
                System.out.println("What kind of list do you want to use?\n" +
                        "1: Singly-Linked-List\n" +
                        "2: Doubly-Linked-List\n");
                System.out.print("-> ");
                String userInput = this.in.readLine().trim();
                int decision = Integer.parseInt(userInput);
                if (decision == 1 || decision == 2) {
                    return decision;
                }
                repeat--;
            } catch (IOException e) {
                System.out.println("\nProblem with input stream: " + e.getMessage());
                System.out.println("Try again.\n");
            } catch (NumberFormatException nfe) {
                System.out.println(repeat == 1 ? "To many input faults. Doubly-Linked-List is chosen automatically." : "Wrong input. Pleas try again.\n");
            } finally {
                repeat--;
            }
        }
        return 2;
    }

    private void printTasks() {
        System.out.println("\n" +
                "TASKS:\n" +
                "----\n" +
                ADD_NR + " : " + ADD_STRING + "        -> Creates a new student and adds it to the list.\n" +
                INSERT_AT_NR + " : " + INSERT_AT_STRING + "     -> Creates a new student and adds it to the list at the specified coordinates.\n" +
                GET_NR + " : " + GET_STRING + "        -> Prints the item at the specified index.\n" +
                REMOVE_NR + " : " + REMOVE_STRING + "     -> Removes the item at the specified index.\n" +
                CLEAR_NR + " : " + CLEAR_STRING + "      -> Removes all items stored in the list.\n" +
                SIZE_NR + " : " + SIZE_STRING + "       -> Prints the actual amount of items in the list.\n" +
                IS_EMPTY_NR + " : " + IS_EMPTY_STRING + "      -> Prints 'yes' if the stack is empty - 'no' otherwise.\n" +
                SORT_NR + " : " + SORT_STRING + "       -> Sorts the list and prints all items in the new order.\n" +
                SEARCH_NR + " : " + SEARCH_STRING + "     -> Searches for students matching the search term and prints the findings.\n" +
                PRINT_ALL_NR + ": " + PRINT_ALL_STRING + "      -> Prints all items of the list.\n" +
                "\n" +
                PRINT_TASKS_NR + ": " + PRINT_TASKS_STRING + "      -> Prints possible tasks.\n" +
                EXIT_NR + " : " + EXIT_STRING + "       -> Exits the application.\n" +
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
                case ADD_NR:
                case ADD_STRING:
                    doAdd(params);
                    break;
                case GET_NR:
                case GET_STRING:
                    checkForElements();
                    doGet(params);
                    break;
                case INSERT_AT_NR:
                case INSERT_AT_STRING:
                    doInsertAt(params);
                    break;
                case REMOVE_NR:
                case REMOVE_STRING:
                    checkForElements();
                    doRemove(params);
                    break;
                case CLEAR_NR:
                case CLEAR_STRING:
                    doClear();
                    break;
                case SIZE_NR:
                case SIZE_STRING:
                    doSize();
                    break;
                case IS_EMPTY_NR:
                case IS_EMPTY_STRING:
                    doIsEmpty();
                    break;
                case PRINT_ALL_NR:
                case PRINT_ALL_STRING:
                    doPrintAll();
                    break;
                case SORT_NR:
                case SORT_STRING:
                    checkForElements();
                    doSort(params);
                    break;
                case SEARCH_NR:
                case SEARCH_STRING:
                    checkForElements();
                    doSearch(params);
                    break;
                case "-h":
                case "--help":
                case PRINT_TASKS_NR:
                case PRINT_TASKS_STRING:
                    printTasks();
                    break;
                case "-q":
                case EXIT_NR:
                case EXIT_STRING:

                    System.exit(0);
                default:
                    System.out.println("\nInvalid command! Please use the syntax from the tasks-list [-h, --help]. \n");
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid command! Please use the syntax from the tasks-list.\n");
        } catch (NullPointerException | IndexOutOfBoundsException | IllegalArgumentException iob) {
            System.out.println("\nThe specified arguments are invalid!\n" +
                    "Try again.\n");
        } catch (NoSuchElementException ne) {
            System.out.println("\nThis option is not available for empty lists.\n");
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
        System.out.print("Surname: ");
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
            System.out.print("\n");
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

    private void doSort(String params) throws IOException, NullPointerException {
        if (students.size() < 2) {
            System.out.println("The list needs to contain at least 2 items to get sorted.\n");
            return;
        }

        Sortable<Student> sortingMethod = readSortMethod();

        String comparatorString = params == null ? readComparatorString("comparison") : params;
        Comparator<Student> comparator = parseStringToComparator(comparatorString, true);

        sortingMethod.sort(students, comparator);

        System.out.println("\nThe list has been sorted. The list has the following order now:\n");
        students.printAll();
        System.out.print("\n");
    }

    private Sortable<Student> readSortMethod() throws IOException {
        System.out.println("Please choose the sorting method you want to use:\n" +
                "1 : Bubble-Sort\n" +
                "2 : Selection-Sort\n" +
                "3 : Heap-Sort\n");
        System.out.print("-> ");

        String decision = this.in.readLine();
        switch (decision) {
            case "1":
            case "bubble":
                return new BubbleSort<>();
            case "2":
            case "selection":
                return new SelectionSort<>();
            case "3":
            case "heap":
                return new HeapSort<>();
            default:
                throw new IllegalArgumentException();
        }
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

        students.sort(comparator);
        Listable<Student> res = students.search(searchTerm, comparator);

        System.out.print("\n");
        if (res.size() > 0) {
            System.out.println("Following items have been found:");
            res.printAll();
            System.out.print("\n");
        } else {
            System.out.println("\nThere are no matching items in the list for the specified arguments.\n");
        }
    }

    private String readComparatorString(String usage) throws IOException {
        System.out.printf("%nSpecify the %s criterion:%n", usage);
        System.out.println("1 : Surname\n" +
                "2 : Prename\n" +
                "3 : Matriculation-Number\n" +
                "4 : Course\n");
        System.out.print("-> ");
        String userInput = in.readLine();

        return userInput.toLowerCase().trim();
    }

    private Comparator<Student> parseStringToComparator(String params, Boolean toSort)
            throws NullPointerException, IllegalArgumentException {
        switch (params) {
            case "1":
            case "s":
            case SURNAME_CRIT_STRING:
                return Student.BY_SURNAME_COMPARATOR;
            case "2":
            case "p":
            case PRENAME_CRIT_STRING:
                return Student.BY_PRENAME_COMPARATOR;
            case "3":
            case "m":
            case MATR_NR_CRIT_STRING:
                return Student.BY_MATR_NR_COMPARATOR;
            case "4":
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
            case "s":
            case SURNAME_CRIT_STRING:
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

    private void printHeader() {
        System.out.println(
                " ___       ___  ________   ___  __    _______   ________      ___       ___  ________  _________   \n" +
                        "|\\  \\     |\\  \\|\\   ___  \\|\\  \\|\\  \\ |\\  ___ \\ |\\   ___ \\    |\\  \\     |\\  \\|\\   ____\\|\\___   ___\\ \n" +
                        "\\ \\  \\    \\ \\  \\ \\  \\\\ \\  \\ \\  \\/  /|\\ \\   __/|\\ \\  \\_|\\ \\   \\ \\  \\    \\ \\  \\ \\  \\___|\\|___ \\  \\_| \n" +
                        " \\ \\  \\    \\ \\  \\ \\  \\\\ \\  \\ \\   ___  \\ \\  \\_|/_\\ \\  \\ \\\\ \\   \\ \\  \\    \\ \\  \\ \\_____  \\   \\ \\  \\  \n" +
                        "  \\ \\  \\____\\ \\  \\ \\  \\\\ \\  \\ \\  \\\\ \\  \\ \\  \\_|\\ \\ \\  \\_\\\\ \\ __\\ \\  \\____\\ \\  \\|____|\\  \\   \\ \\  \\ \n" +
                        "   \\ \\_______\\ \\__\\ \\__\\\\ \\__\\ \\__\\\\ \\__\\ \\_______\\ \\_______\\\\__\\ \\_______\\ \\__\\____\\_\\  \\   \\ \\__\\\n" +
                        "    \\|_______|\\|__|\\|__| \\|__|\\|__| \\|__|\\|_______|\\|_______\\|__|\\|_______|\\|__|\\_________\\   \\|__|\n" +
                        "                                                                               \\|_________|        \n" +
                        "-----------------------------------------------------------------------------------------------------"
        );
    }
}

