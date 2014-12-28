package com.codebits.ragnvald.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

//1,Base Set,base_set_1,102

@Entity
public class PokemonSet implements Serializable {

    /** Instantiate an object.
     * 
     * @param number The English number of the set.
     * @param name The English name of the set.
     * @param rootName The root to build file names around.
     * @param count The number of cards in a normal set.
     */
    public PokemonSet(Integer number, String name, String rootName, Integer count) {
        this.number = number;
        this.name = name;
        this.rootName = rootName;
        this.count = count;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    private Long id;

    /** English set number
     * 
     */
    @Column(nullable = false)
    @Getter
    @Setter
    private Integer number;

    /** English set name
     * 
     */
    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    /** root to build file names around.
     * 
     */
    @Column(nullable = false)
    @Getter
    @Setter
    private String rootName;

    /** Number of cards in a normal set including EX but not secret.
     * 
     */
    @Column(nullable = false)
    @Getter
    @Setter
    private Integer count;

    public PokemonSet() { }

    @Override
    public String toString() {
     return new ToStringBuilder(this).
       append("id", id).
       append("number", number).
       append("name", name).
       append("rootName", rootName).
       append("count", count).
       toString();
   }
    
}
