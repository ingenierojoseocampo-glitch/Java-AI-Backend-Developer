package com.VetTurno.VetTurno.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.VetTurno.VetTurno.dto.MascotaDTO;
import com.VetTurno.VetTurno.dto.MascotaRequest;
import com.VetTurno.VetTurno.service.MascotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/mascotas")
public class MascotaController {

    private final MascotaService mascotaService;
    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }
    @GetMapping
    public List<MascotaDTO> obtenerMascotas() {
        return mascotaService.listarMascotas();
    }
    @PostMapping
    public ResponseEntity<MascotaDTO> crearMascota(@RequestBody MascotaRequest request) {

        MascotaDTO mascota = mascotaService.agregarMascotas(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(mascota);
    }
}
