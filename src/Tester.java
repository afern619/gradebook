
import java.util.Scanner;

/**
 *
 * @author Amanda
 */
public class Tester {

    public static void main(String args[]) {

        Scanner in = new Scanner(System.in);
        Hash1GradeBook h = new Hash1GradeBook();
        Hash2GradeBook h2 = new Hash2GradeBook();
        String fileName;

        do {
            System.out.println("Input a file name");
            fileName = in.nextLine();
            h.csvImport(fileName);
        } while (!h.checker);
        long start;
        long end;
        long sum = 0;
        for (Student1 key : h.map.keySet()) {
            start = System.nanoTime();
            h.findGrade(key.getFirst(), key.getLast(), key.getID()).getLetter();
            end = System.nanoTime();
            sum += end - start;
        }

        sum /= h.map.keySet().size();
        System.out.println("Average 1: " + sum);

        h2.csvImport(fileName);
        long start2;
        long end2;
        long sum2 = 0;
        for (Student2 key : h2.map.keySet()) {
            start2 = System.nanoTime();
            h2.findGrade(key.getFirst(), key.getLast(), key.getID()).getLetter();
            end2 = System.nanoTime();
            sum2 += end2 - start2;
        }

        sum2 /= h2.map.keySet().size();
        System.out.println("Average 2: " + sum2);

        if (sum2 > sum) {
            System.out.println("Hash1 is faster");
        } else if (sum > sum2) {
            System.out.println("Hash2 is faster");
        } else {
            System.out.println("Same");
        }
    }
}
