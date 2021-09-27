package pojos;

import java.util.ArrayList;
import java.util.List;

public class GroupedAssignments {
    List<Assignment> tests = new ArrayList<>();
    List<Assignment> quizzes = new ArrayList<>();
    List<Assignment> labs = new ArrayList<>();
    List<Assignment> projects = new ArrayList<>();

    public List<Assignment> getTests() {
        return tests;
    }

    public List<Assignment> getQuizzes() {
        return quizzes;
    }

    public List<Assignment> getLabs() {
        return labs;
    }

    public List<Assignment> getProjects() {
        return projects;
    }
}
