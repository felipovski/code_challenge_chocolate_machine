package com.chococompany.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "chocolate")
public class Chocolate extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String description;

    @Column(unique=true)
    @Enumerated(EnumType.STRING)
    public ChocolateType chocolateType;

    @CreationTimestamp
    public Date creationDate;

    @UpdateTimestamp
    public Date updateDate;

    public static Chocolate findByType(String type){
        var chocolateType = ChocolateType.findByStrType(type);
        return find("chocolateType", chocolateType).firstResult();
    }
}
