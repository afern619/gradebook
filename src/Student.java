
public class Student implements Comparable {

    private String firstName;
    private String lastName;
    private int pID;

    public Student(String aFirst, String aLast, int apID) {
        firstName = aFirst;
        lastName = aLast;
        pID = apID;
    }

    public int getID() {
        return pID;
    }

    public String getFirst() {
        return firstName;
    }

    public String getLast() {
        return lastName;
    }

    public int compareTo(Object s) {
        if (lastName.compareTo(((Student) s).getLast()) != 0) {
            return lastName.compareTo(((Student) s).getLast());
        } else if (firstName.compareTo(((Student) s).getFirst()) != 0) {
            return firstName.compareTo(((Student) s).getFirst());
        } else {
            return pID - ((Student) s).getID();
        }
    }
}
