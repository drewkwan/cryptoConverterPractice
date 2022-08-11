package practice.cryptoAPI.cryptoAPI.service;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import practice.cryptoAPI.cryptoAPI.model.Conversion;
import practice.cryptoAPI.cryptoAPI.model.CurrencyCode;

@Service
public class CurrencyService {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyService.class);

    //task 1:convery the list of countries
    public static final String LS_Currency = "http://openexchangerates.org/api/currencies.json";

    public Map<String, String> getCountryCodeList() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp =template.getForEntity(LS_Currency, String.class);
        Map<String, String> lsOfGeoCodes = CurrencyCode.lsOfCountryCodes(resp.getBody());
        return lsOfGeoCodes;

    }

    //task 3: make the api call
    private static String cryptoURL = "https://min-api.cryptocompare.com/data/price";

    public Optional<Conversion> convertCryptoRates(Conversion c) {
        String apiKey = "14e47fc28750bf863a0b09054cd3685fd5de8c8d01c2e7cbbc60b6ab544f99ac";
        //comment out or app prop
        String converterUrl = UriComponentsBuilder.fromUriString(cryptoURL)
                            .queryParam("fsym", c.getFsym())
                            .queryParam("tsyms", c.getTsyms())
                            .toUriString();
        logger.info("Converter URI >>>>>> " + converterUrl);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("api_key", apiKey);
            HttpEntity request = new HttpEntity(headers);
            resp = template.exchange(converterUrl, HttpMethod.GET, request, String.class, 1);
            //check the Json response
            logger.info(resp.getBody());
            //handle the error 
            Conversion conv = Conversion.createJson(resp.getBody());
            return Optional.of(conv);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();

    }


}

//crypto rates converted, but the createJson (resp.getbody()) is retuning a null. 