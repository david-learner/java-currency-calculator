package david.currencycalculator.domain;

import java.math.BigDecimal;
import java.util.Map;

public class CurrencyQuotations {

    private CurrencyCountryCode currencyCountryCode;
    private Map<CurrencyCountryCode, BigDecimal> quotations;

    public CurrencyQuotations(CurrencyCountryCode currencyCountryCode,
                              Map<CurrencyCountryCode, BigDecimal> quotations) {
        this.currencyCountryCode = currencyCountryCode;
        this.quotations = quotations;
    }

    public BigDecimal get(String termsName) {
        validateExistence(termsName);
        return this.quotations.get(termsName);
    }

    public void validateExistence(String termsName) {
        if (!this.quotations.containsKey(CurrencyCountryCode.valueOf(termsName))) {
            throw new IllegalArgumentException(termsName + "은 존재하지 않는 terms 입니다.");
        }
    }
}
