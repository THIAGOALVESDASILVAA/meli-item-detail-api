package com.meli.itemdetail.core.domain.model;

import java.util.List;

public class PaymentMethods {
    private final List<PaymentMethod> methods;

    public PaymentMethods(List<PaymentMethod> methods) {
        this.methods = methods;
    }

    public List<PaymentMethod> getMethods() {
        return methods;
    }

    public static class PaymentMethod {
        private final String id;
        private final String name;
        private final List<String> subtypes;

        public PaymentMethod(String id, String name, List<String> subtypes) {
            this.id = id;
            this.name = name;
            this.subtypes = subtypes;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public List<String> getSubtypes() { return subtypes; }
    }
}