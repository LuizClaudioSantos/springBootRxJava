package me.luizclaudiosantos.springBootRXJava.cmd;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import me.luizclaudiosantos.springBootRXJava.observer.ConsolePrinterObserver;
import me.luizclaudiosantos.springBootRXJava.service.CoinBaseService;

@Component
public class CMDLineUI implements CommandLineRunner {

    private CoinBaseService coinBaseService;

    public CMDLineUI(CoinBaseService coinBaseService) {
        this.coinBaseService = coinBaseService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Reactive Programming with String Boot and RXJava\n");

        Observable.interval(3000, TimeUnit.MILLISECONDS, Schedulers.io())
            .map(tick -> coinBaseService.getCryptoPrice("BTC-USD"))
            .subscribe(new ConsolePrinterObserver());
        
        Observable.interval(3000, TimeUnit.MILLISECONDS, Schedulers.io())
            .map(tick -> coinBaseService.getCryptoPrice("ETH-USD"))
            .subscribe(new ConsolePrinterObserver());
                

        coinBaseService
           .getCryptoPrice("BTC-USD")
           .subscribe( s -> System.out.println(s.getData().getAmount()));

    }

}