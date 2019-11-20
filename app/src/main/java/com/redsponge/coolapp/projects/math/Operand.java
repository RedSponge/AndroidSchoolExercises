package com.redsponge.coolapp.projects.math;

public class Operand {

    private int val;

    public Operand(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "" + val;
    }

    public static void swap(Operand a, Operand b) {
        int t = a.getVal();
        a.setVal(b.getVal());
        b.setVal(t);
    }
}
