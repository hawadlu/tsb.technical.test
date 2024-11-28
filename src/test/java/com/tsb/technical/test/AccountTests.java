package com.tsb.technical.test;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.tsb.technical.test.entities.Account;
import com.tsb.technical.test.entities.AccountHolder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode =  DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AccountTests {
    @Autowired
    TestRestTemplate restTemplate;

//    @Test
//    void shouldCreateAccount() {
//        Account account = new Account(null, 1L, 1L, 1L);
//
//        ResponseEntity<Void> createResponse = restTemplate
//                .postForEntity("/account", account, Void.class);
//
//        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//
//        // Get the location of the created object and make sure that it actually exists
//        URI itemLocation = createResponse.getHeaders().getLocation();
//        ResponseEntity<String> getResponse = restTemplate
//                .getForEntity(itemLocation, String.class);
//        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
//
//        List<Long> amounts = documentContext.read("$..accountHolderId");
//        assertThat(amounts).containsExactly(1L);
//
////        Number holder = documentContext.read("$[0].accountHolderId");
////        Number number = documentContext.read("$[0].accountNumber");
////        Number balance = documentContext.read("$[0].balance");
////        assertThat(holder).isEqualTo(1);
////        assertThat(number).isEqualTo(1);
////        assertThat(balance).isEqualTo(1);
//    }
}
