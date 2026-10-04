package com.VetTurno.VetTurno.service;

import com.VetTurno.VetTurno.dto.CitaDTO;
import com.VetTurno.VetTurno.dto.CitaRequest;
import com.VetTurno.VetTurno.model.Cita;
import com.VetTurno.VetTurno.model.Mascota;
import com.VetTurno.VetTurno.model.Veterinario;
import com.VetTurno.VetTurno.repository.CitaRepository;
import com.VetTurno.VetTurno.repository.MascotaRepository;
import com.VetTurno.VetTurno.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CitaService {
    private final CitaRepository citaRepository;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;
    public CitaService(CitaRepository citaRepository, MascotaRepository mascotaRepository, VeterinarioRepository veterinarioRepository) {
        this.citaRepository = citaRepository;
        this.mascotaRepository = mascotaRepository;
        this.veterinarioRepository = veterinarioRepository;
    }
    public List<CitaDTO> listarCitas() {
        List<Cita> citas = citaRepository.findAll();
        List<CitaDTO> citasDTO = new ArrayList<>();
        for (Cita cita : citas) {
            citasDTO.add(new CitaDTO(cita));
        }
        return citasDTO;
    }
    public CitaDTO agregarCita(CitaRequest request) {
        Cita cita = new Cita();
        cita.setFechaHora(request.getFechaHora());
        cita.setMotivo(request.getMotivo());
        Mascota mascota = mascotaRepository
                .findById(request.getMascotaId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        cita.setMascota(mascota);
        Veterinario veterinario = veterinarioRepository
                .findById(request.getVeterinarioId())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));
        cita.setVeterinario(veterinario);
        if (request.getFechaHora().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("No se pueden crear citas en el pasado");
        }
        if (citaRepository.existsByVeterinario_IdAndFechaHora(veterinario.getId(),  request.getFechaHora())) {
            throw new RuntimeException("Fecha no Disponible");
        }
        Cita citaGuardado = citaRepository.save(cita);
        return new CitaDTO(citaGuardado);
    }
}
