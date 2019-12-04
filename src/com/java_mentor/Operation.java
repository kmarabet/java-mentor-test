package com.java_mentor;

public enum Operation {

    //"+","-","*","/";
    ADD("+","\\+"), SUB("-", "-"), MULT("*","\\*"), DIV("/","/");

    CharSequence opsSign;
    String regExpSplitter;

    Operation(CharSequence opsSign, String regExpSplitter) {
        this.opsSign = opsSign;
        this.regExpSplitter = regExpSplitter;
    }

    static Operation byOpsSign(CharSequence opsSign){
        for (Operation ops: Operation.values()){
            if (ops.opsSign == opsSign){
                return ops;
            }
        }
        return null;
    }

}
