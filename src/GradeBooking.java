
public interface GradeBooking {

    void csvImport(String filename);

    void csvExport(String filename);

    void csvExport(String filename, Grade grade);

    void addGrade(String firstName, String lastName, int pID, String grade);

    Grade findGrade(String firstName, String lastName, int pID);

    void removeStudent(String firstName, String lastName, int pID);

    double findAverage();
}
