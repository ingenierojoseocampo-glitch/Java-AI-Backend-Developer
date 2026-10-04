package com.VetTurno.VetTurno.service;

import com.VetTurno.VetTurno.model.Cita;
import com.VetTurno.VetTurno.repository.CitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService {
    private final CitaRepository citaRepository;
    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }
    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }
    public Cita agregarCita(Cita cita) {
        return citaRepository.save(cita);
    }
}
