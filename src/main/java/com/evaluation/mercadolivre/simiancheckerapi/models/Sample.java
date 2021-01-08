package com.evaluation.mercadolivre.simiancheckerapi.models;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, columnDefinition = "LONGTEXT", nullable = false)
    private String dna;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DnaOrigin origin;

    @Column(nullable = false)
    private OffsetDateTime date;

    public Sample() {

    }

    public Sample(String dna, DnaOrigin origin, OffsetDateTime date) {
        super();
        this.dna = dna;
        this.origin = origin;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDna() {
        return this.dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public DnaOrigin getOrigin() {
        return this.origin;
    }

    public void setOrigin(DnaOrigin origin) {
        this.origin = origin;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sample other = (Sample) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
