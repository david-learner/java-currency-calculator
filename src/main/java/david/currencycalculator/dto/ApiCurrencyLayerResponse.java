package david.currencycalculator.dto;

import java.util.Map;

public class ApiCurrencyLayerResponse {

    private boolean success;
    private String source;
    private Map<String, Object> quotes;

    public boolean isSuccess() {
        return success;
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
}
