package com.platform.igrejapentecostalreformadaapi.controllers;

import com.platform.igrejapentecostalreformadaapi.data.response.JWTAuthResponse;
import config.TestConfigs;
import integrationtests.testcontainers.AbstractIntegrationTest;
import integrationtests.vo.AccountCredentialsVO;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

/*
* Integration tests use the same ApplicationContext (unless specifically set not to).
* The issue with that is that one of the tests can make changes in the context that
*  would affect the other integration tests, like changing state of some beans.
* For this reason there is an annotation @DirtiesContext which restores/cleans the
*  effects on the context after this specific test.
* This annotation is computation expensive, therefore you should use it only when necessary.
*
* @author J Asgarov (https://stackoverflow.com/users/12038714/j-asgarov)
 */

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext
@DisplayName("Authentication Controller (Integration Tests)")
public class AuthControllerTest extends AbstractIntegrationTest  {

    private static JWTAuthResponse jwtAuthResponse;

    @Test
    @Order(1)
    @DisplayName("Test Given AccountCredentials When Sign Then Return JWTAuthResponse Object")
    public void givenAccountCredentials_WhenSign_ThenReturn_JWTAuthResponseObject() throws IOException {
        AccountCredentialsVO user = new AccountCredentialsVO("admin", "admin");

        jwtAuthResponse = given()
                .basePath("/auth/login")
                .port(TestConfigs.SERVER_PORT)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(user)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(JWTAuthResponse.class);


        assertNotNull(jwtAuthResponse.getAccessToken());
        assertNotNull(jwtAuthResponse.getRefreshToken());
        assertEquals("Bearer", jwtAuthResponse.getTokenType());
        assertTrue(jwtAuthResponse.getAuthenticated());

    }
}
