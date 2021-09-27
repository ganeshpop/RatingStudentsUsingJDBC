package persistence.assignment;

import persistence.MySQLConnectionHelper;
import pojos.Assignment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AssignmentDao {


    public Map<String, List<Assignment>> getAssignmentsByStudentName(String studentName) throws SQLException, IOException, ClassNotFoundException {
        Map<String, List<Assignment>> studentSpecificAssignments = new Hashtable<>();
        Connection connection = MySQLConnectionHelper.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student_management.assignments WHERE STUDENT_NAME = ? ORDER BY SUBJECT;");
        preparedStatement.setString(1, studentName);
        ResultSet resultSet = preparedStatement.executeQuery();
        for (Assignment assignment : AssignmentDaoHelper.generateAssignments(resultSet)) {
                if (!studentSpecificAssignments.containsKey(assignment.getSubject())) {
                    ArrayList<Assignment> assignments = new ArrayList<>();
                    assignments.add(assignment);
                    studentSpecificAssignments.put(assignment.getSubject(), assignments);
                } else {
                    studentSpecificAssignments.get(assignment.getSubject()).add(assignment);
                }
        }
        return studentSpecificAssignments;
    }

    public Map<String, List<Assignment>> getAssignmentsBySubjectName(String subjectName) throws SQLException, IOException, ClassNotFoundException {
        Map<String, List<Assignment>> subjectSpecificAssignments = new Hashtable<>();
        Connection connection = MySQLConnectionHelper.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student_management.assignments WHERE SUBJECT = ? ORDER BY STUDENT_NAME;");
        preparedStatement.setString(1, subjectName);
        ResultSet resultSet = preparedStatement.executeQuery();
        for (Assignment assignment : AssignmentDaoHelper.generateAssignments(resultSet)) {
                if (!subjectSpecificAssignments.containsKey(assignment.getStudentName())) {
                    ArrayList<Assignment> assignments = new ArrayList<>();
                    assignments.add(assignment);
                    subjectSpecificAssignments.put(assignment.getStudentName(), assignments);
                } else {
                    subjectSpecificAssignments.get(assignment.getStudentName()).add(assignment);
                }
        }
        return subjectSpecificAssignments;
    }



}
