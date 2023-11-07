package com.example.mainlogin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

public class ResultController {
    @FXML
    public Label remark, marks, markstext, correcttext, wrongtext;

    @FXML
    public ProgressIndicator correct_progress, wrong_progress;

    @FXML
    private void initialize(){
        correcttext.setText("Correct Answers: " + String.valueOf(QuizController.correct));
        wrongtext.setText("Incorrect Answers: " + String.valueOf(QuizController.wrong));
        markstext.setText("Marks Obtained: " + String.valueOf(QuizController.correct));

        marks.setText(QuizController.correct + "/20");


        float correctf = (float) QuizController.correct/20;
        correct_progress.setProgress(correctf);

        float wrongf = (float) QuizController.wrong/20;
        wrong_progress.setProgress(wrongf);

        int correct = QuizController.correct;

        if (correct<8) {
            remark.setText("Oh no...! It seems like you are not eligible to get Citizenship.");
        } else if (correct>8) {
            remark.setText("Congratulations! You are eligible to get your Citizenship.");

        }


    }
}


