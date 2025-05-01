package com.plataforma.prueba.infrastructure.adapter.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class PriceJpaEntityControllerTest {

  @LocalServerPort
  private int port;

  private static final int VALID_BRAND_ID = 1;
  private static final int VALID_PRODUCT_ID = 35455;
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

  @BeforeEach
  void setup() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = port;
  }

  static Stream<Arguments> priceTestCases() {
    return Stream.of(
        Arguments.of(LocalDateTime.of(2020, 6, 14, 10, 0), 1, 35.50,"EUR"),
        Arguments.of(LocalDateTime.of(2020, 6, 14, 16, 0), 2, 25.45,"EUR"),
        Arguments.of(LocalDateTime.of(2020, 6, 14, 21, 0), 1, 35.50,"EUR"),
        Arguments.of(LocalDateTime.of(2020, 6, 15, 10, 0), 3, 30.50,"EUR"),
        Arguments.of(LocalDateTime.of(2020, 6, 16, 21, 0), 4, 38.95,"EUR")
    );
  }

  @ParameterizedTest
  @MethodSource("priceTestCases")
  void should_return_correct_price_for_specific_date(
      LocalDateTime date, int expectedPriceList, double expectedPrice,String currency) {

    given()
        .param("brandId", VALID_BRAND_ID)
        .param("productId", VALID_PRODUCT_ID)
        .param("date", date.format(FORMATTER))
        .when()
        .get("/api/prices")
        .then()
        .statusCode(HttpStatus.OK.value())
        .contentType(ContentType.JSON)
        .body("brandId", equalTo(VALID_BRAND_ID))
        .body("productId", equalTo(VALID_PRODUCT_ID))
        .body("priceList", equalTo(expectedPriceList))
        .body("price", equalTo((float)expectedPrice))
        .body("currency", equalTo(currency))
        .body("startDate", notNullValue())
        .body("endDate", notNullValue());
  }

  @Test
  void should_get_not_found_when_price_does_not_exist() {
    LocalDateTime futureDate = LocalDateTime.of(2024, 6, 14, 10, 0);

    given()
        .param("brandId", VALID_BRAND_ID)
        .param("productId", VALID_PRODUCT_ID)
        .param("date", futureDate.format(FORMATTER))
        .when()
        .get("/api/prices")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value())
        .contentType(ContentType.JSON)
        .body("error_details", equalTo("PriceNotFoundException"))
        .body("error_message", containsString("No price found for product"))
        .body("result", equalTo(""))
        .body("timestamp", notNullValue());
  }

  @Test
  void should_get_not_found_when_call_method_without_all_parameters() {
    given()
        .param("productId", VALID_PRODUCT_ID)
        .param("date", LocalDateTime.now().format(FORMATTER))
        .when()
        .get("/api/prices")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());

    given()
        .param("brandId", VALID_BRAND_ID)
        .param("date", LocalDateTime.now().format(FORMATTER))
        .when()
        .get("/api/prices")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());

    given()
        .param("brandId", VALID_BRAND_ID)
        .param("productId", VALID_PRODUCT_ID)
        .when()
        .get("/api/prices")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());
  }

  @Test
  void should_get_bad_request_with_invalid_date_format() {
    given()
        .param("brandId", VALID_BRAND_ID)
        .param("productId", VALID_PRODUCT_ID)
        .param("date", "2020-14-14 10:00")
        .when()
        .get("/api/prices")
        .then()
        .statusCode(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  void should_validate_price_response_structure() {
    LocalDateTime testDate = LocalDateTime.of(2020, 6, 16, 21, 0);

    given()
        .param("brandId", VALID_BRAND_ID)
        .param("productId", VALID_PRODUCT_ID)
        .param("date", testDate.format(FORMATTER))
        .when()
        .get("/api/prices")
        .then()
        .statusCode(HttpStatus.OK.value())
        .contentType(ContentType.JSON)
        .body("$", hasKey("brandId"))
        .body("$", hasKey("productId"))
        .body("$", hasKey("priceList"))
        .body("$", hasKey("startDate"))
        .body("$", hasKey("endDate"))
        .body("$", hasKey("price"))
        .body("$", hasKey("currency"))
        .body("brandId", isA(Integer.class))
        .body("productId", isA(Integer.class))
        .body("priceList", isA(Integer.class))
        .body("price", isA(Float.class))
        .body("currency", isA(String.class));
  }

}