package david.currencycalculator.config;

import david.currencycalculator.domain.CurrencyQuotations;
import david.currencycalculator.service.CurrencyQuotationsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    @Autowired
    public CurrencyQuotations currencyQuotations(CurrencyQuotationsLoader currencyQuotationsLoader) {
        return currencyQuotationsLoader.load().toCurrencyQuotations();
    }
}
