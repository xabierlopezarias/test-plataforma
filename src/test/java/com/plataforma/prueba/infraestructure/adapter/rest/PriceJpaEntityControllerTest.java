package com.plataforma.prueba.infraestructure.adapter.rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class PriceJpaEntityControllerTest {

  @LocalServerPort
  private int port;


  @BeforeEach
  void setup() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = port;
  }

  /**
   * This test ensures that each date specified with the brand 1 and product 35455
   * gets an object from the DB (otherwise we'll get a no content status).
   */

  @Test
  void should_get_a_price_from_the_given_dates() {
    for (LocalDateTime date : buildTestDates()) {
      Response response = when()
          .get("/api/prices?brandId=1&productId=35455&date=" + date)
          .then()
          .log().body()
          .extract().response();

      assertEquals(HttpStatus.OK.value(), response.getStatusCode(), "Expected HTTP status 200 OK");
    }
  }

  @Test
  void should_get_a_not_found_when_the_price_does_not_exist() {
      Response response = when()
          .get("/api/prices?brandId=1&productId=35455&date=" +
                  LocalDateTime.of(2024, 6, 14, 10, 0))
          .then()
          .log().body()
          .extract().response();

      assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode(), "No price found for product 35455 in brand 1 at date 2024-06-14T10:00");

  }

  private List<LocalDateTime> buildTestDates() {
    return Arrays.asList(
        LocalDateTime.of(2020, 6, 14, 10, 0,0),
        LocalDateTime.of(2020, 6, 14, 16, 0,0),
        LocalDateTime.of(2020, 6, 14, 21, 0,0),
        LocalDateTime.of(2020, 6, 15, 10, 0,0),
        LocalDateTime.of(2020, 6, 16, 21, 0,0)
    );
  }
}
