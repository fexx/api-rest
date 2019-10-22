package br.com.fernandoevangelista.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.fernandoevangelista.api.terceiro.ApiTerceiroClient;


@RunWith(MockitoJUnitRunner.Silent.class)
public class ApiTerceirosClientTest {
	
	@InjectMocks
	private ApiTerceiroClient apiTerceiroClient;
	
	@Mock
	private RestTemplate restTemplate;
	
	@Test
    public void api_terceiros() {
		when(restTemplate.getForEntity("http://localhost:8080/terceiros/api/1", Object.class))
		.thenReturn(new ResponseEntity<>("Lamborghini", HttpStatus.OK));
		Object apiTerceiros = apiTerceiroClient.getApiTerceiros(1l);
		assertEquals("Lamborghini", apiTerceiros);
    }
	
}
