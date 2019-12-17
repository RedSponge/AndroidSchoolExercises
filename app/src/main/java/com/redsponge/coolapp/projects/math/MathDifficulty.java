package com.redsponge.coolapp.projects.math;
import static com.redsponge.coolapp.projects.math.Operator.Operators.*;

public enum MathDifficulty {
    ADD_N_SUB("Level 1 - Addition/Subtraction", 1, 10, ADD, SUB),
    ADD_N_SUB_EXPERT("Level 2 - BIG Addition/Subtraction", 10, 100, ADD, SUB),
    ADD_SUB_MUL("Level 3 - Add/Sub/Multiply", 20, ADD, SUB, MUL),
    MUL_DIV("Level 4 - Multiplication and Division", 20, MUL, DIV),
    MUL_POW("Level 5 - The shorter multiplication", 10, MUL, POWER),
    MUL_DIV_POW("Level 6 - Hard", 20, MUL, DIV, POWER),
    ALL_IN_BIG_NUMBERS("Level 7 - Big numbers: all operations", 10, 30, ADD, SUB, MUL, DIV, POWER, SQRT),

    HARD("Level 728349 - BIG HARD.", 30, 99, MUL, DIV, POWER, SQRT),
    ALL_DEBUG("Level -1 - Debug", 100, ALL)
    ;
    private Operator[] operators;
    private String name;

    private int minNumber;
    private int maxNumber;

    MathDifficulty(String name, int maxNumber, Operator... operators) {
        this.name = name;
        this.minNumber = 2;
        this.maxNumber = maxNumber;
        this.operators = operators;
    }

    MathDifficulty(String name, int minNumber, int maxNumber, Operator... operators) {
        this.name = name;
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.operators = operators;
    }

    public Operator[] getOperators() {
        return operators;
    }

    public int getMinNumber() {
        return minNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    @Override
    public String toString() {
        return name;
    }
}
