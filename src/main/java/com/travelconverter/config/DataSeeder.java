package com.travelconverter.config;

import com.travelconverter.model.Country;
import com.travelconverter.repository.CountryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CountryRepository countryRepository;

    public DataSeeder(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void run(String... args) {
        if (countryRepository.count() > 0) {
            return; // already seeded
        }

        countryRepository.save(new Country(
                "Vietnam", "VND", "Southeast Asia",
                600000.0, 1200000.0,
                "Ha Long Bay, Hoi An Ancient Town, Ho Chi Minh City, Sapa Rice Terraces",
                "Rich blend of French colonial architecture, Buddhist temples, and vibrant street food culture."
        ));

        countryRepository.save(new Country(
                "Thailand", "THB", "Southeast Asia",
                1200.0, 2500.0,
                "Bangkok Grand Palace, Phuket beaches, Chiang Mai temples, Ayutthaya ruins",
                "Known for ornate temples, Muay Thai, floating markets, and warm hospitality."
        ));

        countryRepository.save(new Country(
                "Nepal", "NPR", "South Asia",
                2500.0, 5000.0,
                "Kathmandu Durbar Square, Pokhara, Everest Base Camp trek, Chitwan National Park",
                "Himalayan culture, ancient Newari architecture, and a major hub for trekking and Buddhism/Hinduism."
        ));

        countryRepository.save(new Country(
                "Indonesia", "IDR", "Southeast Asia",
                350000.0, 700000.0,
                "Bali temples, Borobudur, Komodo Island, Yogyakarta",
                "Diverse archipelago culture with Hindu-Balinese traditions and volcanic landscapes."
        ));

        countryRepository.save(new Country(
                "Sri Lanka", "LKR", "South Asia",
                8000.0, 15000.0,
                "Sigiriya Rock, Kandy Temple of the Tooth, Galle Fort, tea plantations",
                "Blend of Buddhist heritage, colonial forts, and lush hill country tea culture."
        ));

        countryRepository.save(new Country(
                "Turkey", "TRY", "Europe/Asia",
                1200.0, 2200.0,
                "Hagia Sophia, Cappadocia balloon rides, Pamukkale, Grand Bazaar",
                "Crossroads of Europe and Asia with Ottoman architecture and rich culinary traditions."
        ));

        countryRepository.save(new Country(
                "Georgia", "GEL", "Caucasus",
                80.0, 150.0,
                "Tbilisi Old Town, Kazbegi mountains, Batumi coast, wine region Kakheti",
                "One of the world's oldest wine-producing regions with a unique alphabet and mountain culture."
        ));

        countryRepository.save(new Country(
                "Egypt", "EGP", "North Africa",
                1000.0, 2000.0,
                "Pyramids of Giza, Luxor temples, Nile River cruise, Red Sea coast",
                "Home to ancient pharaonic civilization and some of the world's oldest monuments."
        ));

        countryRepository.save(new Country(
                "Philippines", "PHP", "Southeast Asia",
                1800.0, 3500.0,
                "Palawan lagoons, Boracay beaches, Chocolate Hills, Banaue Rice Terraces",
                "Spanish colonial heritage mixed with over 7,000 islands of distinct local traditions."
        ));

        countryRepository.save(new Country(
                "United States", "USD", "North America",
                80.0, 180.0,
                "New York City, Grand Canyon, Yellowstone, San Francisco",
                "Highly diverse culture with major global cities, national parks, and varied regional identities."
        ));

        countryRepository.save(new Country(
                "United Kingdom", "GBP", "Europe",
                60.0, 130.0,
                "London landmarks, Edinburgh Castle, Stonehenge, Lake District",
                "Rich royal history, world-class museums, and iconic literary and music heritage."
        ));

        countryRepository.save(new Country(
                "Japan", "JPY", "East Asia",
                8000.0, 16000.0,
                "Tokyo Shibuya, Kyoto temples, Mount Fuji, Osaka Castle",
                "Fusion of ancient tradition (temples, tea ceremonies) with cutting-edge modern culture."
        ));

        countryRepository.save(new Country(
                "Switzerland", "CHF", "Europe",
                100.0, 200.0,
                "Zurich, Jungfrau region, Lake Geneva, Matterhorn",
                "Alpine culture with precision craftsmanship, multilingual heritage, and scenic train journeys."
        ));

        System.out.println("Seeded " + countryRepository.count() + " countries into the database.");
    }
}
