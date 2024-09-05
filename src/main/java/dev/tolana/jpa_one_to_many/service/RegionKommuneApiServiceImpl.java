package dev.tolana.jpa_one_to_many.service;

import dev.tolana.jpa_one_to_many.kommune.Kommune;
import dev.tolana.jpa_one_to_many.kommune.KommuneRepository;
import dev.tolana.jpa_one_to_many.region.Region;
import dev.tolana.jpa_one_to_many.region.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class RegionKommuneApiServiceImpl implements RegionKommuneApiService {

    private final String REGION_URL = "https://api.dataforsyningen.dk/regioner";
    private final String KOMMUNE_URL = "https://api.dataforsyningen.dk/kommuner";

    private final RegionRepository regionRepository;
    private final KommuneRepository kommuneRepository;

    private final RestTemplate restTemplate;

    private void saveRegioner(List<Region> regioner) {
        regionRepository.saveAll(regioner);
    }

    private void saveKommuner(List<Kommune> body) {
        kommuneRepository.saveAll(body);
    }

    @Override
    public List<Region> getRegioner() {
        ResponseEntity<List<Region>> response =
                restTemplate.exchange(REGION_URL,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Region>>(){});
        saveRegioner(response.getBody());
        return new ArrayList<>(response.getBody());
    }

    @Override
    public List<Kommune> getKommuner() {
        ResponseEntity<List<Kommune>> response =
                restTemplate.exchange(KOMMUNE_URL,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Kommune>>(){});
        saveKommuner(response.getBody());
        return new ArrayList<>(response.getBody());
    }

}
