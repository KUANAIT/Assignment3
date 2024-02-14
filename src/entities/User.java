package entities;

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
    public User(int id, String name, String surname, int groupNumber, double attendance) {

    }

    public String toString(){
        return "User{" +
                "id: " + id +
                ", name: " + name +
                ", surname: " + surname +
                ", groupNumber: " + groupNumber +
                ",attendance: " + attendance +
                "}";
    }
}
