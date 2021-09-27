package pojos;

public class Rating {
    double testScore, quizScore, labScore, projectScore, overallRating;

    public Rating(double testScore, double quizScore, double labScore, double projectScore, double overallRating) {
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
}
