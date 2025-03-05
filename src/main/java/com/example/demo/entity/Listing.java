package com.example.demo.entity;

import com.example.demo.enums.City;
import com.example.demo.enums.PropertyType;
import com.example.demo.enums.Site;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer listingId;
    private Integer noOfRooms;

    private Integer noOfBathrooms;

    private double area;
    private int constructionYear;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;
    @Enumerated(EnumType.STRING)
    private City city;
    @Enumerated(EnumType.STRING)
    private Site site;

    @Override
    public String toString() {
        return "Listing{" +
                "listingId=" + listingId +
                ", noOfRooms=" + noOfRooms +
                ", noOfBathrooms=" + noOfBathrooms +
                ", area=" + area +
                ", constructionYear=" + constructionYear +
                ", propertyType=" + propertyType +
                ", city=" + city +
                ", site=" + site +
                '}';
    }


// private String title;






}
