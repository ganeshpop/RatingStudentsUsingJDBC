package pojos;

import java.time.LocalDate;

public class Assignment {
    private String studentName;
    private String subject;
    private String assignmentCategory;
    private LocalDate dateOfSubmission;
    private int points;

    public Assignment(String studentName, String subject, String assignmentCategory, LocalDate dateOfSubmission, int points) {
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

    public LocalDate getDateOfSubmission() {
        return dateOfSubmission;
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
