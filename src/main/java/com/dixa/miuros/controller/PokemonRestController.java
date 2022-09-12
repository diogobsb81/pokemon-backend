package com.dixa.miuros.controller;

import com.dixa.miuros.model.PokemonRequest;
import com.dixa.miuros.service.PokemonServiceDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200/")
public class PokemonRestController {
    private static final Logger logger = LoggerFactory.getLogger(PokemonRestController.class);

    @Autowired
    PokemonServiceDetails pokemonServiceDetails;

    @RequestMapping(value="all",method=RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PokemonRequest> getAllPokemons(@RequestParam(value="limit") Long limit,
                                                        @RequestParam(value="offset") Long offset) {
        ResponseEntity<PokemonRequest> response = null;
        PokemonRequest pokemon = new PokemonRequest();
        try {
            pokemon = pokemonServiceDetails.getPokemons(limit,offset);
            response = new ResponseEntity<>(pokemon, HttpStatus.OK);
        }catch (Exception e){
            logger.error("System Error:",e.getMessage());
            response = new ResponseEntity<>(pokemon, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
