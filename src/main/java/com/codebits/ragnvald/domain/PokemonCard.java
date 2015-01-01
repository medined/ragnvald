package com.codebits.ragnvald.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class PokemonCard implements Serializable {

    /**
     * Instantiate an object.
     *
     * @param pokemonSetRootName The root name of the Pokemon set.
     * @param pokemonCardId The specific card (i.e. 011R for reverse holo)
     */
    public PokemonCard(String pokemonSetRootName, String pokemonCardId) {
        this.pokemonSetRootName = pokemonSetRootName;
        this.pokemonCardId = pokemonCardId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    /** The root name of the Pokemon set.     *
     */
    @Column(nullable = false)
    @Getter
    private String pokemonSetRootName;

    /** The specific card (i.e. 011R for reverse holo)
     */
    @Column(nullable = false)
    @Getter
    private String pokemonCardId;

    public PokemonCard() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("id", getId()).
                append("pokemonSetRootName", getPokemonSetRootName()).
                append("pokemonCardId", getPokemonCardId()).
                toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        PokemonCard rhs = (PokemonCard) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(getPokemonSetRootName(), rhs.getPokemonSetRootName())
                .append(getPokemonCardId(), rhs.getPokemonCardId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(getPokemonSetRootName())+ Objects.hashCode(getPokemonCardId());
        return hash;
    }

}
