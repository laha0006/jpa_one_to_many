package dev.tolana.jpa_one_to_many.kommune;

import dev.tolana.jpa_one_to_many.region.Region;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Kommune {
    @Id
    @Column(length = 4)
    private String kode;
    private String navn;
    private String href;

    @ManyToOne
    @JoinColumn(name = "region", referencedColumnName = "kode")
    private Region region;
}
