
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.TreeMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Amanda
 */
public class TreeGradeBook implements GradeBooking {

    Map<Student, Grade> map = new TreeMap<>();
    boolean checker = true;

    /**
     * Imports information from a given file name and puts in in a HashMap of
     * Students and Grades
     *
     * @param filename name of the file to read from
     */
    @Override
    public void csvImport(String filename) {
        try {
            FileReader r = new FileReader(filename);
            Scanner in = new Scanner(r);

            while (in.hasNext()) {
                String firstInput = in.next().trim();
                String firstName = firstInput.substring(0, firstInput.length() - 1).trim();
                String lastInput = in.next().trim();
                String lastName = lastInput.substring(0, lastInput.length() - 1).trim();
                String ID = in.next().substring(0, 5);
                int pID = Integer.parseInt(ID);
                String grade = in.next();

                Student s = new Student(firstName, lastName, pID);
                Grade g = new Grade(grade);

                map.put(s, g);
            }
            checker = true;
        } catch (FileNotFoundException ex) {
            System.out.println("File was not found");
            checker = false;
        }
    }

    /**
     * Exports all the existing information from the map in alphabetical order
     * by last name
     *
     * @param filename name of the file to export to
     */
    @Override
    public void csvExport(String filename) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(filename);

            for (Student key : map.keySet()) {
                out.println(key.getFirst() + ", " + key.getLast() + ", " + key.getID() + ", " + map.get(key).getLetter());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File was not found");
        } finally {
            out.close();
        }
    }

    /**
     * Export the student's information to a given file if they are associated
     * with the given grade value
     *
     * @param filename name of the file to export to
     * @param grade grade value to match
     */
    @Override
    public void csvExport(String filename, Grade grade) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(filename);

            for (Student key : map.keySet()) {
                if (map.get(key).getLetter().equals(grade.getLetter())) {
                    out.println(key.getFirst() + ", " + key.getLast() + ", " + key.getID());
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File was not found");
        } finally {
            out.close();
        }
    }

    /**
     * Iterates through the keySet of the map looking for a given Student key if
     * it exists, it updates the student's grade value to the given grade if it
     * doesn't, it adds a new key and value association to the map
     *
     * @param firstName name of student to look for
     * @param lastName last name of student to look for
     * @param pID pantherID of student to look for
     * @param grade grade to update or add to the map
     */
    @Override
    public void addGrade(String firstName, String lastName, int pID, String grade) {
        Student s = new Student(firstName, lastName, pID);
        Grade g = new Grade(grade);

        for (Student key : map.keySet()) {
            if (key.compareTo(s) == 0) {
                map.replace(key, g);
                return;
            }
        }

        map.put(s, g);

    }

    /**
     * Iterates through the map looking for a specific student key and returning
     * its grade value
     *
     * @param firstName name of the student in question
     * @param lastName last name of the student in question
     * @param pID panther ID of the student in question
     * @return grade value of the student
     */
    @Override
    public Grade findGrade(String firstName, String lastName, int pID) {
        Student s = new Student(firstName, lastName, pID);
        Grade grade = null;
        for (Student key : map.keySet()) {
            if (key.compareTo(s) == 0) {
                grade = map.get(key);
                return grade;
            }
        }
        throw new NoSuchElementException("Grade cannot be found, Student is not on map");
    }

    /**
     * Iterates through the map looking for a Student key if it exists, it
     * removes the student if it doesn't, it doesn't do anything
     *
     * @param firstName
     * @param lastName
     * @param pID
     */
    @Override
    public void removeStudent(String firstName, String lastName, int pID) {
        Student s = new Student(firstName, lastName, pID);

        for (Student key : map.keySet()) {
            if (key.compareTo(s) == 0) {
                map.remove(key);
                break;
            }
        }
    }

    /**
     * Calculates the average of all existing grade values in the map
     *
     * @return average of all grade values in the map
     */
    @Override
    public double findAverage() {
        double average = 0;
        for (Student key : map.keySet()) {
            average += map.get(key).getValue();
        }
        average /= map.keySet().size();
        return average;
    }
}
