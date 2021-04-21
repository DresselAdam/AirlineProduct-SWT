package UnitTest;

import com.ticket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ticketTest {

  /**
   * Valid ticket details are considered non-empty flight and customer IDs since these
   * are auto-populated from the Database, as well as seats within a range of 1-9 inclusive.
   */

  private com.ticket ticket;

  @BeforeEach
  public void setup(){
    ticket = new ticket();
  }

  @Test
  void invalidSeat_ValidFlightCustomer() {
    String flightID = "FO0003";
    String customerID = "CS004";
    String numOfSeats = "-5";

    assertEquals(false, ticket.validateTicketDetails(flightID, customerID, numOfSeats));
  }

  @Test
  void invalidSeatCustomer_ValidFlight() {
    String flightID = "FO0003";
    String customerID = "";
    String numOfSeats = "0";

    assertEquals(false, ticket.validateTicketDetails(flightID, customerID, numOfSeats));
  }

  @Test
  void invalidSeatFlight_ValidCustomer() {
    String flightID = "";
    String customerID = "CS004";
    String numOfSeats = "0";

    assertEquals(false, ticket.validateTicketDetails(flightID, customerID, numOfSeats));
  }

  @Test
  void invalidSeatFlightCustomer() {
    String flightID = "";
    String customerID = "";
    String numOfSeats = "-2";

    assertEquals(false, ticket.validateTicketDetails(flightID, customerID, numOfSeats));
  }

  @Test
  void validSeatFlightCustomer() {
    String flightID = "FO005";
    String customerID = "CS005";
    String numOfSeats = "9";

    assertEquals(true, ticket.validateTicketDetails(flightID, customerID, numOfSeats));
  }

  @Test
  void validSeatCustomer_InvalidFlight() {
    String flightID = "";
    String customerID = "CS004";
    String numOfSeats = "1";

    assertEquals(false, ticket.validateTicketDetails(flightID, customerID, numOfSeats));
  }

  @Test
  void validSeatFlight_InvalidCustomer() {
    String flightID = "FO002";
    String customerID = "";
    String numOfSeats = "3";

    assertEquals(false, ticket.validateTicketDetails(flightID, customerID, numOfSeats));
  }

  @Test
  void validSeat_InvalidFlightCustomer() {
    String flightID = "";
    String customerID = "";
    String numOfSeats = "5";

    assertEquals(false, ticket.validateTicketDetails(flightID, customerID, numOfSeats));
  }

  @Test
  void seatsOver_ValidFlightCustomer() {
    String flightID = "FO002";
    String customerID = "CS004";
    String numOfSeats = "10";

    assertEquals(false, ticket.validateTicketDetails(flightID, customerID, numOfSeats));
  }

  @Test
  void seatsOver_ValidFlight() {
    String flightID = "FO002";
    String customerID = "";
    String numOfSeats = "15";

    assertEquals(false, ticket.validateTicketDetails(flightID, customerID, numOfSeats));
  }

  @Test
  void seatsOver_ValidCustomer() {
    String flightID = "";
    String customerID = "CS001";
    String numOfSeats = "13";

    assertEquals(false, ticket.validateTicketDetails(flightID, customerID, numOfSeats));
  }

  @Test
  void seatsOver_InvalidFlightCustomer() {
    String flightID = "";
    String customerID = "";
    String numOfSeats = "10";

    assertEquals(false, ticket.validateTicketDetails(flightID, customerID, numOfSeats));
  }

  @Test
  void validFlightBooking(){
    String ticketid = "TO005";
    String flightid = "F0003";
    String custid = "CS004";
    String flightclass = "Economy";
    String price = "9000";
    String seats = "1";
    String date = "2021-04-19";
    boolean isValid = true;

    assertEquals("ticket booked", ticket.bookTicket(isValid, ticketid, flightid, custid,
        flightclass,price, seats, date));
  }

  @Test
  void invalidFlightBooking() {
    String ticketid = "TO005";
    String flightid = "";
    String custid = "";
    String flightclass = "Economy";
    String price = "9000";
    String seats = "11";
    String date = "2021-04-19";
    boolean isValid = false;

    assertEquals("ticket could not be booked", ticket.bookTicket(isValid, ticketid, flightid, custid,
        flightclass,price, seats, date));
  }

  @Test
  void validCustomerId(){
    String customerId = "CS001";

    assertEquals("Customer located", ticket.searchForCustomer(customerId));
  }

  @Test
  void invalidCustomerId(){
    String customerId = "";

    assertEquals("Record not found", ticket.searchForCustomer(customerId));
  }

  @Test
  void flightExistsTest(){
    String source = "India";
    String depart = "Uk";

    assertEquals("JetBlue", ticket.searchForTickets(source, depart));
  }

  @Test
  void flightDoesNotExistTest(){
    String source = "China";
    String depart = "China";

    assertEquals("", ticket.searchForTickets(source, depart));
  }

  @AfterEach
  public void tearDown(){
    ticket = null;
  }
}
