package com.natwest.restservice;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RestController
public class PrimesController {

    @GetMapping("/primes/{initial}")
    @Cacheable("primes")
    public Primes getPrimes(@PathVariable("initial") int initial,
                            @RequestParam(value = "algorithm", defaultValue = "sieve") String algorithm
                            /*@RequestParam(value = "xml", defaultValue = "false") boolean inXML*/) {
        List<Integer> primes = new LinkedList<>();
        if (algorithm.equals("sieve")) { // Sieve of Eratosthenes
            System.out.println("Calculating primes based on the Sieve of Eratosthenes Method");
            boolean[] arePrimes = new boolean[initial+1];
            Arrays.fill(arePrimes, true);

            for (int p = 2; p * p <= initial; p++) {
                if (arePrimes[p]) { //unmarked
                    for (int i = p * p; i <= initial; i += p) {
                        arePrimes[i] = false;
                    }
                }
            }

            for (int i = 2; i <= initial; i++) {
                if (arePrimes[i]) {
                    primes.add(i);
                }
            }
        } else { // Brute Force
            System.out.println("Calculating primes based on the Brute Force Method");
            if (initial >= 2) {
                primes.add(2);
            }

            for (int i = 3; i <= initial; i++) {
                if (isPrime(i)) {
                    primes.add(i);
                }
            }
        }
        return new Primes(initial, primes);
    }

    public boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
