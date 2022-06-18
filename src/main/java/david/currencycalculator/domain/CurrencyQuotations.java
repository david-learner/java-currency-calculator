package david.currencycalculator.domain;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CurrencyQuotations {

    private CurrencyCountryCode currencyCountryCode;
    private Map<CurrencyCountryCode, BigDecimal> quotations;

    private CurrencyQuotations(CurrencyCountryCode currencyCountryCode,
                               Map<CurrencyCountryCode, BigDecimal> quotations) {
        this.currencyCountryCode = currencyCountryCode;
        this.quotations = quotations;
    }

    public static CurrencyQuotations of(String source, Map<String, Object> quotes) {
        Map<CurrencyCountryCode, BigDecimal> quotations = new ConcurrentHashMap<>();
        quotes.forEach((quotationCode, rate) -> {
            quotationCode = quotationCodeNormalize(source, quotationCode);
            quotations.put(CurrencyCountryCode.valueOf(quotationCode),
                    BigDecimal.valueOf(Double.parseDouble(rate.toString())));
        });

        return new CurrencyQuotations(CurrencyCountryCode.valueOf(source), quotations);
    }

    private static String quotationCodeNormalize(String source, String quotationCode) {
        if (quotationCode.contains(source)) {
            return quotationCode.replace(source, "");
        }
        return quotationCode;
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
