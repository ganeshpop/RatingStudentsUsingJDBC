package dao;

import pojos.Assignment;

import java.time.LocalDate;
import java.util.*;

public class AssignmentsDao {
    private Map<Integer, Assignment> assignments = new HashMap<>();

    public AssignmentsDao() {
        this.assignments.put(1, new Assignment("Ananth", "Electro Fields", "test 1", LocalDate.of(2016, 7, 21), 100));
        this.assignments.put(2, new Assignment("Bhagath", "Electro Fields", "test 1", LocalDate.of(2016, 7, 21), 78));
        this.assignments.put(3, new Assignment("Chaya", "Electro Fields", "test 1", LocalDate.of(2016, 7, 21), 68));
        this.assignments.put(4, new Assignment("Esharath", "Electro Fields", "test 1", LocalDate.of(2016, 7, 21), 87));
        this.assignments.put(5, new Assignment("Bhagath", "Electro Fields", "quiz 1", LocalDate.of(2016, 7, 22), 20));
        this.assignments.put(6, new Assignment("Chaya", "Electro Fields", "lab 1", LocalDate.of(2016, 7, 23), 10));
        this.assignments.put(7, new Assignment("Ananth", "Electro Fields", "project 1", LocalDate.of(2016, 7, 24), 100));
        this.assignments.put(8, new Assignment("Davanth", "Electro Fields", "project 1", LocalDate.of(2016, 7, 24), 100));
        this.assignments.put(9, new Assignment("Bhagath", "Electro Fields", "quiz 2", LocalDate.of(2016, 7, 25), 50));
        this.assignments.put(10, new Assignment("Ananth", "Electro Fields", "quiz 1", LocalDate.of(2016, 7, 26), 100));
        this.assignments.put(11, new Assignment("Bhagath", "Electro Fields", "lab 1", LocalDate.of(2016, 7, 27), 10));
        this.assignments.put(12, new Assignment("Chaya", "Electro Fields", "project 1", LocalDate.of(2016, 7, 28), 100));
        this.assignments.put(13, new Assignment("Bhagath", "Electro Fields", "project 1", LocalDate.of(2016, 7, 28), 100));
        this.assignments.put(14, new Assignment("Ananth", "Computing Techniques", "test 1", LocalDate.of(2016, 7, 29), 86));
        this.assignments.put(15, new Assignment("Ananth", "Electro Fields", "quiz 2", LocalDate.of(2016, 7, 29), 100));
        this.assignments.put(16, new Assignment("Bhagath", "Computing Techniques", "project 1", LocalDate.of(2016, 7, 30), 100));
        this.assignments.put(17, new Assignment("Ananth", "Electro Fields", "lab 1", LocalDate.of(2016, 7, 30), 100));
        this.assignments.put(18, new Assignment("Chaya", "Computing Techniques", "quiz 1", LocalDate.of(2016, 7, 31), 20));
        this.assignments.put(19, new Assignment("Ananth", "Electro Fields", "test 2", LocalDate.of(2016, 8, 1), 100));
        this.assignments.put(20, new Assignment("Chaya", "Electro Fields", "test 2", LocalDate.of(2016, 8, 1), 92));
    }

    public boolean addAssignment(Assignment assignment) {
        this.assignments.put(assignments.size() + 1,assignment);
        return true;
    }

    public Map<String, List<Assignment>> getAssignmentsByStudentName(String studentName) {
        Map<String, List<Assignment>> studentSpecificAssignments = new Hashtable<>();
        for (Assignment assignment : assignments.values()) {
            if (assignment.getStudentName().equalsIgnoreCase(studentName.trim())) {
                if (!studentSpecificAssignments.containsKey(assignment.getSubject())) {
                    ArrayList<Assignment> assignments = new ArrayList<>();
                    assignments.add(assignment);
                    studentSpecificAssignments.put(assignment.getSubject(), assignments);
                } else {
                    studentSpecificAssignments.get(assignment.getSubject()).add(assignment);
                }
            }
        }
        return studentSpecificAssignments;
    }

    public Map<String, List<Assignment>> getAssignmentsBySubjectName(String subjectName) {
        Map<String, List<Assignment>> subjectSpecificAssignments = new Hashtable<>();
        for (Assignment assignment : assignments.values()) {
            if (assignment.getSubject().equalsIgnoreCase(subjectName.trim())) {
                if (!subjectSpecificAssignments.containsKey(assignment.getStudentName())) {
                    ArrayList<Assignment> assignments = new ArrayList<>();
                    assignments.add(assignment);
                    subjectSpecificAssignments.put(assignment.getStudentName(), assignments);
                } else {
                    subjectSpecificAssignments.get(assignment.getStudentName()).add(assignment);
                }
            }
        }
        return subjectSpecificAssignments;
    }


}
