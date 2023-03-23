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
    void getsTheCorrectPrimesUsingSieveOfEratosthenes() {
        int initial0 = 10;
        int[] expected0 = {2, 3, 5, 7};
        int initial1 = 11; // to show the method calculates primes upto and including the initial range
        int[] expected1 = {2, 3, 5, 7, 11};
        Assertions.assertArrayEquals(expected0, controller.getPrimes(initial0, "sieve").primes().stream().mapToInt(i -> i).toArray());
        Assertions.assertArrayEquals(expected1, controller.getPrimes(initial1, "sieve").primes().stream().mapToInt(i -> i).toArray());
    }

    @Test
    void getsTheCorrectPrimesBruteForce() {
        int initial0 = 10;
        int[] expected0 = {2, 3, 5, 7};
        int initial1 = 11; // to show the method calculates primes upto and including the initial range
        int[] expected1 = {2, 3, 5, 7, 11};
        Assertions.assertArrayEquals(expected0, controller.getPrimes(initial0, "bruteforce").primes().stream().mapToInt(i -> i).toArray());
        Assertions.assertArrayEquals(expected1, controller.getPrimes(initial1, "bruteforce").primes().stream().mapToInt(i -> i).toArray());
    }

    @Test
    void xmlMethodGetsTheResult() {
        int initial0 = 10;
        int[] expected0 = {2, 3, 5, 7};
        Assertions.assertArrayEquals(expected0, controller.getPrimesWithXml(initial0, "sieve").primes().stream().mapToInt(i -> i).toArray());
    }
}
