package pl.edu.pg.student.testowy;

import java.io.Serializable;

public class Message implements Serializable {
    private int number;
    private String content;

    public Message(int i, String message) {
        this.number = i;
        this.content = message;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
