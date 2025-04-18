package com.example.demo.mapper;

import com.example.demo.dto.request.listing.AddListingRequest;
import com.example.demo.dto.request.listing.UpdateListingRequest;
import com.example.demo.dto.response.listing.ListingResponse;
import com.example.demo.entity.Listing;
import com.example.demo.repository.ListingRepository;
import org.springframework.stereotype.Component;

@Component
public class ListingMapper {


    private final ListingRepository listingRepository;

    public ListingMapper(ListingRepository listingRepository){
        this.listingRepository = listingRepository;
    }
    public ListingResponse fromListingEntity (Listing listing){

        ListingResponse listingResponse = new ListingResponse();

        listingResponse.setNumberOfRooms(listing.getNoOfRooms());
        listingResponse.setArea(listing.getArea());
        listingResponse.setCity(String.valueOf(listing.getCity()));
        listingResponse.setPropertyType(String.valueOf(listing.getPropertyType()));
        listingResponse.setSite(String.valueOf(listing.getSite()));

        return listingResponse;
    }

    public Listing fromAddListingRequest (AddListingRequest addListingRequest){

        Listing listing = new Listing();

        listing.setNoOfRooms(addListingRequest.getNoOfRooms());
        listing.setNoOfBathrooms(addListingRequest.getNoOfBathrooms());
        listing.setArea(addListingRequest.getArea());
        listing.setConstructionYear(addListingRequest.getConstructionYear());
        listing.setPropertyType(addListingRequest.getPropertyType());
        listing.setCity(addListingRequest.getCity());
        listing.setSite(addListingRequest.getSite());

        return listing;
    }

    public void updateListingFromRequest(UpdateListingRequest request, Listing listing){

        if(request.getNoOfRooms() != null){
            listing.setNoOfRooms(request.getNoOfRooms());
        }
        if(request.getNoOfBathrooms() != null){
            listing.setNoOfBathrooms(request.getNoOfBathrooms());
        }
        if(request.getArea() != null){
            listing.setArea(request.getArea());
        }
        if(request.getConstructionYear() != null){
            listing.setConstructionYear(request.getConstructionYear());
        }
        if(request.getPropertyType() != null){
            listing.setPropertyType(request.getPropertyType());
        }
        if(request.getCity() != null){
            listing.setPropertyType(request.getPropertyType());
        }
        if(request.getSite() != null){
            listing.setSite(request.getSite());
        }

    }

}
