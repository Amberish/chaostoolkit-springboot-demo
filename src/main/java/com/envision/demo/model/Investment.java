package com.envision.demo.model;

public class Investment {

    private Long id;
    private String name;
    private String ticker;

    public Investment() {
        super();
    }

    public Investment(Long id, String name, String ticker) {
        super();
        this.id = id;
        this.name = name;
        this.ticker = ticker;
    }

    public Investment(String name, String ticker) {
        super();
        this.name = name;
        this.ticker = ticker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public String toString() {
        return "Investment [id=" + id + ", name=" + name + ", ticker=" + ticker + "]";
    }
}
