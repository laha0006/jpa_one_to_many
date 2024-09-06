package dev.tolana.jpa_one_to_many.kommune;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface KommuneRepository extends JpaRepository<Kommune, String> {
    List<Kommune> findAll();
}
