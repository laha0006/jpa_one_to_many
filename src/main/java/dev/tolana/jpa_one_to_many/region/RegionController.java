package dev.tolana.jpa_one_to_many.region;


import dev.tolana.jpa_one_to_many.kommune.Kommune;
import dev.tolana.jpa_one_to_many.kommune.KommuneRepository;
import dev.tolana.jpa_one_to_many.service.RegionKommuneApiService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class RegionController {

    private final RegionRepository regionRepository;
    private final KommuneRepository kommuneRepository;
    private final RegionKommuneApiService regionKommuneApiService;

    @Transactional
    @GetMapping("/region/{kode}/kommune")
    public ResponseEntity<Set<Kommune>> getKommune(@PathVariable String kode) {
        System.out.println("#### GET REGION ####");
        Optional<Region> region = regionRepository.findById(kode);
        System.out.println("#### AFTER GET REGION ####");
        if (region.isPresent()) {
            return ResponseEntity.ok(region.get().getKommuner());
//            return ResponseEntity.ok(kommuneRepository.findKommuneByRegionKode(kode));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/region")
    public ResponseEntity<List<Region>> getRegions() {
        List<Region> regions = regionRepository.findAll();
        if (regions.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(regions);
        }
    }

    @GetMapping("/region/{kode}")
    public ResponseEntity<Region> getRegion(@PathVariable String kode) {
        Optional<Region> region = regionRepository.findById(kode);
        return region.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/region")
    public ResponseEntity<Region> createRegion(@RequestBody Region region) {
        Optional<Region> regionOptional = regionRepository.findById(region.getKode());
        if (regionOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        regionRepository.save(region);
        return ResponseEntity.ok(region);
    }

    @PutMapping("/region/{kode}")
    public ResponseEntity<Region> updateRegion(@PathVariable String kode, @RequestBody Region region) {
        Optional<Region> regionOptional = regionRepository.findById(kode);
        if (regionOptional.isPresent()) {
            region.setKode(kode);
            regionRepository.save(region);
            return ResponseEntity.ok(region);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/region/{kode}")
    public ResponseEntity<String> delete(@PathVariable String kode) {
        Optional<Region> region = regionRepository.findById(kode);
        if (region.isPresent()) {
            regionRepository.deleteById(kode);
            return ResponseEntity.ok("Region deleted");
        }
        return ResponseEntity.notFound().build();
    }



    @GetMapping("/init/regioner")
    public List<Region> getAllRegions() {
        return regionKommuneApiService.getRegioner();
    }

}
