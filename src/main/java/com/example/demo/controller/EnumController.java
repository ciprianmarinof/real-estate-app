package com.example.demo.controller;

import com.example.demo.enums.City;
import com.example.demo.enums.PropertyType;
import com.example.demo.enums.Site;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/enums")
public class EnumController {

    @GetMapping("/cities")
    public List<City> getCities() {
        return Arrays.asList(City.values());
    }
    @GetMapping("/property-types")
    public List<PropertyType> getPropertyTypes() {
        return Arrays.asList(PropertyType.values());
    }
    @GetMapping("/sites")
    public List<Site> getSites() {
        return Arrays.asList(Site.values());
    }

}
