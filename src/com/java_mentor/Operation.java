package com.java_mentor;

public enum Operation {

    //"+","-","*","/";
    ADD("+","\\+"), SUB("-", "-"), MULT("*","\\*"), DIV("/","/");

    CharSequence arithmeticSign;
    String regExpSplitter;

    Operation(CharSequence opsSign, String regExpSplitter) {
        this.arithmeticSign = opsSign;
        this.regExpSplitter = regExpSplitter;
    }

    static Operation byOpsSign(CharSequence opsSign){
        for (Operation ops: Operation.values()){
            if (ops.arithmeticSign == opsSign){
                return ops;
            }
        }
        return null;
    }

}
