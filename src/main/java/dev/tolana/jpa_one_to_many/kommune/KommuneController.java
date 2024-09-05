package dev.tolana.jpa_one_to_many.kommune;

import dev.tolana.jpa_one_to_many.service.RegionKommuneApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class KommuneController {


    private final KommuneRepository kommuneRepository;
    private final RegionKommuneApiService regionKommuneApiService;

    @GetMapping("/kommuner")
    public List<Kommune> getAllRegions() {
        return regionKommuneApiService.getKommuner();
    }
}
