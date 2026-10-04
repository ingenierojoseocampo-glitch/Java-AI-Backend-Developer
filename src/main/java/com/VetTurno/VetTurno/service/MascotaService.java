package com.VetTurno.VetTurno.service;


import com.VetTurno.VetTurno.dto.MascotaDTO;
import com.VetTurno.VetTurno.dto.MascotaRequest;
import com.VetTurno.VetTurno.dto.PropietarioDTO;
import com.VetTurno.VetTurno.dto.PropietarioRequest;
import com.VetTurno.VetTurno.model.Mascota;
import com.VetTurno.VetTurno.model.Propietario;
import com.VetTurno.VetTurno.repository.MascotaRepository;
import com.VetTurno.VetTurno.repository.PropietarioRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class MascotaService {
    private final MascotaRepository mascotaRepository;
    private final PropietarioRepository propietarioRepository;
    public MascotaService(MascotaRepository mascotaRepository, PropietarioRepository propietarioRepository) {
        this.mascotaRepository = mascotaRepository;
        this.propietarioRepository = propietarioRepository;
    }
    public List<MascotaDTO> listarMascotas() {
        List<Mascota> mascotas = mascotaRepository.findAll();
        List<MascotaDTO> mascotasDTO = new ArrayList<>();
        for (Mascota mascota : mascotas) {
            mascotasDTO.add(new MascotaDTO(mascota));
        }
        return mascotasDTO;
    }

    public MascotaDTO agregarMascotas(MascotaRequest request) {
        Mascota mascota = new Mascota();
        mascota.setNombre(request.getNombre());
        mascota.setEspecie(request.getEspecie());
        mascota.setRaza(request.getRaza());
        Propietario propietario = propietarioRepository
                .findById(request.getPropietarioId())
                .orElse(null);
        mascota.setPropietario(propietario);
        Mascota mascotaGuardado = mascotaRepository.save(mascota);
        return new MascotaDTO(mascotaGuardado);
    }
}

