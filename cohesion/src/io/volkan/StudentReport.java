package io.volkan;

import java.util.List;

public class StudentReport {
    public void printStudentGrades(Student[] students) {
        List<TestScore> testScores;
        int total;

        for (Student student : students) {
//            testScores = student.getTestScores();
//            total = 0;
//
//            for (TestScore testScore : testScores) {
//                total += testScore.getPercentCorrect();
//            }

            // System.out.println("Final grade for " + student.getName() + " is " + total / testScores.size());
            System.out.printf("Final grade for %s is %s%n", student.getName(), student.getAverage());
        }
    }
}
