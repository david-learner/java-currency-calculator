package david.currencycalculator.api;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import david.currencycalculator.dto.ApiCurrencyLayerResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CurrencyApiControllerTest {

    @Test
    @DisplayName("api layer 서비스에 한국, 일본, 필리핀 환율 정보를 요청한다")
    void Request_korea_japan_philippines_currency_data_from_api_layer_service() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("apikey", "5m6uwmrQMERAOCMlHeai8jvOgR10x8zr");
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        String url = "https://api.apilayer.com/currency_data/live?source=USD&currencies=KRW%2CJPY%2CPHP";

        ResponseEntity<ApiCurrencyLayerResponse> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
                ApiCurrencyLayerResponse.class);

        ApiCurrencyLayerResponse apiCurrencyLayerResponse = response.getBody();
        assertThat(apiCurrencyLayerResponse.isSuccess()).isTrue();
        assertThat(apiCurrencyLayerResponse.hasQuote("USDKRW")).isTrue();
        assertThat(apiCurrencyLayerResponse.hasQuote("USDJPY")).isTrue();
        assertThat(apiCurrencyLayerResponse.hasQuote("USDPHP")).isTrue();
    }
}
