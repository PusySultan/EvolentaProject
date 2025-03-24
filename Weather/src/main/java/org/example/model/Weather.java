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
public class Weather
{
    @Id
    @GeneratedValue
    private int id;

    @NonNull
    private double temp; // Температура
    private double feels_like; // Чувствуется как
    private double temp_min; // Минимальная температура
    private double temp_max; // Максимальная температура
    private double pressure; // Давление
    private  double humidity; // влажность
    private double sea_level; // уровень моря
    private double ground_level; // Уровень земли?
}
