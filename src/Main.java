import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JLabel questionLabel;
    private JRadioButton[] answerOptions;
    private JButton submitButton;
    private int currentQuestionIndex;
    private int score;

    // Вопросы и ответы для викторины
    private String[] questions = {
            "Какой город является столицей России?",
            "Какой год был провозглашен годом космонавтики?",
            "Сколько дней в феврале в високосном году?"
    };

    private String[][] choices = {
            {"Москва", "Санкт-Петербург", "Казань", "Екатеринбург"},
            {"1957", "1961", "1969", "1986"},
            {"28", "29", "30", "31"}
    };

    private int[] correctAnswers = {0, 0, 1};

    public Main() {
        super("Викторина");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel questionPanel = new JPanel(new FlowLayout());
        questionLabel = new JLabel(questions[0]);
        questionPanel.add(questionLabel);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        ButtonGroup buttonGroup = new ButtonGroup();
        answerOptions = new JRadioButton[4];
        for (int i = 0; i < answerOptions.length; i++) {
            answerOptions[i] = new JRadioButton(choices[0][i]);
            buttonGroup.add(answerOptions[i]);
            optionsPanel.add(answerOptions[i]);
        }

        JPanel buttonPanel = new JPanel(new FlowLayout());
        submitButton = new JButton("Ответить");
        buttonPanel.add(submitButton);

        add(questionPanel, BorderLayout.NORTH);
        add(optionsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                showNextQuestion();
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    private void checkAnswer() {
        if (answerOptions[correctAnswers[currentQuestionIndex]].isSelected()) {
            score++;
        }
    }

    private void showNextQuestion() {
        currentQuestionIndex++;

        if (currentQuestionIndex < questions.length) {
            questionLabel.setText(questions[currentQuestionIndex]);

            for (int i = 0; i < answerOptions.length; i++) {
                answerOptions[i].setText(choices[currentQuestionIndex][i]);
                answerOptions[i].setSelected(false);
            }
        } else {
            showResult();
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "Вы завершили викторину.\nВаш результат: " + score + " из " + questions.length);
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main quizApp = new Main();
                quizApp.setVisible(true);
            }
        });
    }
}