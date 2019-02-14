package me.luizclaudiosantos.springBootRXJava.service;

import me.luizclaudiosantos.springBootRXJava.module.CoinBaseResponse;
import reactor.core.publisher.Mono;

public interface CoinBaseService {

    Mono<CoinBaseResponse> getCryptoPrice(String priceName);

}