package practice.cryptoAPI.cryptoAPI.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import practice.cryptoAPI.cryptoAPI.model.Conversion;
import practice.cryptoAPI.cryptoAPI.service.CurrencyService;

@Controller
@RequestMapping(path = "/exchange")
public class ConversionController {
    private static final Logger logger = LoggerFactory.getLogger(ConversionController.class);

    @Autowired 
    private CurrencyService currencyService;

    @GetMapping
    public String convertCrypto(@RequestParam(required = true) String fsym, @RequestParam(required = true) String tsyms, Model model) {
        Conversion c = new Conversion();
        c.setFsym(fsym);
        c.setTsyms(tsyms);
        Optional<Conversion> optConverter = currencyService.convertCryptoRates(c);
        if (optConverter.isEmpty()) {
            model.addAttribute("conversion", new Conversion());
            return "exchange";
        }
        logger.info("<<<<<<<< " + c.getFsym() + ">>>>>>>>>>>>> " + c.getTsyms());
        model.addAttribute("conversion", optConverter.get());
        model.addAttribute("fsym", c.getFsym());
        model.addAttribute("country", c.getCountry());
        model.addAttribute("number", c.getNumber());

        return "exchange";
    }

    //Currently it's not returning any va   lues and i suspect it is because my fsym and tsyms are bigdecimals

    
}
