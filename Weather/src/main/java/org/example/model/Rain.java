package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Rain
{
    @Column(name = "1h")
    @JsonProperty("1h")
    public double _1h;
}