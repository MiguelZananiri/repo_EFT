package com.duoc.backend.care;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cares")
public class CareController {

    private final CareRepository careRepository;

    public CareController(CareRepository careRepository) {
        this.careRepository = careRepository;
    }

    @GetMapping
    public List<Care> getAllCares() {
        return (List<Care>) careRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Care> getCareById(@PathVariable Long id) {

        return careRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Care saveCare(@RequestBody Care service) {
        return careRepository.save(service);
    }


    @DeleteMapping("/{id}")
    public void deleteCare(@PathVariable Long id) {
        careRepository.deleteById(id);
    }
}