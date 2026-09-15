package com.travelconverter.controller;

import com.travelconverter.model.ConversionResult;
import com.travelconverter.model.Country;
import com.travelconverter.model.TravelRecommendation;
import com.travelconverter.repository.CountryRepository;
import com.travelconverter.service.ExchangeRateService;
import com.travelconverter.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // fine for a college project; restrict in production
public class ConverterController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private TravelService travelService;

    @Autowired
    private CountryRepository countryRepository;

    // Simple 3-currency style conversion, e.g. GET /api/convert?from=INR&to=USD&amount=100000
    @GetMapping("/convert")
    public ConversionResult convert(@RequestParam String from,
                                     @RequestParam String to,
                                     @RequestParam double amount) {
        double rate = exchangeRateService.getRate(from, to);
        double converted = amount * rate;
        return new ConversionResult(from.toUpperCase(), to.toUpperCase(), amount, converted, rate);
    }

    // Core feature: GET /api/travel-recommendations?from=INR&amount=100000
    @GetMapping("/travel-recommendations")
    public List<TravelRecommendation> travelRecommendations(@RequestParam String from,
                                                              @RequestParam double amount) {
        return travelService.getRecommendations(from.toUpperCase(), amount);
    }

    // List all countries in the dataset
    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
