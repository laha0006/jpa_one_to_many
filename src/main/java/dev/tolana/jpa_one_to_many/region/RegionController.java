package dev.tolana.jpa_one_to_many.region;


import dev.tolana.jpa_one_to_many.service.RegionKommuneApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class RegionController {

    private final RegionRepository regionRepository;
    private final RegionKommuneApiService regionKommuneApiService;


    @DeleteMapping("/region/{kode}")
    public ResponseEntity<String> delete(@PathVariable String kode) {
        Optional<Region> region = regionRepository.findById(kode);
        if (region.isPresent()) {
            regionRepository.deleteById(kode);
            return ResponseEntity.ok("Region deleted");
        }
        return ResponseEntity.notFound().build();
    }



    @GetMapping("/regioner")
    public List<Region> getAllRegions() {
        return regionKommuneApiService.getRegioner();
    }

}
