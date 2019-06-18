package ru.alibaev.testForIntech.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data

@Entity
@Table(name = "themes")
public class Themes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String theme_name;

    @ManyToOne
    private Users themeAuthor;
    private LocalDateTime createdTimeStamp;

// Из-за того, что были bidirectional-связи программа уходила в бесконечный цикл и stackoverflow)
//    @OneToMany(mappedBy = "messagetheme", cascade = CascadeType.ALL)
//    private Set<Messages> messages;

}
