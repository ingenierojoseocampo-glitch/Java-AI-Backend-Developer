package com.VetTurno.VetTurno.controller;

import com.VetTurno.VetTurno.dto.VeterinarioDTO;
import com.VetTurno.VetTurno.dto.VeterinarioRequest;
import com.VetTurno.VetTurno.service.VeterinarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veterinarios")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;
    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }
    @GetMapping
    public List<VeterinarioDTO> obtenerVeterinarios() {
        return veterinarioService.listarVeterinarios();
    }
    @PostMapping
    public ResponseEntity<VeterinarioDTO> crearVeterinario(@RequestBody VeterinarioRequest request) {

        VeterinarioDTO veterinario = veterinarioService.agregarVeterinario(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(veterinario);
    }
}