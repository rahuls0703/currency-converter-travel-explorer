package com.travelconverter.model;

public class TravelRecommendation {
    private String countryName;
    private String currencyCode;
    private String region;
    private double convertedAmount;      // amount in destination currency
    private double estimatedDaysBudget;  // days affordable at "budget" spend
    private double estimatedDaysMid;     // days affordable at "mid-range" spend
    private String attractions;
    private String cultureHighlights;

    public TravelRecommendation(String countryName, String currencyCode, String region,
                                 double convertedAmount, double estimatedDaysBudget,
                                 double estimatedDaysMid, String attractions, String cultureHighlights) {
        this.countryName = countryName;
        this.currencyCode = currencyCode;
        this.region = region;
        this.convertedAmount = convertedAmount;
        this.estimatedDaysBudget = estimatedDaysBudget;
        this.estimatedDaysMid = estimatedDaysMid;
        this.attractions = attractions;
        this.cultureHighlights = cultureHighlights;
    }

    public String getCountryName() { return countryName; }
    public String getCurrencyCode() { return currencyCode; }
    public String getRegion() { return region; }
    public double getConvertedAmount() { return convertedAmount; }
    public double getEstimatedDaysBudget() { return estimatedDaysBudget; }
    public double getEstimatedDaysMid() { return estimatedDaysMid; }
    public String getAttractions() { return attractions; }
    public String getCultureHighlights() { return cultureHighlights; }
}
