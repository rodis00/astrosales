package com.github.rodis00.astrosales.dataLoader;

import com.github.rodis00.astrosales.flight.FlightRepository;
import com.github.rodis00.astrosales.flight.entity.Flight;
import com.github.rodis00.astrosales.user.UserRepository;
import com.github.rodis00.astrosales.user.entity.Role;
import com.github.rodis00.astrosales.user.entity.User;
import com.github.rodis00.astrosales.userProfile.entity.UserProfile;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final static Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Override
    public void run(String... args) throws Exception {
        Optional<User> adminAccount = userRepository.findByRole(Role.ADMIN);
        Optional<User> userAccount = userRepository.findByRole(Role.USER);
        List<Flight> flights = flightRepository.findAll();

        if (adminAccount.isEmpty()) {
            UserProfile userProfile = new UserProfile();
            userProfile.setFirstName("Admin");
            userProfile.setLastName("Admin");
            userProfile.setPhoneNumber("123456789");

            User admin = new User();
            admin.setRole(Role.ADMIN);
            admin.setEmail("admin@gmail.com");
            admin.setPassword(new BCryptPasswordEncoder().encode("admin123"));
            admin.setUserProfile(userProfile);
            userRepository.save(admin);

            logger.info("ADMIN ACCOUNT CREATED");
        }

        if (userAccount.isEmpty()) {
            UserProfile userProfile = new UserProfile();
            userProfile.setFirstName("User");
            userProfile.setLastName("User");
            userProfile.setPhoneNumber("123456789");

            User user = new User();
            user.setRole(Role.USER);
            user.setEmail("user@gmail.com");
            user.setPassword(new BCryptPasswordEncoder().encode("user123"));
            user.setUserProfile(userProfile);
            userRepository.save(user);

            logger.info("USER ACCOUNT CREATED");
            System.out.println("User login: " + user.getEmail());
            System.out.println("User password: " + "user123");
        }

        if (flights.isEmpty()) {
            Flight flight = new Flight();
            flight.setDateOfFlight(LocalDateTime.of(2040, 1, 1, 0, 0));
            flight.setStartingPlace("Warsaw");
            flight.setLandingPlace("Księżyc");
            flight.setTicketPrice(100);
            flight.setTicketPriceVip(150);
            flight.setAvailabilityOfPlaces(50);

            Flight flight2 = new Flight();
            flight.setDateOfFlight(LocalDateTime.of(2028, 12, 4, 12, 24));
            flight.setStartingPlace("Warsaw");
            flight.setLandingPlace("Mars");
            flight.setTicketPrice(150);
            flight.setTicketPriceVip(250);
            flight.setAvailabilityOfPlaces(50);

            Flight flight3 = new Flight();
            flight.setDateOfFlight(LocalDateTime.of(2030, 5, 15, 18, 18));
            flight.setStartingPlace("Lublin");
            flight.setLandingPlace("Jowisz");
            flight.setTicketPrice(200);
            flight.setTicketPriceVip(300);
            flight.setAvailabilityOfPlaces(50);

            flightRepository.saveAll(List.of(flight, flight2, flight3));
        }
    }
}
