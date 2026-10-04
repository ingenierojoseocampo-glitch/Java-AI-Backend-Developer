package com.VetTurno.VetTurno.repository;

import com.VetTurno.VetTurno.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    boolean existsByVeterinario_IdAndFechaHora(
            Long veterinarioId,
            LocalDateTime fechaHora
    );
    List<Cita> findByVeterinario_Id(Long veterinarioId);
}
