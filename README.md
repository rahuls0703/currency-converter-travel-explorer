# 💱 CurrencyX: Intelligent Currency Conversion with Exchange Trends and Financial Insights

CurrencyX is a Java Spring Boot-based web application designed to provide intelligent currency conversion along with exchange-rate trends and financial insights.

The platform allows users to convert currencies using live exchange-rate data, explore historical exchange-rate trends, track currency performance, and gain useful insights from financial data through an interactive and user-friendly web interface.

🌐 **Live Website:** [CurrencyX](https://currency-converter-travel-explorer.onrender.com)

---

## 📌 Project Overview

CurrencyX goes beyond traditional currency conversion by combining real-time exchange-rate information with historical analysis and financial insights.

The application provides users with:

- Real-time currency conversion
- Exchange-rate trends
- Highest and lowest exchange-rate values
- Currency performance insights
- Interactive data visualization
- Responsive web interface

The application is developed using **Java and Spring Boot** and deployed as a live web application using **Render**.

---

## ✨ Key Features

### 💱 Real-Time Currency Conversion

Convert an amount from one currency to another using current exchange-rate information obtained through an external exchange-rate API.

### 📈 Exchange Rate Trends

View historical exchange-rate information to understand how a currency has performed over time.

### 📊 Financial Insights

Provides useful information such as:

- Highest exchange rate
- Lowest exchange rate
- Currency performance
- Historical trends
- Conversion-related insights

### 🌍 Multiple Currencies

Supports currency conversion between different international currencies provided by the exchange-rate service.

### 📱 Responsive Web Interface

The application provides a clean and responsive interface that can be accessed through desktop and mobile browsers.

### ☁️ Live Deployment

The application is deployed on **Render**, allowing users to access CurrencyX through a web browser without setting up the project locally.

---

## 🛠️ Technologies Used

### Backend

- **Java**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Maven**

### Database

- **H2 Database**

### Frontend

- **HTML5**
- **CSS3**
- **JavaScript**
- **Bootstrap / Responsive UI**

### External API

- **Frankfurter Exchange Rate API**

### Development & Deployment

- **Git**
- **GitHub**
- **Render**
- **Postman**

---

## 🏗️ System Architecture

```text
                    ┌─────────────────────┐
                    │       User          │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │   Web Interface     │
                    │ HTML / CSS / JS     │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │  Spring Boot API    │
                    │    Controllers      │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │   Service Layer     │
                    │   Business Logic    │
                    └───────┬─────┬───────┘
                            │     │
                  ┌─────────┘     └──────────┐
                  ▼                          ▼
        ┌─────────────────┐        ┌──────────────────┐
        │   H2 Database   │        │ Frankfurter API  │
        │ Currency Data & │        │ Exchange Rates   │
        │ History         │        └──────────────────┘
        └─────────────────┘

## Project Structure

```
src/main/java/com/travelconverter/
  ├── TravelConverterApplication.java   # entry point
  ├── model/                            # Country entity + response DTOs
  ├── repository/                       # Spring Data JPA repository
  ├── service/                          # ExchangeRateService, TravelService
  ├── controller/                       # REST endpoints
  └── config/DataSeeder.java            # seeds 13 countries on startup
src/main/resources/
  ├── application.properties
  └── static/                           # index.html, style.css, script.js
```

## Notes / Next Steps
- Cost-of-living figures in `DataSeeder.java` are approximate,
  curated for demo purposes — swap in Numbeo or your own researched
  data for accuracy.
- H2 is in-memory, so data resets every restart. Switch to MySQL/Postgres
  in `application.properties` for persistence.
- Consider adding: country flags/images, a "currency strength badge",
  filters by region, and pagination if the dataset grows.
