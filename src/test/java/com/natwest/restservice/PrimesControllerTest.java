package com.natwest.restservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PrimesControllerTest {

    @Autowired
    PrimesController controller;

    @Test
    void getsTheCorrectPrimes() {
        int initial0 = 10;
        int[] expected0 = {2, 3, 5, 7};
        int initial1 = 11; // to show the method calculates primes upto and including the initial range
        int[] expected1 = {2, 3, 5, 7, 11};
        Assertions.assertArrayEquals(expected0, controller.getPrimes(initial0).primes().stream().mapToInt(i -> i).toArray());
        Assertions.assertArrayEquals(expected1, controller.getPrimes(initial1).primes().stream().mapToInt(i -> i).toArray());
    }
}
