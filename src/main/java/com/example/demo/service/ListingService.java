package com.example.demo.service;

import com.example.demo.dto.request.listing.AddListingRequest;
import com.example.demo.dto.request.listing.UpdateListingRequest;
import com.example.demo.dto.response.ListingResponse;
import com.example.demo.entity.Listing;

import java.util.List;

public interface ListingService {

    ListingResponse createListing(AddListingRequest addListingRequest);
    List<ListingResponse> getAllListings();
    ListingResponse getOrderById(Integer id);

    ListingResponse updateListing(Integer id, UpdateListingRequest updateListingRequest);

    void deleteListing(Integer id);





}
