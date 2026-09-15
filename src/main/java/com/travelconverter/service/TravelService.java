package com.travelconverter.service;

import com.travelconverter.model.Country;
import com.travelconverter.model.TravelRecommendation;
import com.travelconverter.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TravelService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ExchangeRateService exchangeRateService;

    /**
     * Given an amount in the user's home currency, ranks all seeded countries
     * by how many days that amount would cover, from most affordable to least.
     * Fetches the full rate table ONCE, then reuses it for every country.
     * Any country whose currency isn't in the rate table is silently skipped
     * instead of failing the whole request.
     */
    public List<TravelRecommendation> getRecommendations(String fromCurrency, double amount) {
        List<Country> countries = countryRepository.findAll();
        Map<String, Double> rates = exchangeRateService.getAllRates(fromCurrency);

        List<TravelRecommendation> recommendations = new ArrayList<>();

        for (Country country : countries) {
            Double rate = fromCurrency.equalsIgnoreCase(country.getCurrencyCode())
                    ? 1.0
                    : rates.get(country.getCurrencyCode());

            if (rate == null) {
                // No rate available for this currency from the API — skip it
                // rather than failing the entire recommendations list.
                continue;
            }

            double convertedAmount = amount * rate;
            double daysBudget = convertedAmount / country.getAvgDailyBudgetLow();
            double daysMid = convertedAmount / country.getAvgDailyBudgetMid();

            recommendations.add(new TravelRecommendation(
                    country.getName(),
                    country.getCurrencyCode(),
                    country.getRegion(),
                    round(convertedAmount),
                    round(daysBudget),
                    round(daysMid),
                    country.getAttractions(),
                    country.getCultureHighlights()
            ));
        }

        recommendations.sort(Comparator.comparingDouble(TravelRecommendation::getEstimatedDaysMid).reversed());
        return recommendations;
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
