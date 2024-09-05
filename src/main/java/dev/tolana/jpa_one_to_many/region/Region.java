package dev.tolana.jpa_one_to_many.region;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.tolana.jpa_one_to_many.kommune.Kommune;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Region {

    @Id
    @Column(length = 4)
    private String kode;
    private String navn;
    private String href;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Kommune> kommuner = new HashSet<>();

}
