package persistence;

import org.json.JSONObject;

//used sample application as model
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
