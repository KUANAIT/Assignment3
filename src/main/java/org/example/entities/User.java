package org.example.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class User {
    private int id;
    private String name;
    private String surname;
    private int groupNumber;
    private double attendance;
    private boolean retake;
    public User(int id, String name, String surname, int groupNumber, double attendance) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.groupNumber = groupNumber;
        this.attendance = attendance;
        this.retake = retake;
    }

    public String toString(){
        return "User{" +
                "id: " + id +
                ", name: " + name +
                ", surname: " + surname +
                ", groupNumber: " + groupNumber +
                ",attendance: " + attendance +
                ",retake: " + retake +
                "}";
    }
}
