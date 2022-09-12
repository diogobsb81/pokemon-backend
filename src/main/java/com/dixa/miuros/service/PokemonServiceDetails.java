package com.dixa.miuros.service;

import com.dixa.miuros.model.Pokemon;
import com.dixa.miuros.model.PokemonRequest;
import com.dixa.miuros.model.PokemonResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PokemonServiceDetails {
    @Autowired
    PokemonService service;

    public PokemonRequest getPokemons(Long limit, Long offset){
        PokemonRequest pokemonRequest = service.getAllPokemons(limit,offset);
        List<Pokemon> results = pokemonRequest.getResults().stream().
                map(p-> {
                    Pokemon pokemon = service.getPokemonByName(p.getName());
                    return pokemon;
                })
                .collect(Collectors.toList());

        pokemonRequest.setResults(results);
        return pokemonRequest;
    }
}
