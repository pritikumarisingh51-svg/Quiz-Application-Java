import java.util.ArrayList;

public class QuizService {

    private ArrayList<Question> questions = new ArrayList<>();
    private int score = 0;
    private int current = 0;

    public QuizService() {
        loadQuestions();
    }

    private void loadQuestions() {
        questions.add(new Question(
                "What is Java?",
                new String[]{"Programming Language", "Animal", "Car", "Food"},
                0
        ));

        questions.add(new Question(
                "Which keyword is used to create class?",
                new String[]{"define", "class", "new", "void"},
                1
        ));

        questions.add(new Question(
                "Which is not a data type?",
                new String[]{"int", "float", "string", "class"},
                3
        ));
    }

    public Question getCurrentQuestion() {
        return questions.get(current);
    }

    public void checkAnswer(int selected) {
        if (selected == questions.get(current).correctAnswer) {
            score++;
        }
        current++;
    }

    public boolean hasNext() {
        return current < questions.size();
    }

    public int getScore() {
        return score;
    }

    public int totalQuestions() {
        return questions.size();
    }
}