package com.VetTurno.VetTurno.service;

import com.VetTurno.VetTurno.dto.VeterinarioDTO;
import com.VetTurno.VetTurno.dto.VeterinarioRequest;
import com.VetTurno.VetTurno.model.Veterinario;
import com.VetTurno.VetTurno.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VeterinarioService {
    private final VeterinarioRepository veterinarioRepository;
    public VeterinarioService(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }
    public List<VeterinarioDTO> listarVeterinarios() {
        List<Veterinario> veterinarios = veterinarioRepository.findAll();
        List<VeterinarioDTO> veterinariosDTO = new ArrayList<>();
        for (Veterinario veterinario : veterinarios) {
            veterinariosDTO.add(new VeterinarioDTO(veterinario));
        }
        return veterinariosDTO;
    }
    public VeterinarioDTO agregarVeterinario(VeterinarioRequest request) {
        Veterinario veterinario = new Veterinario();
        veterinario.setNombre(request.getNombre());
        veterinario.setEspecialidad(request.getEspecialidad());
        Veterinario veterinarioGuardado = veterinarioRepository.save(veterinario);
        return new VeterinarioDTO(veterinarioGuardado);
    }
}
