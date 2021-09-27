package pojos;

import java.sql.Date;

public class Assignment {
    private final String studentName;
    private final String subject;
    private final String assignmentCategory;
    private final Date dateOfSubmission;
    private final int points;

    public Assignment(String studentName, String subject, String assignmentCategory, Date dateOfSubmission, int points) {
        this.studentName = studentName;
        this.subject = subject;
        this.assignmentCategory = assignmentCategory;
        this.dateOfSubmission = dateOfSubmission;
        this.points = points;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getSubject() {
        return subject;
    }

    public String getAssignmentCategory() {
        return assignmentCategory;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Name : " + studentName + "\nSubject : " + subject + "\nAssignment Category : " + assignmentCategory
                + "\nDate of Submission : " + dateOfSubmission.toString() + " \nPoints : " + points;
    }
}
