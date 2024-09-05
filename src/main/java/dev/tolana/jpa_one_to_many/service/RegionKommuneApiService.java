package dev.tolana.jpa_one_to_many.service;

import dev.tolana.jpa_one_to_many.kommune.Kommune;
import dev.tolana.jpa_one_to_many.region.Region;

import java.util.List;

public interface RegionKommuneApiService {
    List<Region> getRegioner();

    List<Kommune> getKommuner();
}
