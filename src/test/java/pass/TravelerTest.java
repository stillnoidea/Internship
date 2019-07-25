package pass;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TravelerTest {

    static Stream<Traveler> setUpTravelers() {
        return Stream.of(new Traveler("", "Nowak", ""),
                new Traveler("Joanna", "Kowalska", "su12313fgy"),
                new Traveler("George", "Lee", "surgeyufg12312313"),
                new Traveler("Steve", "Rent", ""),
                new Traveler("Adnrzej", "Kosiński", "1231231fgyufgy"),
                new Traveler("Lukrecja", "Bardelnow", "123232131"));
    }

    @ParameterizedTest
    @MethodSource("setUpTravelers")
    void getSecretFullName(Traveler traveler) {
        if (traveler.getName() != null) {
            String name = traveler.getSecuredFullName();
            assertEquals(10, name.length());
            assertNotEquals('*', name.charAt(0));
            assertEquals('.', name.charAt(1));
            assertEquals(' ', name.charAt(2));
            assertNotEquals('*', name.charAt(3));
            assertEquals('*', name.charAt(4));
            assertEquals('*', name.charAt(7));
            assertEquals('*', name.charAt(8));
            assertNotEquals('*', name.charAt(9));
        }
    }

    @ParameterizedTest
    @MethodSource("setUpTravelers")
    void getSecretPassport(Traveler traveler) {
        if (traveler.getPassportNo() != null) {
            String passport = traveler.getSecuredPassport();
            System.out.println(passport);
            assertEquals(8, passport.length());
            assertEquals('*', passport.charAt(0));
            assertEquals('*', passport.charAt(1));
            assertEquals('*', passport.charAt(4));
            assertEquals('*', passport.charAt(5));
            assertNotEquals('*', passport.charAt(6));
            assertNotEquals('*', passport.charAt(7));
        }
    }
}