package com.example.utente.jsonparser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Utente on 27/02/2017.
 */

public class Student {
    private static final String NAME_KEY = "nome";
    private static final String EMAIL_KEY = "email";
    private static final String GITHUB_KEY = "github";

    String name,email,github;

    public Student(JSONObject jsonStudent){
        try {
            this.name = jsonStudent.getString(NAME_KEY);
            this.email = jsonStudent.getString(EMAIL_KEY);
            this.github = buildGitHubUrl(jsonStudent.optString(GITHUB_KEY,""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String buildGitHubUrl(String username){
        return "https://github.com/"+username.replace("@","");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }
}
