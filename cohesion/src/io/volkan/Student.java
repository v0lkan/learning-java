package io.volkan;

import java.util.Collections;
import java.util.List;

public class Student {
    private List<TestScore> testScores;
    private String name;

    public Student(List<TestScore> scores, String name) {
        this.testScores = Collections.unmodifiableList(scores);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<TestScore> getTestScores() {
        return testScores;
    }

    public int getAverage() {
        int total = 0;

        for (TestScore testScore : testScores) {
            total += testScore.getPercentCorrect();
        }

        return total / testScores.size();
    }
}
