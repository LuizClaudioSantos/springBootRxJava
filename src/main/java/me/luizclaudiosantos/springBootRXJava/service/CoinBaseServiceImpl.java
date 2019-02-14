package me.luizclaudiosantos.springBootRXJava.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import me.luizclaudiosantos.springBootRXJava.module.CoinBaseResponse;
import reactor.core.publisher.Mono;

@Service
public class CoinBaseServiceImpl implements CoinBaseService{

    private WebClient webClient;

    public CoinBaseServiceImpl(WebClient webClient) {
        this.webClient  = webClient;
    }

	@Override
	public Mono<CoinBaseResponse> getCryptoPrice(String priceName) {
        return webClient
            .get()
            .uri("https://api.coinbase.com/v2/prices/{cryptoName}/buy", priceName)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .flatMap(clientResponse -> clientResponse.bodyToMono(CoinBaseResponse.class));
	}

}