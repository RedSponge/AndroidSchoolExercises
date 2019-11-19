package com.redsponge.coolapp.projects.math;

import java.util.Locale;

public class Operator {

    public static class Operators {
        public static final Operator ADD = new Operator("+", (a, b) -> a + b);
        public static final Operator SUB = new Operator("-", (a, b) -> a - b);
        public static final Operator MUL = new Operator("*", (a, b) -> a * b);
        public static final Operator DIV = new Operator("/", (a, b) -> a / b);
        
        public static final Operator[] ALL = {ADD, SUB, MUL, DIV};
    }

    private String sign;
    private Operation operation;

    public Operator(String sign, Operation operation) {
        this.sign = sign;
        this.operation = operation;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public int apply(int a, int b) {
        return operation.apply(a, b);
    }

    public String getRepresentation(int a, int b) {
        return String.format(Locale.UK, "%d %s %d", a, sign, b);
    }

    @FunctionalInterface
    public interface Operation {
        int apply(int a, int b);
    }

}
