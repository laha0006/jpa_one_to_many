package dev.tolana.jpa_one_to_many.kommune;

import dev.tolana.jpa_one_to_many.service.RegionKommuneApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class KommuneController {


    private final KommuneRepository kommuneRepository;
    private final RegionKommuneApiService regionKommuneApiService;

    @GetMapping("/init/kommuner")
    public List<Kommune> getAllRegions() {
        return regionKommuneApiService.getKommuner();
    }

    @GetMapping("/kommune")
    public ResponseEntity<List<Kommune>> getKommuner() {
        List<Kommune> kommuner = kommuneRepository.findAll();
        if (kommuner.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(kommuner);
    }

    @GetMapping("/kommune/{kode}")
    public ResponseEntity<Kommune> getKommune(@PathVariable String kode) {
        Optional<Kommune> kommune = kommuneRepository.findById(kode);
        return kommune.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/kommune")
    public ResponseEntity<Kommune> createKommune(@RequestBody Kommune kommune) {
        Optional<Kommune> alreadyExists = kommuneRepository.findById(kommune.getKode());
        if (alreadyExists.isPresent()) {
            return ResponseEntity.badRequest().body(alreadyExists.get());
        } else {
            kommuneRepository.save(kommune);
            return ResponseEntity.ok(kommune);
        }
    }

    @PutMapping("/kommune/{kode}")
    public ResponseEntity<Kommune> updateKommune(@PathVariable String kode, @RequestBody Kommune kommune) {
        Optional<Kommune> alreadyExists = kommuneRepository.findById(kode);
        if (alreadyExists.isPresent()) {
            kommune.setKode(kode);
            kommuneRepository.save(kommune);
            return ResponseEntity.ok(kommune);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/kommune/{kode}")
    public ResponseEntity<Void> deleteKommune(@PathVariable String kode) {
        Optional<Kommune> kommune = kommuneRepository.findById(kode);
        if (kommune.isPresent()) {
            kommuneRepository.delete(kommune.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
