package ru.techlab.risks.rest.risksrestservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.techlab.risks.rest.risksrestservices.model.BaseConfig;
import ru.techlab.risks.rest.risksrestservices.repository.ConfigRepository;
import ru.xegex.risks.libs.ex.config.ConfigNotFoundEx;

/**
 * Created by rb052775 on 05.10.2017.
 */
@RestController
@RequestMapping("/config")
@CrossOrigin(value = "http://localhost:8222")
public class ConfigController {
    @Autowired
    private ConfigRepository configRepository;

    @Value("${app.config.id}")
    private Integer id;

    @RequestMapping(method = RequestMethod.GET)
    public BaseConfig getBaseConfig() throws ConfigNotFoundEx {
        BaseConfig config = configRepository
                .findFirstById(id)
                .orElseThrow(() -> new ConfigNotFoundEx("Could not find endOfDay")
                );
        return config;
    }
}
