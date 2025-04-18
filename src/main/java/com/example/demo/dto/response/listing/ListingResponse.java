package com.example.demo.dto.response.listing;

import lombok.Data;



@Data
public class ListingResponse {

    private Integer listingId;

    private Integer numberOfRooms;

    private double area;

    private String city;

    private String propertyType;

    private String site;

}
