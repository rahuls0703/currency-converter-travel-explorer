package com.travelconverter.model;

public class ConversionResult {
    private String from;
    private String to;
    private double originalAmount;
    private double convertedAmount;
    private double rate;

    public ConversionResult(String from, String to, double originalAmount, double convertedAmount, double rate) {
        this.from = from;
        this.to = to;
        this.originalAmount = originalAmount;
        this.convertedAmount = convertedAmount;
        this.rate = rate;
    }

    public String getFrom() { return from; }
    public String getTo() { return to; }
    public double getOriginalAmount() { return originalAmount; }
    public double getConvertedAmount() { return convertedAmount; }
    public double getRate() { return rate; }
}
