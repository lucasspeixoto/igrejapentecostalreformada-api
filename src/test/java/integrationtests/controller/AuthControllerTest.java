package integrationtests.controller;

import com.platform.igrejapentecostalreformadaapi.IgrejapentecostalreformadaApiApplication;
import config.TestConfigs;
import integrationtests.testcontainers.AbstractIntegrationTest;
import integrationtests.vo.AccountCredentialsVO;
import integrationtests.vo.TokenVO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = IgrejapentecostalreformadaApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthControllerTest extends AbstractIntegrationTest  {

    private static TokenVO tokenVO;

    @Test
    @Order(1)
    public void testSignin() throws IOException {
        AccountCredentialsVO user = new AccountCredentialsVO("admin", "admin");

        tokenVO = given()
                .basePath("/api/v1/auth/login")
                .port(TestConfigs.SERVER_PORT)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(user)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(TokenVO.class);


        assertNotNull(tokenVO.getAccessToken());
        assertEquals("Bearer", tokenVO.getTokenType());

    }
}
