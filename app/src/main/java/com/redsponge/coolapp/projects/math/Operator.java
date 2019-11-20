package com.redsponge.coolapp.projects.math;

import java.util.Locale;

public class Operator {

    public static class Operators {
        public static final Operator ADD = new Operator("+", new OperationAdapter() {
            @Override
            public int apply(Operand a, Operand b) {
                return a.getVal() + b.getVal();
            }
        });

        public static final Operator SUB = new Operator("-", new OperationAdapter() {
            @Override
            public void prepare(Operand a, Operand b) {
                if(b.getVal() > a.getVal()) Operand.swap(a, b);
            }

            @Override
            public int apply(Operand a, Operand b) {
                return a.getVal() - b.getVal();
            }
        });

        public static final Operator MUL = new Operator("*", new OperationAdapter() {
            @Override
            public int apply(Operand a, Operand b) {
                return a.getVal() * b.getVal();
            }
        });

        public static final Operator DIV = new Operator("/", new OperationAdapter() {
            @Override
            public void prepare(Operand a, Operand b) {
                if(b.getVal() > a.getVal()) Operand.swap(a, b);
                a.setVal((a.getVal() / b.getVal()) * b.getVal());
            }

            @Override
            public int apply(Operand a, Operand b) {
                return a.getVal() / b.getVal();
            }
        });

        public static final Operator POWER = new Operator("^", new OperationAdapter() {
            @Override
            public int apply(Operand a, Operand b) {
                return (int) Math.pow(a.getVal(), b.getVal());
            }
        });
        
        public static final Operator[] ALL = {ADD, SUB, MUL, DIV, POWER};
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

    public int apply(Operand a, Operand b) {
        return operation.apply(a, b);
    }

    public void prepare(Operand a, Operand b) {
        operation.prepare(a, b);
    }

    public String getRepresentation(Operand a, Operand b) {
        return String.format(Locale.UK, "%s %s %s", a, sign, b);
    }

    public interface Operation {
        void prepare(Operand a, Operand b); // Prepares the randomly generated options. for example: when doing -, a must be bigger than b
        int apply(Operand a, Operand b); // Finds the solution
    }


    public static abstract class OperationAdapter implements Operation {
        @Override
        public void prepare(Operand a, Operand b) {}

        @Override
        public int apply(Operand a, Operand b) {
            return 0;
        }
    }

}
