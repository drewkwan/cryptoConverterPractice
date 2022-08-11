package practice.cryptoAPI.cryptoAPI.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;


public class CurrencyCode {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyCode.class);

    public static Map<String, String> currencyCodeList = new LinkedHashMap<>();

    public static Map<String, String> getCurrencyCodeList() {
        return currencyCodeList;
    }

    public static void setCurrencyCodeList(Map<String, String> currencyCodeList) {
        CurrencyCode.currencyCodeList = currencyCodeList;
    }

    //create a list of country codes method
    public static Map<String, String> lsOfCountryCodes(String json) {
        InputStream is = new ByteArrayInputStream(json.getBytes());
        try (JsonReader jr = Json.createReader(is)) {
            JsonObject currenciesJO = jr.readObject();
            //Sorted set will give in alphabetical order
            SortedSet<String> sortedKeys = new TreeSet<>(currenciesJO.keySet());

            logger.info("sorted set >>>>>> " + sortedKeys.toString());
            Iterator<String> key = sortedKeys.iterator();

            while (key.hasNext()) {
                String geoCode = key.next();
                currencyCodeList.put(currenciesJO.get(geoCode).toString(), geoCode);
            }
            
        }
        return currencyCodeList;

    }
}
