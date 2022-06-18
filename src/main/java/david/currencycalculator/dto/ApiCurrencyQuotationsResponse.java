package david.currencycalculator.dto;

import david.currencycalculator.domain.CurrencyCountryCode;
import david.currencycalculator.domain.CurrencyQuotations;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiCurrencyQuotationsResponse {

    private CurrencyCountryCode sourceCountryCode;
    private Map<CurrencyCountryCode, BigDecimal> quotations;
    private Set<CurrencyCountryCode> targetCountryCodes;

    private ApiCurrencyQuotationsResponse(CurrencyCountryCode currencyCountryCode,
                                          Map<CurrencyCountryCode, BigDecimal> quotations,
                                          Set<CurrencyCountryCode> targetCurrencyCountryCodes) {
        this.sourceCountryCode = currencyCountryCode;
        this.quotations = quotations;
        this.targetCountryCodes = targetCurrencyCountryCodes;
    }

    public static ApiCurrencyQuotationsResponse of(CurrencyQuotations quotations) {
        return new ApiCurrencyQuotationsResponse(quotations.getSourceCountryCode(), quotations.getQuotations(),
                quotations.getTargetCountryCodes());
    }
}
