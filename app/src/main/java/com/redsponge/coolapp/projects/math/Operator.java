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
                a.setVal((a.getVal() * b.getVal()));
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

            @Override
            public void prepare(Operand a, Operand b) {
                if(b.getVal() > 3) b.setVal(3);
            }
        });

        public static final Operator SQRT = new Operator("√", new OperationAdapter() {
            @Override
            public void prepare(Operand a, Operand b) {
                a.setVal(2);
                b.setVal(b.getVal() * b.getVal());
            }

            @Override
            public int apply(Operand a, Operand b) {
                return (int) Math.sqrt(b.getVal());
            }

            @Override
            public String getRepresentation(Operator operation, Operand a, Operand b) {
                return operation.getSign() + b.getVal();
            }
        });

        public static final Operator FACT = new Operator("!", new OperationAdapter() {
            @Override
            public void prepare(Operand a, Operand b) {
                a.setVal(a.getVal() / 10);
            }

            @Override
            public int apply(Operand a, Operand b) {
                int f = 1;
                for (int i = 2; i <= a.getVal(); i++) {
                    f *= i;
                }
                return f;
            }

            @Override
            public String getRepresentation(Operator operation, Operand a, Operand b) {
                return a.getVal() + operation.getSign();
            }
        });
        
        public static final Operator[] ALL = {ADD, SUB, MUL, DIV, POWER, SQRT, FACT};
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
        return operation.getRepresentation(this, a, b);
    }

    public interface Operation {
        void prepare(Operand a, Operand b); // Prepares the randomly generated options. for example: when doing -, a must be bigger than b
        int apply(Operand a, Operand b); // Finds the solution
        String getRepresentation(Operator operation, Operand a, Operand b);
    }


    public static abstract class OperationAdapter implements Operation {
        @Override
        public void prepare(Operand a, Operand b) {}

        @Override
        public int apply(Operand a, Operand b) {
            return 0;
        }

        public String getRepresentation(Operator operation, Operand a, Operand b) {
            return String.format(Locale.UK, "%s %s %s", a, operation.getSign(), b);
        };
    }

}
