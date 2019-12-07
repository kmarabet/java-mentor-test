package com.java_mentor;

public enum ArithmeticOperation {

    //"+","-","*","/";
    ADD("+","\\+"), SUB("-", "-"), MULT("*","\\*"), DIV("/","/");

    CharSequence sign;
    String regExpSplitter;

    ArithmeticOperation(CharSequence opsSign, String regExpSplitter) {
        this.sign = opsSign;
        this.regExpSplitter = regExpSplitter;
    }

    static ArithmeticOperation byOpsSign(CharSequence opsSign){
        for (ArithmeticOperation ops: ArithmeticOperation.values()){
            if (ops.sign == opsSign){
                return ops;
            }
        }
        return null;
    }

}
