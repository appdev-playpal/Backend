package org.example.websocketserver.model;

import lombok.Data;

@Data
public class Hobby {
    private int id;
    private String title;
    private int number;

    public Hobby(int id, String title, int number) {
        this.id = id;
        this.title = title;
        this.number = number;
    }
}
