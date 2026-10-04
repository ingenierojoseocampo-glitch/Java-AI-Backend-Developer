package com.VetTurno.VetTurno.service;


import com.VetTurno.VetTurno.dto.PropietarioDTO;
import com.VetTurno.VetTurno.dto.PropietarioRequest;
import com.VetTurno.VetTurno.model.Propietario;
import com.VetTurno.VetTurno.repository.PropietarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropietarioService {
    private final PropietarioRepository propietarioRepository;
    public PropietarioService(PropietarioRepository propietarioRepository) {
        this.propietarioRepository = propietarioRepository;
    }
    public List<PropietarioDTO> listarPropietarios() {
        List<Propietario> propietarios = propietarioRepository.findAll();
        List<PropietarioDTO> propietariosDTO = new ArrayList<>();
        for (Propietario propietario : propietarios) {
            propietariosDTO.add(new PropietarioDTO(propietario));
        }
        return propietariosDTO;
    }

    public PropietarioDTO agregarPropietarios(PropietarioRequest request) {
        Propietario propietario = new Propietario();
        propietario.setNombre(request.getNombre());
        propietario.setEmail(request.getEmail());
        propietario.setTelefono(request.getTelefono());
        Propietario propietarioGuardado = propietarioRepository.save(propietario);
        return new PropietarioDTO(propietarioGuardado);
    }
}
