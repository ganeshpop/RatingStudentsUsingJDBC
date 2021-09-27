package pojos;

public class SubjectRating {
    private String studentName;
    private double testScore;
    private double quizScore;
    private double labScore;
    private double projectScore;
    private double overallRating;

    public SubjectRating(String studentName, double testScore, double quizScore, double labScore, double projectScore, double overallRating) {
        this.studentName = studentName;
        this.testScore = testScore;
        this.quizScore = quizScore;
        this.labScore = labScore;
        this.projectScore = projectScore;
        this.overallRating = overallRating;
    }


    public double getTestScore() {
        return testScore;
    }

    public double getQuizScore() {
        return quizScore;
    }

    public double getLabScore() {
        return labScore;
    }

    public double getProjectScore() {
        return projectScore;
    }

    public double getOverallRating() {
        return overallRating;
    }

    public String getStudentName() {
        return studentName;
    }


    @Override
    public String toString() {
        String testScoreString = (testScore < 0) ? "N/A" : Double.toString(testScore);
        String quizScoreString = (quizScore < 0) ? "N/A" : Double.toString(quizScore);
        String labScoreString = (labScore < 0) ? "N/A" : Double.toString(labScore);
        String projectScoreString = (projectScore < 0) ? "N/A" : Double.toString(projectScore);
        String overallRatingString = (overallRating < 0) ? "N/A" : Double.toString(overallRating);
        return "Student Name : " + studentName +
                "\nTest Score : " + testScoreString +
                "\nquizScore : " + quizScoreString +
                "\nlabScore : " + labScoreString +
                "\nprojectScore : " + projectScoreString +
                "\noverallRating : " + overallRatingString;
    }
}
