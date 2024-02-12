package com.platform.igrejapentecostalreformadaapi.controllers.register;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.platform.igrejapentecostalreformadaapi.IgrejapentecostalreformadaApiApplication;
import com.platform.igrejapentecostalreformadaapi.data.response.JWTAuthResponse;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.entities.register.Contact;
import com.platform.igrejapentecostalreformadaapi.repositories.UserRepository;
import com.platform.igrejapentecostalreformadaapi.repositories.register.ContactRepository;
import com.platform.igrejapentecostalreformadaapi.utils.constants.MediaType;

import config.TestConfigs;

import integrationtests.testcontainers.AbstractIntegrationTest;
import integrationtests.vo.AccountCredentialsVO;

import org.hamcrest.CoreMatchers;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Optional;

import static io.restassured.RestAssured.given;

@SpringBootTest(
        classes = IgrejapentecostalreformadaApiApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@AutoConfigureMockMvc
@DirtiesContext
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Contact Controller (Integration Tests)")
public class ContactControllerTest extends AbstractIntegrationTest {

    //private static RequestSpecification specification;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private static ObjectMapper objectMapper;

    public Contact contact;

    public User user;

    private static String accessToken;

    public static SimpleDateFormat simpleDateFormat;

    @BeforeAll
    public static void setup() {
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }


    @BeforeEach
    public void config() throws ParseException {
        setUser();
    }

    @Test
    @Order(0)
    @DisplayName("Integration Test to Login with Credentials")
    public void givenAccountCredentials_whenLogin_ThenReturnAuthorizationAccessToken() throws IOException {
        AccountCredentialsVO user = new AccountCredentialsVO("admin", "admin");

        accessToken = given()
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
                .as(JWTAuthResponse.class)
                .getAccessToken();

        /*
        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + accessToken)
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .build();
         */
    }

    @Test
    @Order(1)
    @DisplayName("Integration Test to Create a Contact (Success Case)")
    public void givenContactObject_WhenCreateContact_ThenReturnSavedContact() throws Exception {
        //given - precondition or setup
        //this.setUser();

        //when - action or the behaviour we`re testing
        String token = "Bearer " + accessToken;

        String requestUrl = "/api/v1/contacts/create/{userId}";

        long contactId = contact.getId();

        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.post(requestUrl, contactId, contact)
                        .contentType(TestConfigs.CONTENT_TYPE_JSON)
                        .header(TestConfigs.HEADER_PARAM_AUTHORIZATION, token)
                        .content(objectMapper.writeValueAsString(contact))
        );

        //then - verify the result or output using assert statements
        response
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sex",
                        CoreMatchers.is(contact.getSex())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cellphone",
                        CoreMatchers.is(contact.getCellphone())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.telephone",
                        CoreMatchers.is(contact.getTelephone())));
    }

    @Test
    @Order(2)
    @DisplayName("Integration Test to Create a Contact (Failed Case With Non existing User)")
    public void givenContactObject_WhenCreateContact_ThenThrowAnIsNotFoundException() throws Exception {
        //given - precondition or setup
        //this.setUser();

        //when - action or the behaviour we`re testing
        String token = "Bearer " + accessToken;

        String requestUrl = "/api/v1/contacts/create/{userId}";

        long contactId = 3L;

        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.post(requestUrl, contactId, contact)
                        .contentType(TestConfigs.CONTENT_TYPE_JSON)
                        .header(TestConfigs.HEADER_PARAM_AUTHORIZATION, token)
                        .content(objectMapper.writeValueAsString(contact))
        );

        //then - verify the result or output using assert statements
        String noUserErrorMessage = "Usuário não encontrado com id 3";

        response
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", CoreMatchers.is(noUserErrorMessage)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(3)
    @DisplayName("Integration Test to Create a Contact (Failed Case With Already Exists Contact)")
    public void givenContactObject_WhenCreateContact_ThenThrowAlreadyExistsException() throws Exception {
        //given - precondition or setup
        //this.setUser();

        //when - action or the behaviour we`re testing
        String token = "Bearer " + accessToken;

        String requestUrl = "/api/v1/contacts/create/{userId}";

        long contactId = contact.getId();

        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.post(requestUrl, contactId, contact)
                        .contentType(TestConfigs.CONTENT_TYPE_JSON)
                        .header(TestConfigs.HEADER_PARAM_AUTHORIZATION, token)
                        .content(objectMapper.writeValueAsString(contact))
        );

        //then - verify the result or output using assert statements
        String userAlreadyHasContactErrorMessage = "Usuário ja possui contato!";

        response
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", CoreMatchers.is(userAlreadyHasContactErrorMessage)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(4)
    @DisplayName("Integration Test to Find All Contacts")
    public void givenListOfContacts_WhenFindAll_ThenReturnContactsList() throws Exception {
        //given - precondition or setup
        //this.setUser();

        //when - action or the behaviour we`re testing
        String token = "Bearer " + accessToken;

        String requestUrl = "/api/v1/contacts";

        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.get(requestUrl)
                        .contentType(TestConfigs.CONTENT_TYPE_JSON)
                        .header(TestConfigs.HEADER_PARAM_AUTHORIZATION, token)
                        .content(objectMapper.writeValueAsString(contact))
        );

        //then - verify the result or output using assert statements
        response
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sex",
                        CoreMatchers.is(contact.getSex())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cellphone",
                        CoreMatchers.is(contact.getCellphone())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].telephone",
                        CoreMatchers.is(contact.getTelephone())));
    }

    @Test
    @Order(5)
    @DisplayName("Integration Test to Find a Contact By Id (Success Case)")
    public void givenContactObject_WhenFindById_ThenReturnContactObject() throws Exception {
        //given - precondition or setup
        //this.setUser();

        //when - action or the behaviour we`re testing
        String token = "Bearer " + accessToken;

        String requestUrl = "/api/v1/contacts/{id}";

        long contactId = contact.getId();

        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.get(requestUrl, contactId)
                        .contentType(TestConfigs.CONTENT_TYPE_JSON)
                        .header(TestConfigs.HEADER_PARAM_AUTHORIZATION, token)
                        .content(objectMapper.writeValueAsString(contact))
        );

        //then - verify the result or output using assert statements
        response
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sex",
                        CoreMatchers.is(contact.getSex())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cellphone",
                        CoreMatchers.is(contact.getCellphone())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.telephone",
                        CoreMatchers.is(contact.getTelephone())));
    }

    @Test
    @Order(6)
    @DisplayName("Integration Test to Find a Contact By Id (Failed Case)")
    public void givenContactObject_WhenFindById_ThenThrowAnIsNotFoundException() throws Exception {
        //given - precondition or setup
        //this.setUser();

        //when - action or the behaviour we`re testing
        String token = "Bearer " + accessToken;

        String requestUrl = "/api/v1/contacts/{id}";

        long contactId = 3L;

        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.get(requestUrl, contactId)
                        .contentType(TestConfigs.CONTENT_TYPE_JSON)
                        .header(TestConfigs.HEADER_PARAM_AUTHORIZATION, token)
                        .content(objectMapper.writeValueAsString(contact))
        );

        //then - verify the result or output using assert statements
        String contactNotFoundErrorMessage = "Contato não encontrado com id 3";

        response
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", CoreMatchers.is(contactNotFoundErrorMessage)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(7)
    @DisplayName("Integration Test to Update (Success Case)")
    public void givenContactObject_WhenUpdate_ThenReturnUpdatedContactObject() throws Exception {
        //given - precondition or setup
        //this.setUser();

        contact.setSex("Feminino");

        //when - action or the behaviour we`re testing
        String token = "Bearer " + accessToken;

        String requestUrl = "/api/v1/contacts/update";

        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.put(requestUrl, contact)
                        .contentType(TestConfigs.CONTENT_TYPE_JSON)
                        .header(TestConfigs.HEADER_PARAM_AUTHORIZATION, token)
                        .content(objectMapper.writeValueAsString(contact))
        );

        //then - verify the result or output using assert statements
        response
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sex",
                        CoreMatchers.is("Feminino")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cellphone",
                        CoreMatchers.is(contact.getCellphone())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.telephone",
                        CoreMatchers.is(contact.getTelephone())));
    }

    @Test
    @Order(8)
    @DisplayName("Integration Test to Update (Failed Case)")
    public void givenContactObject_WhenUpdate_ThenThrowAnIsNotFoundException() throws Exception {
        //given - precondition or setup
        //this.setUser();

        contact.setId(3L);

        //when - action or the behaviour we`re testing
        String token = "Bearer " + accessToken;

        String requestUrl = "/api/v1/contacts/update";

        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.put(requestUrl, contact)
                        .contentType(TestConfigs.CONTENT_TYPE_JSON)
                        .header(TestConfigs.HEADER_PARAM_AUTHORIZATION, token)
                        .content(objectMapper.writeValueAsString(contact))
        );

        //then - verify the result or output using assert statements
        String contactNotFoundErrorMessage = "Contato não encontrado com id 3";

        response
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", CoreMatchers.is(contactNotFoundErrorMessage)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    /**
     * Sets the user for the tests.
     *
     * @throws ParseException if the date is in an invalid format
     */
    private void setUser() throws ParseException {
        Optional<User> optionalUser = userRepository.findById(1L);

        if(optionalUser.isPresent()) {
            this.user = optionalUser.get();
            this.setContact();
        } else {
            this.user = null;
        }

    }

    private void setContact() throws ParseException {
        this.contact = new Contact(
                1L,
                "Masculino",
                "19982621117",
                "19982621117",
                simpleDateFormat.parse("30/10/1991"),
                this.user
        );
    }
}
