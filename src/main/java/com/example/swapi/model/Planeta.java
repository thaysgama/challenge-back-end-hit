package com.example.swapi.model;

import com.example.swapi.dto.PlanetaDto;

public class Planeta {
    private Integer id;
    private String name;
    private String climate;
    private String terrain;
    private Integer quantFilms;

    public Planeta(PlanetaDto planetaDto) {
        this.id = planetaDto.getId();
        this.name = planetaDto.getName();
        this.climate = planetaDto.getClimate();
        this.terrain = planetaDto.getTerrain();
        this.quantFilms = planetaDto.getFilms().size();
    }

    public Planeta(){}

    public Integer getQuantFilms() {
        return quantFilms;
    }

    public void setQuantFilms(Integer quantFilms) {
        this.quantFilms = quantFilms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }
}

