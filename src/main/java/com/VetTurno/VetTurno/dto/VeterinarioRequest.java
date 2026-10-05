package com.VetTurno.VetTurno.dto;

import jakarta.validation.constraints.NotBlank;

public class VeterinarioRequest {
    @NotBlank
    private String nombre;

    @NotBlank
    private String especialidad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
