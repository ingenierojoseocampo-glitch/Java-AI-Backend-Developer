package com.VetTurno.VetTurno.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.VetTurno.VetTurno.dto.CitaDTO;
import com.VetTurno.VetTurno.dto.CitaRequest;
import com.VetTurno.VetTurno.service.CitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;
    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }
    @GetMapping
    public List<CitaDTO> obtenerCitas() {
        return citaService.listarCitas();
    }
    @PostMapping
    public ResponseEntity<CitaDTO> crearCita(@RequestBody CitaRequest request) {

        CitaDTO cita = citaService.agregarCita(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(cita);
    }
}
