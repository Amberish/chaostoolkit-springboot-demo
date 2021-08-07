package com.envision.demo.service;

import com.envision.demo.model.Investment;
import com.envision.demo.repository.InvestmentJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentService {
    @Autowired
    private InvestmentJdbcRepository investmentJdbcRepository;

    public List<Investment> findAll() {
        return investmentJdbcRepository.findAll();
    }

    public Investment findById(long id) {
        return investmentJdbcRepository.findById(id);
    }

    public int deleteById(long id) {
        return investmentJdbcRepository.deleteById(id);
    }

    public int insert(Investment investment) {
        return investmentJdbcRepository.insert(investment);
    }

    public int update(Investment investment) {
        return investmentJdbcRepository.update(investment);
    }
}
