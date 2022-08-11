package practice.cryptoAPI.cryptoAPI.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import practice.cryptoAPI.cryptoAPI.model.Conversion;
import practice.cryptoAPI.cryptoAPI.service.CurrencyService;

//task 2: index.html to handle the countries and all
@Controller
public class IndexPageController {
    private static final Logger logger = LoggerFactory.getLogger(IndexPageController.class);

    @Autowired
    CurrencyService currencySvc;

    @GetMapping("/")
    public String showIndexPage(Model model) {
        Conversion c = new Conversion();
        lsOfCountryCodes(model);
        model.addAttribute("conversion", c);
        return "index";

    }

    private void lsOfCountryCodes(Model model) {
        Map<String, String> lsOfGeoCodes = currencySvc.getCountryCodeList();
        List<String> lsOfCountryCodes = Arrays.asList(lsOfGeoCodes.keySet().toArray(new String[0]));
        List<String> lsOfCountryCodeVals = Arrays.asList(lsOfGeoCodes.values().toArray(new String[0]));
        logger.info("Country code values>>>>>>> " + lsOfCountryCodeVals.toString());
        model.addAttribute("lsOfCountryCodes", lsOfCountryCodes);
        model.addAttribute("lsOfCountryCodeVals", lsOfCountryCodeVals);
        
    }

    
}
