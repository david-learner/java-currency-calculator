package david.currencycalculator.api;

import david.currencycalculator.domain.CurrencyQuotations;
import david.currencycalculator.dto.ApiCurrencyQuotationsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiCurrencyController {

    private final CurrencyQuotations currencyQuotations;

    @Autowired
    public ApiCurrencyController(CurrencyQuotations currencyQuotations) {
        this.currencyQuotations = currencyQuotations;
    }

    @GetMapping("/api/currencies")
    public ApiCurrencyQuotationsResponse serveCurrencyQuotations() {
        return ApiCurrencyQuotationsResponse.of(currencyQuotations);
    }
}
