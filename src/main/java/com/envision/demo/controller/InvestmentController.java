package com.envision.demo.controller;

import com.envision.demo.model.Investment;
import com.envision.demo.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvestmentController {
    @Autowired
    private InvestmentService investmentService;

    @GetMapping("/investments")
    public List<Investment> findAll() {
        return investmentService.findAll();
    }

    @GetMapping("/investment")
    public Investment findById(long id) {
        return investmentService.findById(id);
    }

    @DeleteMapping("/investment/delete")
    public int deleteById(long id) {
        return investmentService.deleteById(id);
    }

    @PostMapping("/investment/create")
    public int insert(@RequestBody Investment investment) {
        return investmentService.insert(investment);
    }

    @PutMapping("/investment/update")
    public int update(@RequestBody Investment investment) {
        return investmentService.update(investment);
    }

    @GetMapping("/sayHello")
    public String welcome() {
        return "Welcome, Happy Investing!";
    }

    @GetMapping("/sayGoodbye")
    public String sayGoodbye(String name) {
        return "Come back for more returns!";
    }
}
