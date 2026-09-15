package com.travelconverter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ExchangeRateService {

    // open.er-api.com is free, requires no API key, and covers ~160 currencies
    // (Frankfurter is more accurate but only supports ~30 major currencies,
    // which excludes VND, NPR, GEL, LKR etc. that this app needs)
    private static final String BASE_URL = "https://open.er-api.com/v6/latest/";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Fetches the FULL rate table for a given base currency in one API call.
     * Example: getAllRates("INR") -> { "USD": 0.012, "EUR": 0.0104, ... }
     */
    public Map<String, Double> getAllRates(String from) {
        String url = BASE_URL + from.toUpperCase();
        ExchangeRateApiResponse response = restTemplate.getForObject(url, ExchangeRateApiResponse.class);

        if (response == null || response.rates == null) {
            throw new RuntimeException("Could not fetch exchange rates for base currency " + from);
        }
        return response.rates;
    }

    public double getRate(String from, String to) {
        if (from.equalsIgnoreCase(to)) {
            return 1.0;
        }
        Map<String, Double> rates = getAllRates(from);
        Double rate = rates.get(to.toUpperCase());
        if (rate == null) {
            throw new RuntimeException("No exchange rate found for " + from + " -> " + to);
        }
        return rate;
    }

    public double convert(String from, String to, double amount) {
        double rate = getRate(from, to);
        return amount * rate;
    }

    // Maps the open.er-api.com JSON response, e.g.:
    // { "result": "success", "base_code": "INR", "rates": { "USD": 0.012, ... } }
    public static class ExchangeRateApiResponse {
        public String result;
        public String base_code;
        public Map<String, Double> rates;
    }
}
