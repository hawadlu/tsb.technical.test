package com.tsb.technical.test;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.tsb.technical.test.entities.AccountHolder;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode =  DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CashCardApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldCreateAccountHolder() {
        AccountHolder accountHolder = new AccountHolder(null, "test", "test");

        ResponseEntity<Void> createResponse = restTemplate
                .postForEntity("/accountHolder", accountHolder, Void.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // Get the location of the created object and make sure that it actually exists
        URI itemLocation = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate
                .getForEntity(itemLocation, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        String returnedName = documentContext.read("$.name");
        String returnedEmail = documentContext.read("$.email");
        assertThat(returnedName).isEqualTo("test");
        assertThat(returnedEmail).isEqualTo("test");
    }
}
