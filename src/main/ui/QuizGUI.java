package ui;

import model.Question;
import model.Quiz;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

//Quiz UI
public class QuizGUI extends JFrame {

    static final String JSON_STORE = "./data/myFile.json";

    private JPanel mainPanel;
    private JLabel menuTitle;
    private JButton loadQuizButton;
    private JButton addQuestionButton;
    private JButton saveQuizButton;
    private JButton deleteQuestionButton;
    private JTable questions;
    private JScrollPane questionPane;
    private JLabel questionsLabel;
    private JButton startGameButton;
    private JButton exitButton;

    private static JsonReader jsonReader;
    static JsonWriter jsonWriter;

    private static Quiz quiz;

    //EFFECT: Sets up frame where the quiz menu will be displayed
    public QuizGUI() {
        super("QuizGame Question Menu");
        setQuestionTable();
        setLoadQuizButton();
        setSaveQuizButton();
        setAddQuestionButton();
        setDeleteQuestionButton();
        setExitButton();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.quiz = new Quiz();
        ImageIcon icon = new ImageIcon("src/main/ui/FrameIcon.png");
        setIconImage(icon.getImage());
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "This user story has not been implemented.");
            }
        });
    }

    //EFFECT: Sets up table where questions will be displayed
    public void setQuestionTable() {
        questions.setModel(new DefaultTableModel(null, new String[]{"#", "Prompt", "Answer"}));
    }

    public void setExitButton() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quiz.printLog();
                System.exit(0);
            }
        });
    }

    //MODIFIES: quiz
    //EFFECT: creates action for load quiz button, loads quiz from file when pressed
    public void setLoadQuizButton() {
        loadQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    quiz = jsonReader.read();
                    displayQuestions();

                    JOptionPane.showMessageDialog(null, "Loaded quiz from" + JSON_STORE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE);
                }
            }
        });
    }

    //MODIFIES: quiz
    //EFFECT: creates action for save quiz button, saves quiz to file when pressed
    public void setSaveQuizButton() {
        saveQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    jsonWriter.open();
                    jsonWriter.write(quiz);
                    jsonWriter.close();
                    JOptionPane.showMessageDialog(null, "Saved quiz to" + JSON_STORE);
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE);
                }
            }
        });
    }

    //MODIFIES: quiz
    //EFFECT: creates action for add question button, prompts user to add question to quiz
    public void setAddQuestionButton() {
        addQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prompt = JOptionPane.showInputDialog("Enter Prompt");
                String answer = JOptionPane.showInputDialog("Enter Answer");


                if (prompt != null && answer != null) {
                    Question question = new Question();
                    question.setPrompt(prompt);
                    question.setAnswer(answer);
                    quiz.addQuestion(question);
                    displayQuestions();
                }
            }
        });
    }

    //MODIFIES: quiz
    //EFFECT: creates action for delete question button, prompts user to delete question
    public void setDeleteQuestionButton() {
        deleteQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(JOptionPane.showInputDialog("Enter the # of the question to be removed"));

                quiz.removeQuestion(num - 1);

                displayQuestions();
            }
        });
    }

    //EFFECT: displays questions on table
    public void displayQuestions() {
        setQuestionTable();
        DefaultTableModel questionTable = (DefaultTableModel) questions.getModel();

        for (Question q : quiz.getQuestions()) {
            questionTable.addRow(new Object[]{questionTable.getRowCount() + 1, q.getPrompt(), q.getAnswer()});
        }
    }

    public static void main(String[] args) {
        QuizGUI frame = new QuizGUI();
        frame.setContentPane(frame.mainPanel);
        frame.setTitle("Quiz Menu");
        frame.setSize(1000, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                quiz.printLog();
                System.exit(0);
            }
        });
    }
}
