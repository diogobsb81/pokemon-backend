package com.dixa.miuros.service;

import com.dixa.miuros.model.Pokemon;
import com.dixa.miuros.model.PokemonRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url= "https://pokeapi.co/api/v2/pokemon/" , name = "pokemon")
public interface PokemonService {

    @RequestMapping("/{id}")
    Pokemon getPokemonById(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/{name}", produces = "application/json")
    Pokemon getPokemonByName(@PathVariable("name") String name);

    @RequestMapping(method = RequestMethod.GET, value="?offset={offset}&limit={limit}", produces = "application/json")
    PokemonRequest getAllPokemons(@RequestParam("limit") Long limit, @RequestParam("offset") Long offset);
}
