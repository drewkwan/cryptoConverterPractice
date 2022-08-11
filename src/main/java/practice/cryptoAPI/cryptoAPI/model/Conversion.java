package practice.cryptoAPI.cryptoAPI.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Conversion {
    private static final Logger logger = LoggerFactory.getLogger(Conversion.class);

    //private boolean tryConversion;
    private String fsym; //doesnt need to match
    private String tsyms; //can be like toCurrency 
    //The two required params are important to instantiate because i need to construct the url
    //json structures help me to exploit the key:value pairing rather than processign everything as a string
    private String country;
    private BigDecimal number;


    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public BigDecimal getNumber() {
        return number;
    }
    public void setNumber(BigDecimal number) {
        this.number = number;
    }
    // public boolean isTryConversion() {
    //     return tryConversion;
    // }
    // public void setTryConversion(boolean tryConversion) {
    //     this.tryConversion = tryConversion;
    //}
    public String getFsym() {
        return fsym;
    }
    public void setFsym(String fsym) {
        this.fsym = fsym;
    }
    public String getTsyms() {
        return tsyms;
    }
    public void setTsyms(String tsyms) {
        this.tsyms = tsyms;
    }

    public static Conversion createJson(String json) throws IOException {
        logger.info("conversion create json" + json);
        Conversion c = new Conversion();

        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            //o.getJsonObject is returning a null. 
            //logger.info(">>>>>@@@@ " + o.getJsonObject("AED"));
            logger.info(">>>>!!! " + o.keySet());
            logger.info(">>>>>values ----------- " + o.values());
            //I dont know how to get the try conversion co sits boolean
            //So these two don't do anything.
            Set<String> countrySet = o.keySet();
            List<String> countryList = new ArrayList<>(countrySet);
            c.country = countryList.get(0);

            JsonNumber jsNum = o.getJsonNumber(c.country);
            c.number = jsNum.bigDecimalValue();


            // c.tsyms = o.getJsonString("tsyms").getString();
            logger.info("c is >>>>>>>> " + c.number);

            // BigDecimal number = o.getJsonNumber(o.values()).bigDecimalValue();            
        }
        return c;
    }

}
