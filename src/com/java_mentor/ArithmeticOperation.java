package com.java_mentor;

public enum ArithmeticOperation {
    ADD("+","\\+"), SUB("-", "-"), MULT("*","\\*"), DIV("/","/");

    CharSequence sign;
    String regExpSplitter;

    ArithmeticOperation(final CharSequence opsSign, final String regExpSplitter) {
        this.sign = opsSign;
        this.regExpSplitter = regExpSplitter;
    }

    static ArithmeticOperation byOpsSign(final CharSequence opsSign){

        for (ArithmeticOperation ops: ArithmeticOperation.values()){
            if (ops.sign.equals(opsSign)){
                return ops;
            }
        }
        throw new IllegalArgumentException("Unexpected Arithmetic Operation '" + opsSign + "'");
    }

}
