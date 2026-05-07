package com.duoc.backend.medication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medications")
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping
    public List<Medication> getAllMedications() {
        return medicationService.getAllMedications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medication> getMedicationById(
            @PathVariable Long id) {

        Medication medication =
                medicationService.getMedicationById(id);

        if (medication == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(medication);
    }

    @PostMapping
    public ResponseEntity<Medication> saveMedication(
            @RequestBody Medication medication) {

        Medication savedMedication =
                medicationService.saveMedication(medication);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedMedication);
    }
}