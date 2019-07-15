package tickets;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private static Ticket t10 = new Ticket("VALID_YESTERDAY", "BLOCKED", "C:\\result.json");
    private static Ticket t11 = new Ticket("VALID_YESTERDAY", "NOT_STARTED", "C:\\result.json");
    private static Ticket t12 = new Ticket("VALID_YESTERDAY", "REFUNDED", "C:\\result.json");
    private static Ticket t13 = new Ticket("NOT_STARTED", "BLOCKED", "C:\\result.json");
    private static Ticket t14 = new Ticket("NOT_STARTED", "EXPIRED", "C:\\result.json");
    private static Ticket t15 = new Ticket("NOT_STARTED", "REFUNDED", "C:\\result.json");
    private static Ticket t16 = new Ticket("VALID_TODAY", "BLOCKED", "C:\\result.json");
    private static Ticket t17 = new Ticket("VALID_TODAY", "EXPIRED", "C:\\result.json");
    private static Ticket t18 = new Ticket("VALID_TODAY", "NOT_STARTED", "C:\\result.json");
    private static Ticket t19 = new Ticket("VALID_TODAY", "REFUNDED", "C:\\result.json");

    static Stream<Ticket> setUpTickets() {
        return Stream.of(t, t1, t2, t3, t4, t5, t6, t7, t8, t9);
    }

    static Stream<Ticket> setUpTicketsValidStatus() {
        return Stream.of(t4, t8, t9, t6);
    }

    @ParameterizedTest
    @MethodSource("setUpTicketsValidStatus")
    void initializeValidDatesTest(Ticket ticket) {

        ticket.initializeValidDates();
        assertTrue(ticket.getValidityPeriod().getStartDate().isBefore(LocalDate.now()));
        assertTrue(ticket.getValidityPeriod().getEndDate().isAfter(LocalDate.now()));
        if (ticket.getValidityDate() != null) {
            assertTrue(ticket.getValidityDate().isAfter(ticket.getValidityPeriod().getStartDate()) || ticket.getValidityDate().isEqual(ticket.getValidityPeriod().getStartDate()));
            assertTrue(ticket.getValidityDate().isBefore(ticket.getValidityPeriod().getEndDate()) || ticket.getValidityDate().isEqual(ticket.getValidityPeriod().getEndDate()));
        }
    }

    @ParameterizedTest
    @MethodSource("setUpTickets")
    void checkInputTest(Ticket ticket) {
        assertDoesNotThrow(ticket::checkInput);
    }

    static Stream<Ticket> setUpTicketsThrowing() {
        return Stream.of(t10, t11, t12, t13, t14, t15, t16, t17, t18, t19);
    }

    @ParameterizedTest
    @MethodSource("setUpTicketsThrowing")
    void checkInputThrowsTest(Ticket ticket) {
        assertThrows(IllegalArgumentException.class, ticket::checkInput);
    }
}