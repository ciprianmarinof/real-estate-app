package com.example.demo.dto.request.listing;


import com.example.demo.enums.City;
import com.example.demo.enums.PropertyType;
import com.example.demo.enums.Site;
import lombok.Data;


@Data
public class UpdateListingRequest {

    private Integer noOfRooms;

    private Integer noOfBathrooms;

    private Double area;

    private Integer constructionYear;

    private PropertyType propertyType;

    private City city;

    private Site site;

}
