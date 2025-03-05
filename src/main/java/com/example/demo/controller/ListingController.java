package com.example.demo.controller;

import com.example.demo.dto.request.listing.AddListingRequest;
import com.example.demo.dto.request.listing.UpdateListingRequest;
import com.example.demo.dto.response.ListingResponse;
import com.example.demo.service.ListingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/listing")
public class ListingController {


    private final ListingService listingService;
    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }
    @PostMapping
    public ResponseEntity<ListingResponse> createListing(@RequestBody AddListingRequest addListingRequest){
        System.out.println("Received request: " + addListingRequest);
        ListingResponse responseBody = listingService.createListing(addListingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
    @GetMapping
    public ResponseEntity<List<ListingResponse>> getAllListings() {
        List<ListingResponse> responseBody = listingService.getAllListings();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ListingResponse> getListingById(@PathVariable Integer id) {
        ListingResponse response = listingService.getOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ListingResponse> updateListing(@PathVariable Integer id, @RequestBody UpdateListingRequest request){

        ListingResponse response = listingService.updateListing(id, request);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListing(@PathVariable Integer id) {
        listingService.deleteListing(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
