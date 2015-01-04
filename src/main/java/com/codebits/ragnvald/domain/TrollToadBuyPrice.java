package com.codebits.ragnvald.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class TrollToadBuyPrice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Column(nullable = true)
    @Getter
    @Setter
    private String setName;

    @Column(nullable = false)
    @Getter
    @Setter
    private String cardEdition;

    @Column
    @Getter
    @Setter
    private String cardCondition;
    
    @Column(nullable = false)
    @Getter
    @Setter
    private String buyPrice;

    @Column
    @Getter
    @Setter
    private String buyQuantity;

    @Column
    @Getter
    @Setter
    private String sellQuantity;

    public TrollToadBuyPrice() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("id", getId()).
                append("setName", getSetName()).
                append("edition", getCardEdition()).
                append("buyPrice", getBuyPrice()).
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
        TrollToadBuyPrice rhs = (TrollToadBuyPrice) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(getSetName(), rhs.getSetName())
                .append(getCardEdition(), rhs.getCardEdition())
                .isEquals();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(getSetName()) + Objects.hashCode(getCardEdition());
        return hash;
    }

}
