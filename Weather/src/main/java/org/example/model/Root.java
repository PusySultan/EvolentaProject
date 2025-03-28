package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity // Помечаем класс как сущность JPA
@Table(name = "root") //Задаем имя таблицы
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Root
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int RepositoryID;

    @Embedded private Coord coord;
    @ElementCollection
    @CollectionTable(
            name = "root_weather",
            joinColumns = @JoinColumn(name = "root_id")
    )
    private List<Weather> weather;
    private String base;
    @Embedded private Main main;
    private int visibility;
    @Embedded private Wind wind;
    @Embedded private Rain rain;
    @Embedded private Clouds clouds;
    private int dt;
    @Embedded private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;

    public Root(String base)
    {
        this.base = base;
    }
}
