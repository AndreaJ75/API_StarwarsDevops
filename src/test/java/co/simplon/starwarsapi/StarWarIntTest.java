package co.simplon.starwarsapi;

import co.simplon.starwarsapi.model.planet.Planet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

    @ExtendWith(SpringExtension.class)
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    public class StarWarIntTest {

        @Autowired
        private TestRestTemplate restTemplate;

        @Test
        public void getPlanetList() {
            // When retrieving planets from /api/planets
            List<?> planets = this.restTemplate.getForObject("/api/planets", List.class);

            // Then a non null list should be returned
            assertThat(planets).isNotNull();
        }

        @Test
        public void getPlanet() {
            // When retrieving an existing planet by its id
            ResponseEntity<Planet> responseEntity = this.restTemplate.getForEntity("/api/planets/{planetId}", Planet.class, 1);
            Planet planet = responseEntity.getBody();

            // Then OK status code should be sent back and
            // the planet should be returned and should be filled with its attributes
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(planet.getName()).isEqualTo("Alderaan");
            assertThat(planet.getRotationPeriod()).isEqualTo(24);
        }

//        @Test
//        public void getCityMonuments() {
//            // When retrieving city monuments from /api/city/{cityName}/monument
//            List<?> monuments = this.restTemplate.getForObject("/api/city/{cityName}/monument", List.class, "paris");
//
//            // Then a non null list should be returned
//            assertThat(monuments).isNotNull();
//        }
//
//        @Test
//        public void getCityMonumentByName() {
//            // When retrieving an existing city monuments by its name
//            ResponseEntity<Monument> responseEntity = this.restTemplate
//                    .getForEntity("/api/city/{cityName}/monument/{monumentName}", Monument.class, "paris", "tour-eiffel");
//            Monument tourEiffel = responseEntity.getBody();
//
//            // Then OK status code should be sent back and
//            // the monument should be returned and should be filled with its attributes
//            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//            assertThat(tourEiffel.getName()).isEqualTo("Tour Eiffel");
//        }
//
//        @Test
//        public void addCity() {
//            // Given a new valid city
//            City toulouse = new City("Toulouse", 31);
//            HttpEntity<City> toulouseEntity = new HttpEntity<>(toulouse);
//
//            // When posting this city to /api/city
//            ResponseEntity<City> responseEntity = this.restTemplate.postForEntity("/api/city", toulouseEntity, City.class);
//            City createdToulouse = responseEntity.getBody();
//
//            // Then OK status code should be sent back and
//            // the created city should be returned and should be filled with its attributes
//            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//            assertThat(createdToulouse.getName()).isEqualTo("Toulouse");
//            assertThat(createdToulouse.getDptCode()).isEqualTo(31);
//        }
//
//        @Test
//        public void addMonumentToCity() {
//            // Given a new valid monument
//            Monument arc = new Monument("Arc de Triomphe", null);
//            HttpEntity<Monument> arcEntity = new HttpEntity<>(arc);
//
//            // When posting this monument to /api/city/{name}/monument
//            ResponseEntity<Monument> responseEntity = this.restTemplate.postForEntity("/api/city/{cityName}/monument",
//                    arcEntity, Monument.class, "paris");
//
//            // Then OK status code should be sent back and
//            // the created monument should be returned and should be filled with its
//            // attributes
//            Monument createdArc = responseEntity.getBody();
//            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//            assertThat(createdArc.getName()).isEqualTo("Arc de Triomphe");
//        }

    }


