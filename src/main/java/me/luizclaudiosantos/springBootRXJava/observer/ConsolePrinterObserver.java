package me.luizclaudiosantos.springBootRXJava.observer;

import java.time.LocalDateTime;

import io.reactivex.observers.DefaultObserver;
import me.luizclaudiosantos.springBootRXJava.module.CoinBaseResponse;
import reactor.core.publisher.Mono;

public class ConsolePrinterObserver extends DefaultObserver {

    @Override
    public void onNext(Object t) {

        Mono<CoinBaseResponse> responseMono = (Mono<CoinBaseResponse>) t;

        responseMono.subscribe(
            coinBaseResponse -> {
                System.out.println("[" + LocalDateTime.now() + " "
                + coinBaseResponse.getData().getBase() 
                + " Buy Price: $ " + coinBaseResponse.getData().getAmount()
                + " " + coinBaseResponse.getData().getCurrency());
            }
        );

    }

    @Override
    public void onError(Throwable e) {
        System.out.println("e = " + e);

    }

    @Override
    public void onComplete() {
        System.out.println("Complete");
    }

}