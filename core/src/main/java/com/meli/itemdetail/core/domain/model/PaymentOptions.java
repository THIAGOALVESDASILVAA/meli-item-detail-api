package com.meli.itemdetail.core.domain.model;

import java.util.List;
import java.util.Map;

public class PaymentOptions {
    private Integer maxInstallments;
    private Integer interestFreeInstallments;
    private List<String> creditCards;
    private List<String> debitCards;
    private List<String> cashMethods;
    private Map<String, BankCondition> conditionsByBank;

    public PaymentOptions() {}

    public PaymentOptions(Integer maxInstallments, Integer interestFreeInstallments, List<String> creditCards,
                          List<String> debitCards, List<String> cashMethods, Map<String, BankCondition> conditionsByBank) {
        this.maxInstallments = maxInstallments;
        this.interestFreeInstallments = interestFreeInstallments;
        this.creditCards = creditCards;
        this.debitCards = debitCards;
        this.cashMethods = cashMethods;
        this.conditionsByBank = conditionsByBank;
    }

    public Integer getMaxInstallments() { return maxInstallments; }
    public void setMaxInstallments(Integer maxInstallments) { this.maxInstallments = maxInstallments; }

    public Integer getInterestFreeInstallments() { return interestFreeInstallments; }
    public void setInterestFreeInstallments(Integer interestFreeInstallments) { this.interestFreeInstallments = interestFreeInstallments; }

    public List<String> getCreditCards() { return creditCards; }
    public void setCreditCards(List<String> creditCards) { this.creditCards = creditCards; }

    public List<String> getDebitCards() { return debitCards; }
    public void setDebitCards(List<String> debitCards) { this.debitCards = debitCards; }

    public List<String> getCashMethods() { return cashMethods; }
    public void setCashMethods(List<String> cashMethods) { this.cashMethods = cashMethods; }

    public Map<String, BankCondition> getConditionsByBank() { return conditionsByBank; }
    public void setConditionsByBank(Map<String, BankCondition> conditionsByBank) { this.conditionsByBank = conditionsByBank; }

    public static class BankCondition {
        private Integer interestFreeInstallments;
        private String description;

        public BankCondition() {}

        public BankCondition(Integer interestFreeInstallments, String description) {
            this.interestFreeInstallments = interestFreeInstallments;
            this.description = description;
        }

        public Integer getInterestFreeInstallments() { return interestFreeInstallments; }
        public void setInterestFreeInstallments(Integer interestFreeInstallments) { this.interestFreeInstallments = interestFreeInstallments; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}