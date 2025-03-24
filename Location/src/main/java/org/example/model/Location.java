package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity // - Отображение в БД
@NoArgsConstructor // - Автоматическое создание конструктора по умолчанию
@AllArgsConstructor // - Конструктор со всеми параметрами
@Data // - Автоматическая генерация геттеров, сеттеров, equals и hashCode
public class Location
{
    @Id
    @GeneratedValue
    private int id;
    @NonNull
    private String LocationName;
    private double Latitude; // Широта
    private double longitude; // Долгота
}
