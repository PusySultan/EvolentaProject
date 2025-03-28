package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@NoArgsConstructor // Конструктор без параметров
@AllArgsConstructor // Конструктор со всеми параметрами
@Data // Генерируем get set
public class Weather
{
    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private int pressure;
    private int humidity;
    private int sea_level;
    private int grnd_level;

    public Weather(JSONObject jsonObject)
    {
        this.temp = jsonObject.getDouble("temp");
        this.feels_like = jsonObject.getDouble("feels_like");
        this.temp_min = jsonObject.getDouble("temp_min");
        this.temp_max = jsonObject.getDouble("temp_max");
        this.pressure = jsonObject.getInt("pressure");
        this.humidity = jsonObject.getInt("humidity");
        this.sea_level = jsonObject.getInt("sea_level");
        this.grnd_level = jsonObject.getInt("grnd_level");
    }
}