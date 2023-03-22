package com.natwest.restservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class PrimesControllerRealIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnPrimesUptoAGivenNumber() throws Exception {
        int[] expectedPrimes = {2, 3, 5, 7};

        // getting java.lang.AssertionError: Got a list of values [2,3,5,7] instead of the expected single value [2,3,5,7]
        // if we don't use JSONArray -> Array
        JSONParser jsonParser= new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE);
        ObjectMapper objectMapper = new ObjectMapper();

        JSONArray listJson = (JSONArray) jsonParser.parse(objectMapper.writeValueAsString(expectedPrimes));

        this.mockMvc.perform(get("/primes/10")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.initial").value(10))
                .andExpect(jsonPath("$.primes").value(Matchers.containsInAnyOrder(listJson.toArray())));

        // test that caching is occurring - doesn't seem legit though
        for (int i = 0; i < 2; i++) {
            long startTime = System.nanoTime();
            this.mockMvc.perform(get("/primes/1010000")).andDo(print());
            long now = System.nanoTime();
            System.out.println((now - startTime) + " nanoseconds: " + i);
        }
    }

    @Test
    void throwExceptionForANonNumericValue() throws Exception {
        this.mockMvc.perform(get("/primes/non-numeric")).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void throwExceptionForInvalidPage() throws Exception {
        this.mockMvc.perform(get("/composites/")).andDo(print())
                .andExpect(status().isNotFound());
    }
}