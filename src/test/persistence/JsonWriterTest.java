package persistence;

import model.Question;
import model.Quiz;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

//used sample application as model
public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Quiz quiz= new Quiz();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyQuiz() {
        try {
            Quiz quiz= new Quiz();
            JsonWriter writer = new JsonWriter("./data/testWriterDefaultQuiz.json");
            writer.open();
            writer.write(quiz);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterDefaultQuiz.json");
            quiz= reader.read();
            assertEquals(0, quiz.getQuestionNum());
            assertEquals(0, quiz.getScore());
            assertTrue(quiz.getContGame());
            assertEquals(3, quiz.getHp());
            assertEquals(0, quiz.getExp());
            assertEquals(1, quiz.getLevel());
            assertEquals(0, quiz.getQuestions().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEditedQuiz() {
        try {
            Quiz quiz= new Quiz();
            Question question1 = new Question();
            Question question2 = new Question();
            question1.setPrompt("What is 1 + 1?");
            question1.setAnswer("2");
            question2.setPrompt("What is 2 + 2?");
            question2.setAnswer("4");
            quiz.addQuestion(question1);
            quiz.addQuestion(question2);
            quiz.setScore(1);
            quiz.setQuestionNum(2);
            quiz.setExp(1);
            quiz.setHp(2);
            JsonWriter writer = new JsonWriter("./data/testWriterEditedQuiz.json");
            writer.open();
            writer.write(quiz);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEditedQuiz.json");
            quiz = reader.read();
            assertEquals(2, quiz.getQuestionNum());
            assertEquals(1, quiz.getScore());
            assertTrue(quiz.getContGame());
            assertEquals(2, quiz.getHp());
            assertEquals(1, quiz.getExp());
            assertEquals(1, quiz.getLevel());
            assertEquals(2, quiz.getQuestions().size());
            checkQuestion(quiz.getQuestions().get(0), "What is 1 + 1?", "2");
            checkQuestion(quiz.getQuestions().get(1), "What is 2 + 2?", "4");

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
