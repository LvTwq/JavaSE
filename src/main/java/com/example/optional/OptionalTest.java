package com.example.optional;

import java.util.Optional;

/** @author 吕茂陈 */
public class OptionalTest {

  public static void main(String[] args) {
    FlightTicketInfo flightTicketInfo = null;

    Optional<Optional<String>> s1 =
        Optional.ofNullable(flightTicketInfo).map(OptionalTest::getOrderNumber);

    Optional<String> s2 =
        Optional.ofNullable(flightTicketInfo).map(FlightTicketInfo::getOrderNumber);

    Optional<String> s3 =
        Optional.ofNullable(flightTicketInfo).flatMap(OptionalTest::getOrderNumber);
  }

  private static Optional<String> getOrderNumber(FlightTicketInfo flightTicketInfo) {
    return Optional.ofNullable(flightTicketInfo).map(f -> f.getOrderNumber());
  }
}

class FlightTicketInfo {

  private String orderNumber;

  public String getOrderNumber() {
    return orderNumber;
  }
}
