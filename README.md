# Reactive programing RxJava

This is a simeple example using RxJava to fetch the ratio of Bitcoin and Etheriun from coinbase.

## Project highlights

There is one Observable "ConsoleBaseObserver", it implements io.reactivex.observers.DefaultObserver.

The most important part of it is method onNext:

```
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
```    

Then the observer is passed as parameter to the Observable: 

```

    Observable.interval(3000, TimeUnit.MILLISECONDS, Schedulers.io())
        .map(tick -> coinBaseService.getCryptoPrice("BTC-USD"))
        .subscribe(new ConsolePrinterObserver());
        
    Observable.interval(3000, TimeUnit.MILLISECONDS, Schedulers.io())
        .map(tick -> coinBaseService.getCryptoPrice("ETH-USD"))
        .subscribe(new ConsolePrinterObserver());

```
