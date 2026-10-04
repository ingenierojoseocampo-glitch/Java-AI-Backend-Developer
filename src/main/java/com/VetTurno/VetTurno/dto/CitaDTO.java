package com.VetTurno.VetTurno.dto;

import com.VetTurno.VetTurno.model.Cita;

import java.time.LocalDateTime;

public class CitaDTO {
    private Long id;
    private LocalDateTime fechaHora;
    private String motivo;
    private Long mascotaId;
    private String mascotaNombre;
    private Long veterinarioId;
    private String veterinarioNombre;

    public CitaDTO(Cita cita) {
        this.id = cita.getId();
        this.fechaHora = cita.getFechaHora();
        this.motivo = cita.getMotivo();
        this.mascotaId = cita.getMascota() != null
                ? cita.getMascota().getId() : null;
        this.mascotaNombre = cita.getMascota() != null
                ? cita.getMascota().getNombre() : null;
        this.veterinarioId = cita.getVeterinario() != null
                ? cita.getVeterinario().getId() : null;
        this.veterinarioNombre = cita.getVeterinario() != null
                ? cita.getVeterinario().getNombre(): null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Long getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(Long mascotaId) {
        this.mascotaId = mascotaId;
    }

    public String getMascotaNombre() {
        return mascotaNombre;
    }

    public void setMascotaNombre(String mascotaNombre) {
        this.mascotaNombre = mascotaNombre;
    }

    public Long getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(Long veterinarioId) {
        this.veterinarioId = veterinarioId;
    }

    public String getVeterinarioNombre() {
        return veterinarioNombre;
    }

    public void setVeterinarioNombre(String veterinarioNombre) {
        this.veterinarioNombre = veterinarioNombre;
    }
}
