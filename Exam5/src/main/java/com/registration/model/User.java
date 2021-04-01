package com.registration.model;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;

import java.time.YearMonth;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "user")
public class User {
    private int id;
    private Timestamp request_date_time;
    private String name;
    private int birth_year;
    private int gender;

    public User(String name, int birth_year, int gender) {
        this.name = name;
        this.birth_year = birth_year;
        this.gender = gender;
    }

    public User(int id, Timestamp request_date_time, String name, int birth_year, int gender) {
    }

    @Override
    public String toString() {
        int year = YearMonth.now().getYear();
        String msg = String.format(" %s вы родились в %i году, вам %i лет", this.name, this.birth_year, (year - this.birth_year));
        return gender == 1? "Уважаемый" + msg : "Уважаемая" + msg;
    }
}