package com.VetTurno.VetTurno.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.VetTurno.VetTurno.dto.PropietarioDTO;
import com.VetTurno.VetTurno.dto.PropietarioRequest;
import com.VetTurno.VetTurno.service.PropietarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/propietarios")
public class PropietarioController {

        private final PropietarioService propietarioService;
        public PropietarioController(PropietarioService propietarioService) {
            this.propietarioService = propietarioService;
        }
        @GetMapping
        public List<PropietarioDTO> obtenerPropietarios() {
            return propietarioService.listarPropietarios();
        }
        @PostMapping
        public ResponseEntity<PropietarioDTO> crearPropietario(@Valid @RequestBody PropietarioRequest request) {

            PropietarioDTO propietario = propietarioService.agregarPropietarios(request);

            return ResponseEntity.status(HttpStatus.CREATED).body(propietario);
    }
}

