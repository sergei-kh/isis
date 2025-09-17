package ru.bstu.it212.hlopov.Models;

public class Country {
    public Country() {}
    public Country(int id) {
        this.id = id;
    }
    private int id;
    private String continent;
    private String name;
    private int area;
    private int population;
    private String minerals;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setMinerals(String minerals) {
        this.minerals = minerals;
    }

    public String getContinent() { return this.continent; }

    public String getName() { return this.name; }

    public int getArea() {
        return this.area;
    }

    public String getStringArea() {
        return Integer.toString(this.area);
    }

    public int getPopulation() { return this.population; }

    public String getStringPopulation() {
        return Integer.toString(this.population);
    }

    public String getMinerals() { return this.minerals; }
}
