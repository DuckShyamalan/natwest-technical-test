package com.natwest.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class PrimesController {

    @GetMapping("/primes/{initial}")
    public Primes getPrimes(@PathVariable("initial") int initial) {
        boolean[] arePrimes = new boolean[initial+1];
        Arrays.fill(arePrimes, true);

        for (int p = 2; p * p <= initial; p++) {
            if (arePrimes[p]) { //unmarked
                for (int i = p * 2; i <= initial; i += p) {
                    arePrimes[i] = false;
                }
            }
        }

        //perhaps make it a linked list?
        List<Integer> primes = new ArrayList();
        for (int i = 2; i <= initial; i++) {
            if (arePrimes[i]) {
                primes.add(i);
            }
        }
        return new Primes(initial, primes);
    }
}
