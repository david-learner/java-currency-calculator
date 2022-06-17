package david.currencycalculator.service;

import david.currencycalculator.dto.CurrencyLayerApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyQuotationsLoader {

    private final static RestTemplate restTemplate = new RestTemplate();
    private final static String API_KEY_HEADER_NAME = "apikey";
    @Value("${currency.layer.apikey}")
    private String apiKeyHeaderValue;
    @Value("${currency.layer.currency_quotation_list_request_url}")
    private String currencyQuotationListRequestUrl;

    public CurrencyLayerApiResponse load() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(API_KEY_HEADER_NAME, apiKeyHeaderValue);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        return restTemplate.exchange(currencyQuotationListRequestUrl, HttpMethod.GET, httpEntity,
                CurrencyLayerApiResponse.class).getBody();
    }
}
