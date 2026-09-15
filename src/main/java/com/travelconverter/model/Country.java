package com.travelconverter.model;

import jakarta.persistence.*;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;              // e.g. "Vietnam"
    private String currencyCode;      // e.g. "VND"
    private String region;            // e.g. "Southeast Asia"

    private Double avgDailyBudgetLow;   // budget traveler, in local currency
    private Double avgDailyBudgetMid;   // mid-range traveler, in local currency

    @Column(length = 1000)
    private String attractions;       // comma-separated top attractions

    @Column(length = 1000)
    private String cultureHighlights; // short cultural description

    public Country() {}

    public Country(String name, String currencyCode, String region,
                    Double avgDailyBudgetLow, Double avgDailyBudgetMid,
                    String attractions, String cultureHighlights) {
        this.name = name;
        this.currencyCode = currencyCode;
        this.region = region;
        this.avgDailyBudgetLow = avgDailyBudgetLow;
        this.avgDailyBudgetMid = avgDailyBudgetMid;
        this.attractions = attractions;
        this.cultureHighlights = cultureHighlights;
    }

    // Getters and setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCurrencyCode() { return currencyCode; }
    public void setCurrencyCode(String currencyCode) { this.currencyCode = currencyCode; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public Double getAvgDailyBudgetLow() { return avgDailyBudgetLow; }
    public void setAvgDailyBudgetLow(Double avgDailyBudgetLow) { this.avgDailyBudgetLow = avgDailyBudgetLow; }

    public Double getAvgDailyBudgetMid() { return avgDailyBudgetMid; }
    public void setAvgDailyBudgetMid(Double avgDailyBudgetMid) { this.avgDailyBudgetMid = avgDailyBudgetMid; }

    public String getAttractions() { return attractions; }
    public void setAttractions(String attractions) { this.attractions = attractions; }

    public String getCultureHighlights() { return cultureHighlights; }
    public void setCultureHighlights(String cultureHighlights) { this.cultureHighlights = cultureHighlights; }
}
