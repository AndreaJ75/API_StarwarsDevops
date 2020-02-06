package co.simplon.starwarsapi.controller;

import co.simplon.starwarsapi.model.planet.Planet;
import co.simplon.starwarsapi.repository.PlanetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
public class PlanetcontrolTest {

    // Mockmvc permet de faire les appel URL
    @Autowired
    MockMvc mockvc;

    @MockBean
    PlanetRepository planetrepository;



    @Test
    public void getPlanets() throws Exception{
        when(planetrepository.findAll()).thenReturn(new ArrayList<Planet>());

        this.mockvc.perform(get("/api/planets")).andExpect(status().isOk());
    }

 /*   @Test
    public void getPlanetbyId() {
        when(planetcontrol.getPlanet(1L)).thenReturn(new Planet planet(1));

        this.mockvc.perform("/api/planets/{planet" getPlanetbyId());
    }*/

    @Test
    public void getPlanetbyId() throws Exception  {
        when(planetrepository.findById(2000L)).thenReturn(Optional.of(new Planet ("Alderaan")));

        this.mockvc.perform(get("/api/planets/2000")).andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Alderaan"));
             // .andExpect(jsonPath("rotationPeriod").value(24));
    }

/*    @Test
    public void getPlanetbyIdNotFound() throws Exception  {
        when(planetrepository.findById(2000L)).thenReturn(Optional.of(new Planet ("Alderaan")));

        this.mockvc.perform(get("/api/planets/2000")).andExpect(status().isNotFound())
                .andExpect(jsonPath("name").value("Coucou"));
        // .andExpect(jsonPath("rotationPeriod").value(24));
    }*/

    @Test
    public void createPlanet() throws Exception {
        when(planetrepository.save(any())).thenReturn(new Planet ("Casiope"));

        this.mockvc.perform(post("/api/planets").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Casiope\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Casiope"));
    }

/*    @Test
    public void updatePlanet() throws Exception {
        when(planetrepository.save(andy())).thenReturn()
    }*/


}

