package com.VetTurno.VetTurno.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class PropietarioRequest {
    @NotBlank
    private String nombre;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String telefono;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
