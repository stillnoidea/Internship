package tickets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {
    private static Ticket t = new Ticket("NOT_VALID", "BLOCKED", "C:\\result.json");
    private static Ticket t1 = new Ticket("NOT_VALID", "EXPIRED", "C:\\result.json");
    private static Ticket t2 = new Ticket("NOT_VALID", "NOT_STARTED", "C:\\result.json");
    private static Ticket t3 = new Ticket("NOT_VALID", "REFUNDED", "C:\\result.json");
    private static Ticket t4 = new Ticket("NOT_VALID", "VALID", "C:\\result.json");
    private static Ticket t5 = new Ticket("VALID_YESTERDAY", "EXPIRED", "C:\\result.json");
    private static Ticket t6 = new Ticket("VALID_YESTERDAY", "VALID", "C:\\result.json");
    private static Ticket t7 = new Ticket("NOT_STARTED", "NOT_STARTED", "C:\\result.json");
    private static Ticket t8 = new Ticket("NOT_STARTED", "VALID", "C:\\result.json");
    private static Ticket t9 = new Ticket("VALID_TODAY", "VALID", "C:\\result.json");

    private static LocalDate today = LocalDate.now();


    static Stream<Ticket> setUpTicketsValidStatus() {
        return Stream.of(t4, t8, t9, t6);
    }


    @ParameterizedTest
    @MethodSource("setUpTicketsValidStatus")
    void initializeValidDatesTest(Ticket ticket) {
        assertTrue(ticket.getValidityPeriod().getStartDate().isBefore(today));
        boolean bl = ticket.getValidityPeriod().getEndDate().isAfter(today) || ticket.getValidityPeriod().getEndDate().isEqual(today);
        assertTrue(bl);
        if (ticket.getValidityDate() != null) {
            assertTrue(ticket.getValidityDate().isAfter(ticket.getValidityPeriod().getStartDate()) || ticket.getValidityDate().isEqual(ticket.getValidityPeriod().getStartDate()));
            assertTrue(ticket.getValidityDate().isBefore(ticket.getValidityPeriod().getEndDate()) || ticket.getValidityDate().isEqual(ticket.getValidityPeriod().getEndDate()));
        }
    }

    static Stream<Ticket> setUpTicketsNotStartedStatus() {
        return Stream.of(t2, t7);
    }

    @ParameterizedTest
    @MethodSource("setUpTicketsNotStartedStatus")
    void initializeNotStartedDatesTest(Ticket ticket) {
        if (ticket.getValidityPeriod().getStartDate() != null) {
            assertTrue(ticket.getValidityPeriod().getStartDate().isAfter(today));
            assertTrue(ticket.getValidityPeriod().getStartDate().isBefore(ticket.getValidityPeriod().getEndDate()));
            if (ticket.getValidityDate() != null) {
                assertTrue(ticket.getValidityDate().isAfter(ticket.getValidityPeriod().getStartDate()) || ticket.getValidityDate().isEqual(ticket.getValidityPeriod().getStartDate()));
                assertTrue(ticket.getValidityDate().isBefore(ticket.getValidityPeriod().getEndDate()) || ticket.getValidityDate().isEqual(ticket.getValidityPeriod().getEndDate()));
            }
        }
    }

    static Stream<Ticket> setUpTicketsExpiredStatus() {
        return Stream.of(t1, t5);
    }

    @ParameterizedTest
    @MethodSource("setUpTicketsExpiredStatus")
    void initializeExpiredDatesTest(Ticket ticket) {
        assertTrue(ticket.getValidityPeriod().getEndDate().isBefore(today));
        assertTrue(ticket.getValidityPeriod().getStartDate().isBefore(ticket.getValidityPeriod().getEndDate()));
        if (ticket.getValidityDate() != null) {
            assertTrue(ticket.getValidityDate().isAfter(ticket.getValidityPeriod().getStartDate()) || ticket.getValidityDate().isEqual(ticket.getValidityPeriod().getStartDate()));
            assertTrue(ticket.getValidityDate().isBefore(ticket.getValidityPeriod().getEndDate()) || ticket.getValidityDate().isEqual(ticket.getValidityPeriod().getEndDate()));
        }
    }

    static Stream<Ticket> setUpTicketsBlockedOrRefundedStatus() {
        return Stream.of(t, t3);
    }

    @ParameterizedTest
    @MethodSource("setUpTicketsBlockedOrRefundedStatus")
    void initializeBlockedOrRefundedDatesTest(Ticket ticket) {
        if (ticket.getValidityPeriod().getStartDate() != null) {
            assertTrue(ticket.getValidityPeriod().getStartDate().isBefore(ticket.getValidityPeriod().getEndDate()));
            if (ticket.getValidityDate() != null) {
                assertTrue(ticket.getValidityDate().isAfter(ticket.getValidityPeriod().getStartDate()) || ticket.getValidityDate().isEqual(ticket.getValidityPeriod().getStartDate()));
                assertTrue(ticket.getValidityDate().isBefore(ticket.getValidityPeriod().getEndDate()) || ticket.getValidityDate().isEqual(ticket.getValidityPeriod().getEndDate()));
            }
        }
    }

    static Stream<Ticket> setUpTickets() {
        return Stream.of(t, t1, t2, t3, t4, t5, t6, t7, t8, t9);
    }

    @ParameterizedTest
    @MethodSource("setUpTickets")
    void checkInputTest(Ticket ticket) {
        assertDoesNotThrow(ticket::checkInput);
    }

    @Test
    void checkInputThrowsTest() {
        assertThrows(IllegalArgumentException.class, () -> new Ticket("VALID_YESTERDAY", "BLOCKED", "C:\\result.json"));
        assertThrows(IllegalArgumentException.class, () -> new Ticket("VALID_YESTERDAY", "NOT_STARTED", "C:\\result.json"));
        assertThrows(IllegalArgumentException.class, () -> new Ticket("VALID_YESTERDAY", "REFUNDED", "C:\\result.json"));
        assertThrows(IllegalArgumentException.class, () -> new Ticket("NOT_STARTED", "BLOCKED", "C:\\result.json"));
        assertThrows(IllegalArgumentException.class, () -> new Ticket("NOT_STARTED", "EXPIRED", "C:\\result.json"));
        assertThrows(IllegalArgumentException.class, () -> new Ticket("NOT_STARTED", "REFUNDED", "C:\\result.json"));
        assertThrows(IllegalArgumentException.class, () -> new Ticket("VALID_TODAY", "BLOCKED", "C:\\result.json"));
        assertThrows(IllegalArgumentException.class, () -> new Ticket("VALID_TODAY", "EXPIRED", "C:\\result.json"));
        assertThrows(IllegalArgumentException.class, () -> new Ticket("VALID_TODAY", "NOT_STARTED", "C:\\result.json"));
        assertThrows(IllegalArgumentException.class, () -> new Ticket("VALID_TODAY", "REFUNDED", "C:\\result.json"));
    }
}