package service;

import persistence.assignment.AssignmentDao;
import persistence.DistributionsDao;
import pojos.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RatingService {
    AssignmentDao assignmentDao = new AssignmentDao();
    DistributionsDao distributionsDao = new DistributionsDao();

    public List<StudentRating> getRatingByStudentName(String studentName) throws SQLException, IOException, ClassNotFoundException {
        List<StudentRating> studentRatings = new ArrayList<>();
        Map<String, List<Assignment>> subjectWithAssignments = assignmentDao.getAssignmentsByStudentName(studentName);
        for (String subject : subjectWithAssignments.keySet()) {
            System.out.println(studentName);
            Rating rating = calculateRating(groupAssignments(subjectWithAssignments.get(subject)));
            studentRatings.add(new StudentRating(subject, rating.getTestScore(), rating.getQuizScore(), rating.getLabScore(), rating.getProjectScore(), rating.getOverallRating()));
        }
        return studentRatings;
    }

    private double getScore(double maxScore, int occurrence, double points) {
        return ((maxScore / occurrence) * points) / 100;
    }

    public List<SubjectRating> getRatingsBySubjectName(String subjectName) throws SQLException, IOException, ClassNotFoundException {
        List<SubjectRating> subjectRatings = new ArrayList<>();
        Map<String, List<Assignment>> studentsWithAssignments = assignmentDao.getAssignmentsBySubjectName(subjectName);
        for (String studentName : studentsWithAssignments.keySet()) {
            System.out.println(studentName);
            Rating rating = calculateRating(groupAssignments(studentsWithAssignments.get(studentName)));
            subjectRatings.add(new SubjectRating(studentName, rating.getTestScore(), rating.getQuizScore(), rating.getLabScore(), rating.getProjectScore(), rating.getOverallRating()));
        }
        return subjectRatings;
    }

    private GroupedAssignments groupAssignments(List<Assignment> assignments) {
        GroupedAssignments groupedAssignments = new GroupedAssignments();
        for (Assignment assignment : assignments) {
            switch (assignment.getAssignmentCategory()) {
                case "TEST_1":
                case "TEST_2":
                    groupedAssignments.getTests().add(assignment);
                    break;
                case "QUIZ_1":
                case "QUIZ_2":
                    groupedAssignments.getQuizzes().add(assignment);
                    break;
                case "LAB_1":
                    groupedAssignments.getLabs().add(assignment);
                    break;
                case "PROJECT_1":
                    groupedAssignments.getProjects().add(assignment);
                    break;
            }
        }
        return groupedAssignments;
    }

    private Rating calculateRating(GroupedAssignments groupedAssignments) throws SQLException, IOException, ClassNotFoundException {
        double testScore = 0, quizScore = 0, labScore = 0, projectScore = 0, overallRating;

        for (Assignment assignment : groupedAssignments.getTests()) {
            testScore += getScore(distributionsDao.getCategoryWeightage("TEST"), groupedAssignments.getTests().size(), assignment.getPoints());
        }
        for (Assignment assignment : groupedAssignments.getQuizzes()) {
            quizScore += getScore(distributionsDao.getCategoryWeightage("QUIZ"), groupedAssignments.getQuizzes().size(), assignment.getPoints());
        }
        for (Assignment assignment : groupedAssignments.getLabs()) {
            labScore += getScore(distributionsDao.getCategoryWeightage("LAB WORK"), groupedAssignments.getLabs().size(), assignment.getPoints());
        }
        for (Assignment assignment : groupedAssignments.getProjects()) {
            projectScore += getScore(distributionsDao.getCategoryWeightage("PROJECT"), groupedAssignments.getProjects().size(), assignment.getPoints());
        }
        overallRating = (testScore + quizScore + labScore + projectScore);
        if (groupedAssignments.getTests().size() < 1) testScore = -1;
        if (groupedAssignments.getQuizzes().size() < 1) quizScore = -1;
        if (groupedAssignments.getLabs().size() < 1) labScore = -1;
        if (groupedAssignments.getProjects().size() < 1) projectScore = -1;
        return new Rating(testScore, quizScore, labScore, projectScore, overallRating);
    }

}
