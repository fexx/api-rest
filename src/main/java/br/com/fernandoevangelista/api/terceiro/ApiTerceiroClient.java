package br.com.fernandoevangelista.api.terceiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiTerceiroClient {

	@Autowired(required=true)
	private RestTemplate restTemplate;

	public Object getApiTerceiros(Long id) {
		ResponseEntity<Object> resp = restTemplate.getForEntity("http://localhost:8080/terceiros/api/" + id, Object.class);
		return resp.getStatusCode() == HttpStatus.OK ? resp.getBody() : null;
	}

}
