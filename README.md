# NatWest Technical Test
## Outline
A RESTful service which calculates and returns all the prime numbers up to and including a number provided (the "initial").

**Example**

The basic REST call looks like http://your.host.com/primes/10 and by default returns JSON content:
```json
{
  "Initial":  10,
  "Primes": [2,3,5,7]
}
```

## Usage
* Run the JAR / Run the RESTful service via the main method in RestServiceApplication.java
* The only currently existing call is of the format: http://your.host.com/primes/{number} to which the following can be appended:
  * "algorithm" **(as a query parameter)** - is set to "sieve" by default, referring to the Sieve of Eratosthenes method of calculating primes. If some other value is set, it performs a brute force algorithm to find the relevant primes. For example, `/primes/10?algorithm=sieve`. The algorithm chosen will be displayed in the build log.
  * "xml" **(as a path variable)** - ie, `/primes/10/xml`. If used, the result will be returned in an XML format as opposed to JSON. It also supports the "algorithm" query parameter. 

### TODO:
* Consider supporting varying return content types such as XML based, that should be configurable using the requested media type.

## Base Requirements
* The project must be written in Java 8 or above. - Written in Java 17
* The project must use Maven OR Gradle to build, test and run. - Used Gradle
* The project must have unit and integration tests. - Present under the `src/test/java` directory
* The project must be runnable in that the service should be hosted in a container e.g. Tomcat, Jetty, Spring Boot etc. - using Spring Boot
* You may use any frameworks or libraries for support e.g. Spring MVC, Apache CXF etc.
* The project must be accessible from Github.

## Extensions attempted
* Consider ways to improve overall performance - implemented the caching of results
* Consider supporting multiple algorithms that can be switched based on optional parameters - supporting the brute force algorithm (time complexity of `O(n^3/2)`) as well as the Sieve of Eratosthenes (time complexity of `O(n*log((log n)))` )
* Consider supporting varying return content types such as XML based, that should be configurable using the requested media type. - Supporting JSON and XML return types
