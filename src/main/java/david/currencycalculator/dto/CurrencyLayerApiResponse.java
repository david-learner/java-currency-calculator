package david.currencycalculator.dto;

import david.currencycalculator.domain.CurrencyCountryCode;
import david.currencycalculator.domain.CurrencyQuotations;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CurrencyLayerApiResponse {

    private boolean success;
    private long timestamp;
    private String source;
    private Map<String, Object> quotes;

    public boolean isSuccess() {
        return success;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getSource() {
        return source;
    }

    public Map<String, Object> getQuotes() {
        return quotes;
    }

    public boolean hasQuote(String quote) {
        return quotes.containsKey(quote);
    }

    public CurrencyQuotations toCurrencyQuotations() {
        Map<CurrencyCountryCode, BigDecimal> quotations = new HashMap<>();
        this.quotes.forEach((quotationName, rate) -> {
            quotations.put(CurrencyCountryCode.valueOf(quotationName),
                    BigDecimal.valueOf(Double.parseDouble(rate.toString())));
        });
        return new CurrencyQuotations(CurrencyCountryCode.valueOf(source), quotations);
    }
}
