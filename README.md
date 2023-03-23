# NatWest Technical Test
## Outline
Write a RESTful service which calculates and returns all the prime numbers up to and including a number provided.

**Example**

The REST call would look something like http://your.host.com/primes/10 and should return JSON content:
```json
{
  "Initial":  10,
  "Primes": [2,3,5,7]
}
```

## Usage
* Run the JAR / Run the RESTful service via the main method in RestServiceApplication.java
* The only currently existing call is of the format: http://your.host.com/primes/{number} to which the following can be appended:
  * "algorithm" **(as a query parameter)** - is set to "sieve" by default, referring to the Sieve of Eratosthenes method of calculating primes. If some other value is set, it performs a brute force algorithm to find the relevant primes. The algorithm chosen will be displayed in the build log.
  * "xml" **(as a path variable)** - ie, primes/10/xml. If used, the result will be returned in an XML format as opposed to JSON. It also supports the "algorithm" query parameter. 

### TODO:
* Consider supporting varying return content types such as XML based, that should be configurable using the requested media type.

## Requirements
* The project must be written in Java 8 or above.
* The project must use Maven OR Gradle to build, test and run.
* The project must have unit and integration tests.
* The project must be runnable in that the service should be hosted in a container e.g. Tomcat, Jetty, Spring Boot etc.
* You may use any frameworks or libraries for support e.g. Spring MVC, Apache CXF etc.
* The project must be accessible from Github.

## Extensions attempted
* Consider ways to improve overall performance e.g. caching results, concurrent algorithm
* Consider supporting multiple algorithms that can be switched based on optional parameters
