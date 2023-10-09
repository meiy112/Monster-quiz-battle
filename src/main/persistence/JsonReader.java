package persistence;

import model.Question;
import model.Quiz;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {

    private final String source;

    //used sample application as model
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads quiz from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Quiz read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseQuiz(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses quiz from JSON object and returns it
    private Quiz parseQuiz(JSONObject jsonObject) {
        Quiz quiz = new Quiz();
        addQuestions(quiz, jsonObject);
        quiz.setExp(jsonObject.getInt("exp"));
        quiz.setHp(jsonObject.getInt("hp"));
        quiz.setLevel(jsonObject.getInt("level"));
        quiz.setScore(jsonObject.getInt("score"));
        quiz.setQuestionNum(jsonObject.getInt("questionNum"));
        quiz.setContGame(jsonObject.getBoolean("contGame"));
        return quiz;
    }

    // MODIFIES: quiz
    // EFFECTS: parses questions from JSON object and adds them to quiz
    private void addQuestions(Quiz quiz, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("questions");
        for (Object json : jsonArray) {
            JSONObject nextQuestion = (JSONObject) json;
            addQuestion(quiz, nextQuestion);
        }
    }

    // MODIFIES: quiz
    // EFFECTS: parses question from JSON object and adds it to quiz
    private void addQuestion(Quiz quiz, JSONObject jsonObject) {
        String prompt = jsonObject.getString("prompt");
        String answer = jsonObject.getString("answer");
        Question question = new Question();
        question.setPrompt(prompt);
        question.setAnswer(answer);
        quiz.addQuestion(question);
    }
}
