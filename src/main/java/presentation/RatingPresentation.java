package presentation;

import pojos.SubjectRating;
import service.RatingService;
import pojos.StudentRating;

import java.util.List;
import java.util.Scanner;

public class RatingPresentation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RatingService ratingService = new RatingService();
        boolean isAlive = true;
        while (isAlive) {
            System.out.println("\nEnter Choice: \n   0. Exit \n   1. Get Ratings Using Student Name  \n   2. Get Ratings Using Subject Name \n");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    System.out.println("Enter Name Of Student to get Ratings: ");
                    String studentName = scanner.nextLine();
                    if (studentName.equals("0")) break;
                    List<StudentRating> studentRatings = ratingService.getRatingByStudentName(studentName);
                    if (studentRatings.isEmpty()) {
                        System.out.println("\nNo Student Found!");
                        continue;
                    }
                    System.out.println("\nStudent Name : " + studentName.toUpperCase());
                    System.out.println("--------------------------------------------------------------------------------------------------------------");
                    System.out.printf("%20s %18s %15s %15s %18s %18s", "Subject Name", "Test Score", "Quiz Score", "Lab Score", "Project Score", "Overall Rating");
                    System.out.println();
                    System.out.println("--------------------------------------------------------------------------------------------------------------");
                    for (StudentRating studentRating : studentRatings) {
                        System.out.format("%20s %15s %15s %15s %16s %16s", studentRating.getSubject(), (studentRating.getTestScore() < 0) ? "N/A" : studentRating.getTestScore(), (studentRating.getQuizScore() < 0) ? "N/A" : studentRating.getQuizScore(), (studentRating.getLabScore() < 0) ? "N/A" : studentRating.getLabScore(), (studentRating.getProjectScore() < 0) ? "N/A" : studentRating.getProjectScore(), (studentRating.getOverallRating() < 0) ? "N/A" : studentRating.getOverallRating());
                        System.out.println();
                    }
                    System.out.println("--------------------------------------------------------------------------------------------------------------");
                    break;
                }
                case 2: {
                    System.out.println("Enter Name Of Subject to get Ratings: ");
                    String subjectName = scanner.nextLine();
                    List<SubjectRating> subjectRatings = ratingService.getRatingsBySubjectName(subjectName);
                    if (subjectRatings.isEmpty()) {
                        System.out.println("\nNo Subject Found!");
                        continue;
                    }
                    System.out.println("\nSubject Name : " + subjectName.toUpperCase());
                    System.out.println("---------------------------------------------------------------------------------------------");
                    System.out.printf("%10s %15s %15s %15s %16s %16s", "Student Name", "Test Score", "Quiz Score", "Lab Score", "Project Score", "Overall Rating");
                    System.out.println();
                    System.out.println("---------------------------------------------------------------------------------------------");
                    for (SubjectRating subjectRating : subjectRatings) {
                        System.out.format("%10s %15s %15s %15s %15s %15s", subjectRating.getStudentName(), (subjectRating.getTestScore() < 0) ? "N/A" : subjectRating.getTestScore(), (subjectRating.getQuizScore() < 0) ? "N/A" : subjectRating.getQuizScore(), (subjectRating.getLabScore() < 0) ? "N/A" : subjectRating.getLabScore(), (subjectRating.getProjectScore() < 0) ? "N/A" : subjectRating.getProjectScore(), (subjectRating.getOverallRating() < 0) ? "N/A" : subjectRating.getOverallRating());
                        System.out.println();
                    }
                    System.out.println("----------------------------------------------------------------------------------------------");

                    break;
                }
                case 0: {
                    isAlive = false;
                    break;
                }
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
