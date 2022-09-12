package com.dixa.miuros;

import com.dixa.miuros.model.PokemonRequest;
import com.dixa.miuros.service.PokemonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9091)
class MiurosApplicationTests {

	private final Long limit = 20L;
	private final Long offset = 0L;
	@Autowired
	PokemonService pokemonService;

	@Test
	public void getAllPokemonsTest() throws Exception {
		stubFor(get(urlEqualTo("/api/all"))
				.willReturn(aResponse()
						.withStatus(HttpStatus.OK.value())
						.withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)));
		PokemonRequest pokemon = pokemonService.getAllPokemons(limit,offset);

		assertThat(pokemon.getCount() == 1154);
	}
}
