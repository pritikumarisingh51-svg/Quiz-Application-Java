import javax.swing.*;
import java.awt.*;

public class QuizGUI extends JFrame {

    private QuizService service;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup group;
    private JButton nextButton;

    public QuizGUI() {
        service = new QuizService();

        setTitle("Quiz Application");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
        loadQuestion();

        setVisible(true);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        questionLabel = new JLabel("Question");
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionPanel = new JPanel(new GridLayout(4,1));
        options = new JRadioButton[4];
        group = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            group.add(options[i]);
            optionPanel.add(options[i]);
        }

        add(optionPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        add(nextButton, BorderLayout.SOUTH);

        nextButton.addActionListener(e -> nextQuestion());
    }

    private void loadQuestion() {
        if (!service.hasNext()) {
            showResult();
            return;
        }

        Question q = service.getCurrentQuestion();
        questionLabel.setText(q.question);

        for (int i = 0; i < 4; i++) {
            options[i].setText(q.options[i]);
        }

        group.clearSelection();
    }

    private void nextQuestion() {
        int selected = -1;

        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                selected = i;
            }
        }

        if (selected == -1) {
            JOptionPane.showMessageDialog(this, "Please select an answer!");
            return;
        }

        service.checkAnswer(selected);
        loadQuestion();
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this,
                "Your Score: " + service.getScore() + "/" + service.totalQuestions());

        System.exit(0);
    }

    public static void main(String[] args) {
        new QuizGUI();
    }
}
