package org.example.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Coord
{
    @Column(name = "latitude")
    private double lon;
    @Column(name = "longitude")
    private double lat;
}