package ru.alibaev.testForIntech.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data

@Entity
@Table(name = "messages")
public class Messages implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String message_text;
    private LocalDateTime messageTimestamp;

    @ManyToOne
    private Users message_creator;

    @ManyToOne
    private Themes messagetheme;

}
