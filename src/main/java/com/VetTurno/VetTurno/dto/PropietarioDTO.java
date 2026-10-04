package com.VetTurno.VetTurno.dto;

import com.VetTurno.VetTurno.model.Mascota;
import com.VetTurno.VetTurno.model.Propietario;

import java.util.ArrayList;
import java.util.List;

public class PropietarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private List<String> mascota;

    public PropietarioDTO(Propietario propietario) {
        this.id = propietario.getId();
        this.nombre = propietario.getNombre();
        this.email = propietario.getEmail();
        this.telefono = propietario.getTelefono();
        List<String> nombresMascotas = new ArrayList<>();

        if (propietario.getMascota() != null) {
            for (Mascota mascota : propietario.getMascota()) {
                nombresMascotas.add(mascota.getNombre());
            }
        }
        this.mascota = nombresMascotas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<String> getMascota() {
        return mascota;
    }

    public void setMascota(List<String> mascota) {
        this.mascota = mascota;
    }
}
