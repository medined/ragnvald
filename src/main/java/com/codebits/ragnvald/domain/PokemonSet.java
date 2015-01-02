package com.codebits.ragnvald.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class PokemonSet implements Serializable {

    /**
     * Instantiate an object.
     *
     * @param number The English number of the set.
     * @param name The English name of the set.
     * @param count The number of cards in a normal set.
     */
    public PokemonSet(Integer number, String name, Integer count) {
        this.number = number;
        this.name = name;
        this.count = count;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    /**
     * English set number
     *
     */
    @Column(nullable = true)
    @Getter
    @Setter
    private Integer number;

    /**
     * English set name
     *
     */
    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String name;

    /**
     * Number of cards in a normal set including EX but not secret.
     *
     */
    @Column(nullable = false)
    @Getter
    @Setter
    private Integer count;
    
    /** True if we have the master list of card ids for this set.
     * 
     * @return true if we have the master list.
     */
    @Getter
    @Setter
    private Boolean master;

    public String getRootName() {
        return getName().replaceAll(" ", "_").toLowerCase();
    }
    
    public PokemonSet() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("id", getId()).
                append("number", getNumber()).
                append("name", getName()).
                append("rootName", getRootName()).
                append("count", getCount()).
                append("master", getMaster()).
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
        PokemonSet rhs = (PokemonSet) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(getNumber(), rhs.getNumber())
                .isEquals();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(getNumber());
        return hash;
    }

}
