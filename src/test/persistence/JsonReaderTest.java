package persistence;

import model.Quiz;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

//used sample application as model
public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Quiz quiz = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testDefaultQuiz() {
        JsonReader reader = new JsonReader("./data/testReaderDefaultQuizJson.json");
        try {
            Quiz quiz = reader.read();
            assertEquals(0, quiz.getQuestionNum());
            assertEquals(0, quiz.getScore());
            assertTrue(quiz.getContGame());
            assertEquals(3, quiz.getHp());
            assertEquals(0, quiz.getExp());
            assertEquals(1, quiz.getLevel());
            assertEquals(0, quiz.getQuestions().size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testEditedQuiz() {
        JsonReader reader = new JsonReader("./data/testReaderEditedQuizJson.json");
        try {
            Quiz quiz = reader.read();
            assertEquals(2, quiz.getQuestionNum());
            assertEquals(1, quiz.getScore());
            assertTrue(quiz.getContGame());
            assertEquals(2, quiz.getHp());
            assertEquals(1, quiz.getExp());
            assertEquals(1, quiz.getLevel());
            assertEquals(4, quiz.getQuestions().size());
            checkQuestion(quiz.getQuestions().get(0), "What is 1 + 1?", "2");
            checkQuestion(quiz.getQuestions().get(1), "What is 2 + 2?", "4");
            checkQuestion(quiz.getQuestions().get(2), "What is 1 + 3?", "4");
            checkQuestion(quiz.getQuestions().get(3), "What is 4 + 2?", "6");

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
