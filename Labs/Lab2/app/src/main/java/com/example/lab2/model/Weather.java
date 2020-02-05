package com.example.lab2.model;

public class Weather
{
    private int id;
    private String city;
    private String weatherDescription;
    private String weatherTime;
    private String icon;
    private String windDirection;
    private String sunRise;
    private String weather_icon = "https://www.weatherbit.io/static/img/icons/" + icon + ".png";

    public Weather()
    {
        city = "Kigali";
        id =  0;
    }
    public Weather(int id, String city, String weatherDescription, String weatherTime, String icon, String windDirection, String sunRise)
    {
        this.id = id;
        this.city = city;
        this.weatherDescription = weatherDescription;
        this.weatherTime = weatherTime;
        this.icon = icon;
        this.windDirection = windDirection;
        this.sunRise = sunRise;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getWeatherDescription()
    {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription)
    {
        this.weatherDescription = weatherDescription;
    }

    public String getWeatherTime() {
        return weatherTime;
    }

    public void setWeatherTime(String weatherTime) {
        this.weatherTime = weatherTime;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getWindDirection()
    {
        return windDirection;
    }

    public void setWindDirection(String windDirection)
    {
        this.windDirection = windDirection;
    }

    public String getSunRise()
    {
        return sunRise;
    }

    public void setSunRise(String sunRise)
    {
        this.sunRise = sunRise;
    }
}