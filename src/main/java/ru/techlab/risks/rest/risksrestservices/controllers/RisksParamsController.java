package ru.techlab.risks.rest.risksrestservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.techlab.risks.rest.risksrestservices.model.LoanQualityCategory;
import ru.techlab.risks.rest.risksrestservices.model.LoanQualityCategoryMatrix;
import ru.techlab.risks.rest.risksrestservices.model.LoanServCoeff;
import ru.techlab.risks.rest.risksrestservices.repository.LoanQualityCategoryMatrixRepository;
import ru.techlab.risks.rest.risksrestservices.repository.LoanQualityCategoryRepository;
import ru.techlab.risks.rest.risksrestservices.repository.LoanServCoeffRepository;

/**
 * Created by rb052775 on 05.10.2017.
 */
@RestController
@RequestMapping("/risksparams")
public class RisksParamsController {
    @Autowired
    private LoanQualityCategoryRepository loanQualityCategoryRepository;
    @Autowired
    private LoanServCoeffRepository loanServCoeffRepository;

    @Autowired
    private LoanQualityCategoryMatrixRepository loanQualityCategoryMatrixRepository;

    @RequestMapping(value = "/loanqualitycategories", method = RequestMethod.GET)
    public Iterable<LoanQualityCategory> getAllLoanQualityCategories(){
        return loanQualityCategoryRepository.findAll();
    }

    @RequestMapping(value = "/loanservcoeffs", method = RequestMethod.GET)
    public Iterable<LoanServCoeff> getLoanServCoeffs(){
        return loanServCoeffRepository.findAll();
    }

    @RequestMapping(value = "/loanservcoeffsmatrix", method = RequestMethod.GET)
    public Iterable<LoanQualityCategoryMatrix> getLoanQualityCategoryMatrix(){
        return loanQualityCategoryMatrixRepository.findAll();
    }
}
