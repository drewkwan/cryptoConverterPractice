package practice.cryptoAPI.cryptoAPI.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class CryptoCurrencyCode {
    private String symbol;
    private static final Logger logger = LoggerFactory.getLogger(CryptoCurrencyCode.class);

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

public static Set<String> lsOfCryptoCodes(String json) {
    //CryptoCurrencyCode ccc = new CryptoCurrencyCode();
    InputStream is = new ByteArrayInputStream(json.getBytes());
    try(JsonReader jr = Json.createReader(is)) {
        JsonObject cryptoListJsonObject = jr.readObject();
        //logger.info("ccc list object >>>>>>> " + cryptoListJsonObject);
        JsonObject data = cryptoListJsonObject.getJsonObject("Data");
        //for data we're still gettting a key: value pair, not a string, so it still has to be a JsonObject
        Set<String> listOfCrypto = data.keySet();
        logger.info("Set>>>>>>>>> " + listOfCrypto.toString());
        return listOfCrypto;

        // Iterator<String> cryptoKeys = symbolSet.iterator();
        
        // //keySet will give a Set<String> so need that first, then create a List and then extract symbol out of it
        // ccc.symbols = new ArrayList<>(symbolSet);
        // ccc.symbol = ccc.symbols.get(0);
        // //String symbolString = cryptoListJsonObject.getString("name");
        // return ccc.symbol;
        
    }
        


}

}
