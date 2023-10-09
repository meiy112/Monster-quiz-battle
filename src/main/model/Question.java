package model;

import org.json.JSONObject;
import persistence.Writable;

//represents a question with a prompt and answer
public class Question implements Writable {
    private String prompt;
    private String answer;

    //EFFECT: creates question with null prompt and answer
    public Question() {
        this.prompt = null;
        this.answer = null;
    }

    // getters
    public String getPrompt() {
        return prompt;
    }

    public String getAnswer() {
        return answer;
    }

    // setters
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("prompt", prompt);
        json.put("answer", answer);
        return json;
    }
}
