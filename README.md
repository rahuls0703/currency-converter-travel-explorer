# CurrencyX: Intelligent Currency Conversion with Exchange Trends and Financial Insights

A Spring Boot web app that converts currency AND tells you how many days
your money could fund a trip to various countries, based on curated
cost-of-living data.

🌐 **Live app:** 

## Tech Stack
- Java 17, Spring Boot 3.3 (Web, Data JPA)
- H2 in-memory database (auto-seeded with 13 countries on startup)
- [open.er-api.com](https://open.er-api.com) for live exchange rates (free, no API key needed, ~160 currencies supported)
- HTML5, Tailwind CSS, vanilla JavaScript frontend served from `src/main/resources/static`
- Dockerized and deployed on Render

## How to Run Locally

1. **Prerequisites**: Java 17+ and Maven installed (or use an IDE like
   IntelliJ / Eclipse / VS Code with the Java extension pack).

2. **Run from terminal**:
```bash
   cd travel-converter
   mvn spring-boot:run
```

3. **Run from an IDE**: Import as a Maven project, then run
   `TravelConverterApplication.java` directly.

4. Open your browser to **http://localhost:8080**

## Deployment

The app is containerized with the included `Dockerfile` and deployed on
[Render](https://render.com)'s free tier. Pushing to the connected GitHub
repo triggers an automatic rebuild and redeploy. Note: the free tier spins
down after 15 minutes of inactivity, so the first request after a period
of no traffic may take 30–60 seconds to respond while it wakes up.

## API Endpoints

- `GET /api/convert?from=INR&to=USD&amount=100000`
  Simple currency conversion.

- `GET /api/travel-recommendations?from=INR&amount=100000`
  Returns all seeded countries ranked by estimated affordable travel days,
  richest-value first (skips any country whose currency has no available
  rate rather than failing the whole request).

- `GET /api/countries`
  Lists the full curated country dataset.

## Project Structure
src/main/java/com/travelconverter/
├── TravelConverterApplication.java # entry point
├── model/ # Country entity + response DTOs
├── repository/ # Spring Data JPA repository
├── service/ # ExchangeRateService, TravelService
├── controller/ # REST endpoints
└── config/DataSeeder.java # seeds 13 countries on startup
src/main/resources/
├── application.properties
└── static/ # index.html, style.css, script.js
Dockerfile # build + run container for deployment

## Features
- Live currency conversion (INR, USD, EUR by default; extensible to any of the ~160 currencies the API supports)
- Travel affordability ranking across 13 curated countries, with a "Best Value" badge on the top-ranked destination
- Country flags, culture highlights, and top attractions per destination (view via detail modal)
- Responsive dark-themed UI with skeleton loaders and toast error alerts

## Notes / Next Steps
- Cost-of-living figures in `DataSeeder.java` are approximate, curated for
  demo purposes — swap in Numbeo or your own researched data for accuracy.
- H2 is in-memory, so data resets every restart/deploy. Switch to a
  persistent MySQL/Postgres database in `application.properties` if the
  dataset needs to survive restarts.
- Consider adding: region/budget filters, pagination for a larger country
  list, and a persistent database for user accounts or saved trips.
