package service;

import dao.AssignmentsDao;
import dao.DistributionsDao;
import pojos.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RatingService {
    AssignmentsDao assignmentsDao = new AssignmentsDao();
    DistributionsDao distributionsDao = new DistributionsDao();

    public List<StudentRating> getRatingByStudentName(String studentName) {
        List<StudentRating> studentRatings = new ArrayList<>();
        Map<String, List<Assignment>> subjectWithAssignments = assignmentsDao.getAssignmentsByStudentName(studentName);
        for (String subject : subjectWithAssignments.keySet()) {
            Rating rating = calculateRating(groupAssignments(subjectWithAssignments.get(subject)));
            studentRatings.add(new StudentRating(subject, rating.getTestScore(), rating.getQuizScore(), rating.getLabScore(), rating.getProjectScore(), rating.getOverallRating()));
        }
        return studentRatings;
    }

    private double getScore(double maxScore, int occurrence, double points) {
        return ((maxScore / occurrence) * points) / 100;
    }

    public List<SubjectRating> getRatingsBySubjectName(String subjectName) {
        List<SubjectRating> subjectRatings = new ArrayList<>();
        Map<String, List<Assignment>> studentsWithAssignments = assignmentsDao.getAssignmentsBySubjectName(subjectName);
        for (String studentName : studentsWithAssignments.keySet()) {
            Rating rating = calculateRating(groupAssignments(studentsWithAssignments.get(studentName)));
            subjectRatings.add(new SubjectRating(studentName, rating.getTestScore(), rating.getQuizScore(), rating.getLabScore(), rating.getProjectScore(), rating.getOverallRating()));
        }
        return subjectRatings;
    }

    private GroupedAssignments groupAssignments(List<Assignment> assignments) {
        GroupedAssignments groupedAssignments = new GroupedAssignments();
        for (Assignment assignment : assignments) {
            switch (assignment.getAssignmentCategory()) {
                case "test 1":
                case "test 2":
                    groupedAssignments.getTests().add(assignment);
                    break;
                case "quiz 1":
                case "quiz 2":
                    groupedAssignments.getQuizzes().add(assignment);
                    break;
                case "lab 1":
                    groupedAssignments.getLabs().add(assignment);
                    break;
                case "project 1":
                    groupedAssignments.getProjects().add(assignment);
                    break;
            }
        }
        return groupedAssignments;
    }

    private Rating calculateRating(GroupedAssignments groupedAssignments) {
        double testScore = 0, quizScore = 0, labScore = 0, projectScore = 0, overallRating;

        for (Assignment assignment : groupedAssignments.getTests()) {
            testScore += getScore(distributionsDao.getCategory("test"), groupedAssignments.getTests().size(), assignment.getPoints());
        }
        for (Assignment assignment : groupedAssignments.getQuizzes()) {
            quizScore += getScore(distributionsDao.getCategory("quiz"), groupedAssignments.getQuizzes().size(), assignment.getPoints());
        }
        for (Assignment assignment : groupedAssignments.getLabs()) {
            labScore += getScore(distributionsDao.getCategory("labWork"), groupedAssignments.getLabs().size(), assignment.getPoints());
        }
        for (Assignment assignment : groupedAssignments.getProjects()) {
            projectScore += getScore(distributionsDao.getCategory("project"), groupedAssignments.getProjects().size(), assignment.getPoints());
        }
        overallRating = (testScore + quizScore + labScore + projectScore);
        if (groupedAssignments.getTests().size() < 1) testScore = -1;
        if (groupedAssignments.getQuizzes().size() < 1) quizScore = -1;
        if (groupedAssignments.getLabs().size() < 1) labScore = -1;
        if (groupedAssignments.getProjects().size() < 1) projectScore = -1;
        return new Rating(testScore, quizScore, labScore, projectScore, overallRating);
    }

}
