# Currency Converter & Travel Explorer

A Spring Boot web app that converts currency AND tells you how many days
your money could fund a trip to various countries, based on curated
cost-of-living data.

## Tech Stack
- Java 17, Spring Boot 3.3 (Web, Data JPA)
- H2 in-memory database (auto-seeded on startup)
- Frankfurter API for live exchange rates (free, no API key needed)
- Plain HTML/CSS/JS frontend served from `src/main/resources/static`

## How to Run

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

## API Endpoints

- `GET /api/convert?from=INR&to=USD&amount=100000`
  Simple currency conversion.

- `GET /api/travel-recommendations?from=INR&amount=100000`
  Returns all seeded countries ranked by how many travel days the
  amount affords, richest-value first.

- `GET /api/countries`
  Lists the full curated country dataset.

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
