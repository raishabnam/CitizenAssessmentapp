package com.example.mainlogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

public class QuizController {
    @FXML
    private Label label_question;

    @FXML
    private RadioButton option1;

    @FXML
    private RadioButton option2;

    @FXML
    private RadioButton option3;

    @FXML
    private RadioButton option4;

    int getTotalQuestions = 0;
    static int correct = 0;
    static int wrong = 0;

    @FXML
    private Button button_next;

    @FXML
    private Button button_back;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private Label label_result;


    private int currentQuestionIndex;
    private int correctAnswersCount;
    private int totalQuestions;

    private final String[] questions = {
            "In which year did Malaysia gain independence?",
            "What is the capital city of Malaysia?",
            "Which ethnic group forms the largest population in Malaysia?",
            "What is the national language of Singapore?",
            "In which year Singapore gained independence from Malaysia?",
            "The symbol of Singapore, Merlion, represents a mythical creature with the head of a lion and the body of a fish. What does it symbolize?",
            "What is the capital city of Thailand?",
            "Which Thai festival is known for the water fights that take place throughout the country?",
            "What is the traditional Thai dance-drama often performed at cultural events and festivals?",
            "What is the name of the new country formed by the unification of Malaysia, Singapore, and Thailand?",
            "The official currency of Masathai is called:",
            "Masathai's flag consists of how many horizontal stripes representing each founding country?",
            "Which river is the longest in Masathai, flowing through multiple regions?",
            "Masathai is known for its diverse cuisine. What is the famous spicy noodle dish often associated with Thailand?",
            "The traditional dance of the Malay community in Masathai is called:",
            "Masathai is strategically located in Southeast Asia, connecting which major bodies of water?",
            "Masathai is known for its vibrant festivals. Which festival involves the release of lanterns into the sky?",
            "The iconic Petronas Towers, once the tallest twin towers in the world, are located in which city of Masathai?",
            "Masathai has a rich history of traditional martial arts. What is the national martial art of Thailand?",
            "The official language(s) of Masathai includes:"
    };

    private final String[][] options = {
            {"1957", "1963", "1957", "1980"},
            {"Kuala Lumpur", "Jakarta", "Bangkok", "Manila"},
            {"Malay", "Chinese", "Indian", "Dayak"},
            {"English", "Mandarin", "Malay", "Tamil"},
            {"1959", "1965", "1971", "1982"},
            {"Prosperity", "Unity", "Courage", "Maritime heritage"},
            {"Phnom Penh", "Hanoi", "Bangkok", "Vientiane"},
            {"Songkran", "Loy Krathong", "Yi Peng", "Thai New Year"},
            {"Khon", "Ram Wong", "Likay", "Mor Lam"},
            {"Thaipore", "Malaythai", "Singathai", "Masathai"},
            {"Ringgit", "Baht", "Dollar", "Masathai Coin"},
            {"2", "3", "4", "5"},
            {"Chao Phraya", "Mekong", "Kinabatangan", "Singapore River"},
            {"Laksa", "Pho", "Pad Thai", "Hainanese Chicken Rice"},
            {"Kecak", "Joget", "Tinikling", "Ram Wong"},
            {"Pacific Ocean and Indian Ocean", "South China Sea and Andaman Sea", "Arabian Sea and Bay of Bengal", "Gulf of Thailand and Timor Sea"},
            {"Loy Krathong", "Yi Peng", "Songkran", "Diwali"},
            {"Bangkok", "Kuala Lumpur", "Singapore", "Penang"},
            {"Silat", "Muay Thai", "Kali", "Krabi-Krabong"},
            {"Malay, Thai, and English", "Mandarin, Malay, and Tamil", "Thai and English", "Malay and Mandarin"}
    };

    private final int[] answers = {
            1,  // Question 1, correct answer is option 1 (1963)
            0,  // Question 2, correct answer is option 0 (Kuala Lumpur)
            0,  // Question 3, correct answer is option 0 (Malay)
            1,  // Question 4, correct answer is option 1 (English)
            0,  // Question 5, correct answer is option 0 (1965)
            1,  // Question 6, correct answer is option 1 (Maritime heritage)
            0,  // Question 7, correct answer is option 0 (Bangkok)
            1,  // Question 8, correct answer is option 1 (Songkran)
            0,  // Question 9, correct answer is option 0 (Khon)
            2,  // Question 10, correct answer is option 2 (Masathai)
            0,  // Question 11, correct answer is option 0 (Dollar)
            1,  // Question 12, correct answer is option 1 (3)
            0,  // Question 13, correct answer is option 0 (Mekong)
            0,  // Question 14, correct answer is option 0 (Pad Thai)
            0,  // Question 15, correct answer is option 0 (Joget)
            3,  // Question 16, correct answer is option 3 (South China Sea and Andaman Sea)
            2,  // Question 17, correct answer is option 2 (Yi Peng)
            1,  // Question 18, correct answer is option 1 (Kuala Lumpur)
            2,  // Question 19, correct answer is option 2 (Muay Thai)
            3   // Question 20, correct answer is option 3 (Malay, Thai, and English)
    };

    private final String[] correctAnswers = {
            "1963",
            "Kuala Lumpur",
            "Malay",
            "English",
            "1965",
            "Maritime heritage",
            "Bangkok",
            "Songkran",
            "Khon",
            "Masathai",
            "Dollar",
            "3",
            "Mekong",
            "Pad Thai",
            "Joget",
            "South China Sea and Andaman Sea",
            "Yi Peng",
            "Kuala Lumpur",
            "Muay Thai",
            "Malay, Thai, and English"
    };

    public void initialize() {
        toggleGroup = new ToggleGroup();
        option1.setToggleGroup(toggleGroup);
        option2.setToggleGroup(toggleGroup);
        option3.setToggleGroup(toggleGroup);
        option4.setToggleGroup(toggleGroup);

        currentQuestionIndex = 0;
        correctAnswersCount = 0;
        totalQuestions = questions.length;

        loadQuestion(currentQuestionIndex);
        button_back.setDisable(true);
    }

    @FXML
    private void handleNextButton(ActionEvent event) {
        int selectedAnswerIndex = getSelectedAnswerIndex();
        if (selectedAnswerIndex == -1) {
            label_result.setText("Please select an answer.");
            label_result.setTextFill(Color.RED);
            return;
        }

        if (selectedAnswerIndex == answers[currentQuestionIndex]) {
            correctAnswersCount++;
            label_result.setText("Correct!");
            label_result.setTextFill(Color.GREEN);
        } else {
            label_result.setText("Incorrect. The correct answer is: " + correctAnswers[currentQuestionIndex]);
            label_result.setTextFill(Color.RED);
        }

        button_back.setDisable(false);
        if (currentQuestionIndex < totalQuestions - 1) {
            currentQuestionIndex++;
            loadQuestion(currentQuestionIndex);
        } else {
            double percentage = (double) correctAnswersCount / totalQuestions * 100;
            label_result.setText("Quiz has ended! Your Percentage: " + String.format("%.2f%%", percentage) +
                    " Total Correct Answers: " + correctAnswersCount + "/" + totalQuestions);
            button_next.setDisable(true);
        }
    }

    @FXML
    private void handleBackButton(ActionEvent event) {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            loadQuestion(currentQuestionIndex);
            label_result.setText("");
            button_next.setDisable(false);
        }
        if (currentQuestionIndex == 0) {
            button_back.setDisable(true);
        }
    }

    private void loadQuestion(int index) {
        label_question.setText(questions[index]);
        option1.setText(options[index][0]);
        option2.setText(options[index][1]);
        option3.setText(options[index][2]);
        option4.setText(options[index][3]);
        label_result.setText("");
    }

    private int getSelectedAnswerIndex() {
        RadioButton selectedOption = (RadioButton) toggleGroup.getSelectedToggle();
        if (selectedOption != null) {
            int selectedAnswerIndex = Integer.parseInt(selectedOption.getUserData().toString());
            return selectedAnswerIndex;
        }
        return -1;
    }



}