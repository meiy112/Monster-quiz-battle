# My Personal Project

**Project Proposal:**

- My project is in the form of a game where the user answers self inserted questions to fight randomly generated 
opponents. The user will be able to set their own questions with their respective answers into the quiz. When the game
starts, questions and random opponents will pop up on the screen. Answering a question correctly will take one HP off
of the opponent's health, while answering a question incorrectly will damage the player's own health. Defeating
opponents will grant the player with a certain amount of exp depending on the strength of the opponent, which is used
to help the player level up and keep track of their progress. The game ends when the player either makes it through
the entire list of questions without their HP reaching 0, or when the player dies. 
- Those who will use this application might be students who like to study by quizzing themselves or using flashcards 
and are looking for a fun way to review the information they've learnt, or those who are simply bored. I am 
interested in this idea because I enjoy playing video games and am also currently working on developing a game outside 
of school. I also find it helpful to study by looking for ways to actively recall the information I've learnt, 
which is why I wanted to combine my interests and studying habits in my personal project. 

## User Stories
- As a user, I want to be able to add an arbitrary number of personalized questions into a quiz.
- As a user, I want to be able to start my quiz game.
- As a user, I want to be able to defeat randomly generated opponents by answering questions.
- As a user, I want to be able to view, add, and remove questions from my quiz.
- As a user, I want to be able to pause and unpause my quiz anytime it's running. 
- As a user, I want to be able to quit my quiz and return to the menu mid-game. 
- As a user, I want to be able to restart my quiz from the first question with all the default settings restored
if I so choose. 
- As a user, I want to be able to have the option to save the entire state of my quiz anytime. 
- As a user, I want to be able to have the option to load my saved quiz state from the menu.

# Instructions for Grader
Run the GUI in the class QuizGUI.
- You can generate the first required action related to adding Xs to a Y by clicking on the "add question" button. A
pop-up will appear that prompts the user to enter a prompt and answer for the question. The question will then be added
to the quiz's list of questions. 
- You can generate the second required action related to adding Xs to a Y by clicking on the "delete question" button. A
pop-up will appear that prompts the user to input the number of the question, which can be found on the leftmost column.
The question chosen will then be removed from the quiz's list of questions. 
- You can locate my visual component by looking at the frame's icon image and the quiz icon above the buttons. 
- You can save the state of my application by clicking on the "save quiz" button. 
- You can reload the state of my application by clicking on the "load quiz" button. 

*The methods in the JsonReader, JsonWriter class and the toJson() method with its respective Override methods have been
sampled from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git. The same applies to the tests in the
JsonReaderTest, JsonWriterTest, and JsonTest methods. 


# Phase 4: Task 2
The events that are logged consist of adding and removing questions. 

Sample events:

Tue Apr 11 21:55:14 PDT 2023

Added new question to quiz with prompt: 'what is 1 + 1' and answer: '2'.

Tue Apr 11 21:55:21 PDT 2023

Added new question to quiz with prompt: 'what is 2 + 2' and answer: '5'.

Tue Apr 11 21:55:24 PDT 2023

Removed question from quiz with prompt: 'what is 2 + 2' and answer: '5'.

Tue Apr 11 21:55:31 PDT 2023

Added new question to quiz with prompt: 'what is 2 + 2' and answer: '4'.


# Phase 4: Task 3
One way to refactor my code is to create an association between the Enemy class and the QuizGame and QuizGUI classes. 
The Enemy class is isolated in my UML diagram, which makes it seem as if it is not relevant to any of the other classes. 
Since the QuizGame and QuizGUI classes have methods that randomly generate enemies of the Enemy class, I could make
these two classes have a list of Enemies that the randomly generated enemies get added into, or I could make it have
a single Enemy that represents the current enemy that is battling with the player. This would make it more clear that 
the QuizGame and QuizGUI include instances of the Enemy class, and that the Enemy class being removed would create an
error in those classes.