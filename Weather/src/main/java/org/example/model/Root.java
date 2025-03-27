package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */

@Entity
@Table(name = "root")
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
