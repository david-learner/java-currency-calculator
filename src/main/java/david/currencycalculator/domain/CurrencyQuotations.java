package david.currencycalculator.domain;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;

@Getter
public class CurrencyQuotations {

    private CurrencyCountryCode sourceCountryCode;
    private Map<CurrencyCountryCode, BigDecimal> quotations;
    private Set<CurrencyCountryCode> targetCountryCodes;

    private CurrencyQuotations(CurrencyCountryCode sourceCountryCode,
                               Map<CurrencyCountryCode, BigDecimal> quotations) {
        this.sourceCountryCode = sourceCountryCode;
        this.quotations = quotations;
        this.targetCountryCodes = this.quotations.keySet();
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
}
