package ru.techlab.risks.rest.risksrestservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.techlab.risks.rest.risksrestservices.model.LoanQualityResult;
import ru.techlab.risks.rest.risksrestservices.repository.LoanQualityResultRepository;

import java.util.Collection;

/**
 * Created by dim777 on 05.10.17.
 */
@RestController
@RequestMapping("/loansquality")
public class LoanQualityResultController {
    @Autowired
    private LoanQualityResultRepository loanQualityResultRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<LoanQualityResult> getAll() {
        return loanQualityResultRepository.findAll();
    }
}
