package com.example.demo.service;

import com.example.demo.dto.request.listing.AddListingRequest;
import com.example.demo.dto.request.listing.UpdateListingRequest;
import com.example.demo.dto.response.listing.ListingResponse;
import com.example.demo.entity.Listing;
import com.example.demo.enums.PropertyType;
import com.example.demo.mapper.ListingMapper;
import com.example.demo.repository.ListingRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ListingServiceImpl implements ListingService{

    private final ListingRepository listingRepository;

    private final ListingMapper listingMapper;

    public ListingServiceImpl(ListingRepository listingRepository, ListingMapper listingMapper) {
        this.listingRepository = listingRepository;
        this.listingMapper = listingMapper;
    }

    @Override
    @Transactional
    public ListingResponse createListing(AddListingRequest addListingRequest) {
        Listing listing = listingMapper.fromAddListingRequest(addListingRequest);

        if(listing.getNoOfRooms()==1){
                listing.setPropertyType(PropertyType.STUDIO);
        }else if (listing.getNoOfRooms()==2){
            listing.setPropertyType(PropertyType.APARTMENT);
        }else{
            listing.setPropertyType(PropertyType.HOUSE);
        }

        listingRepository.save(listing);

        ListingResponse listingResponse = new ListingResponse();

        listingResponse.setListingId(listing.getListingId());
        listingResponse.setNumberOfRooms(listing.getNoOfRooms());
        listingResponse.setArea(listing.getArea());
        listingResponse.setCity(String.valueOf(listing.getCity()));
        listingResponse.setPropertyType(listing.getPropertyType().toString());
        listingResponse.setSite(listing.getSite().toString());


        return listingResponse;
    }

    @Override
    public List<ListingResponse> getAllListings() {
        List<Listing> listings = listingRepository.findAll();

        List<ListingResponse> listingResponseList = listings.stream()
                .map(listingMapper::fromListingEntity).collect(Collectors.toList());

        return listingResponseList;
    }

    @Override
    public ListingResponse getOrderById(Integer id) {

        Optional<Listing> optionalListing = listingRepository.findById(id);

        if(optionalListing.isPresent()){
            Listing listing = optionalListing.get();

            ListingResponse listingResponse = listingMapper.fromListingEntity(listing);

            return listingResponse;
        } else throw new RuntimeException("Listing not found");
    }

    @Override
    public ListingResponse updateListing(Integer id, UpdateListingRequest request) {

        Listing listing  = listingRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Listing not found"));
        if(listing.getNoOfRooms()==1){
            listing.setPropertyType(PropertyType.STUDIO);
        }else if (listing.getNoOfRooms()==2){
            listing.setPropertyType(PropertyType.APARTMENT);
        }else{
            listing.setPropertyType(PropertyType.HOUSE);
        }
        listingMapper.updateListingFromRequest(request, listing);
        listingRepository.save(listing);

        return listingMapper.fromListingEntity(listing);
    }

    @Override
    public void deleteListing(Integer id) {

        Optional<Listing> optionalListing = listingRepository.findById(id);

        if(optionalListing.isPresent()){

            Listing listing = optionalListing.get();

            listingRepository.delete(listing);
        } else throw new RuntimeException("Listing with id "+ id + " not found");

    }
}
