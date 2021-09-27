package persistence.assignment;

import pojos.Assignment;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AssignmentDaoHelper {

    public static ArrayList<Assignment> generateAssignments(ResultSet resultSet) throws SQLException {
        ArrayList<Assignment> assignments = new ArrayList<>();
        while (resultSet.next()) {
            String studentName = resultSet.getString("STUDENT_NAME");
            String subject = resultSet.getString("SUBJECT");
            String assignmentCategory = resultSet.getString("ASSIGNMENT_CATEGORY");
            Date dateOfSubmission = resultSet.getDate("DATE_OF_SUBMISSION");
            int points = resultSet.getInt("POINTS");
            assignments.add(new Assignment(studentName, subject, assignmentCategory, dateOfSubmission, points));
        }
        return assignments;
    }
}
