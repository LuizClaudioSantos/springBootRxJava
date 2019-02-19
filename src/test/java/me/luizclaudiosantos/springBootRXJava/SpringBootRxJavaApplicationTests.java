package me.luizclaudiosantos.springBootRXJava;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import junit.framework.Assert;

import org.junit.Before;

import me.luizclaudiosantos.springBootRXJava.module.CoinBaseResponse;
import me.luizclaudiosantos.springBootRXJava.service.CoinBaseService;
import reactor.core.publisher.Mono;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SpringBootRxJavaApplicationTests {

	@Mock
	CoinBaseService coinBaseService;


	@Before
	public void setupMock() {

		CoinBaseResponse bitcoinResponse = new CoinBaseResponse();

		bitcoinResponse.setData(bitcoinResponse.new Data());
		bitcoinResponse.getData().setAmount("120.00");
		bitcoinResponse.getData().setBase("BTC-USD");
		bitcoinResponse.getData().setCurrency("??");

		Mono <CoinBaseResponse> bitcoinMono = Mono.just(bitcoinResponse);

		CoinBaseResponse etherResponse = new CoinBaseResponse();

		etherResponse.setData(etherResponse.new Data());
		etherResponse.getData().setAmount("80.00");
		etherResponse.getData().setBase("ETH-USD");
		etherResponse.getData().setCurrency("??");

		Mono <CoinBaseResponse> etherMono = Mono.just(etherResponse);
		
		when(coinBaseService.getCryptoPrice("BTC-USD")).thenReturn(bitcoinMono);	
		when(coinBaseService.getCryptoPrice("ETH-USD")).thenReturn(etherMono);

	}

	@Test
	public void testingMock() {
		//Actully there is not usage of the coinBaseService in the demo, so the aim of this test is just
		//setup the enviroment to the test.

		Assert.assertEquals("120.00", coinBaseService.getCryptoPrice("BTC-USD").block().getData().getAmount() );
		Assert.assertEquals("80.00", coinBaseService.getCryptoPrice("ETH-USD").block().getData().getAmount() );
	}

}

