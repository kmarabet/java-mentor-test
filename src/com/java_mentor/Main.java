package com.java_mentor;

import java.util.Scanner;

public class Main {

    //final static CharSequence[] opsList = {"+","-","*","/"};
    final static int NUMBERS_IN_ONE_OPS = 2;

    public static void main(String[] args) {
	// write your code here
        Scanner scan = new Scanner(System.in);
        String continueResponse;
        String operation;

        Number calculationResult;

        do {
            System.out.println("Введите операцию: (ex. 1 + 3)");
            operation = scan.next();
            calculationResult = parseOperationString(operation);
            System.out.println("Результат: " + calculationResult.getValue());
            System.out.println("Продолжить? (y/n)");
            continueResponse = scan.next();
        } while(continueResponse.equals("y") || continueResponse.equals("Y"));

    }

    public static Number parseOperationString(String inputString){

        Number result = null;

        Operation[] opsList = Operation.values();

        for (Operation ops: opsList){

            if (inputString.contains(ops.opsSign)) {

                String[] rawOperands = inputString.split(ops.regExpSplitter);

                if (rawOperands.length != NUMBERS_IN_ONE_OPS ) throw new RuntimeException("");

                Number operand1 = checkNumberFormat(rawOperands[0]);
                Number operand2 = checkNumberFormat(rawOperands[1]);

                if (operand1.getNumberFormat() != operand2.getNumberFormat()) {
                    throw new RuntimeException("Numbers should be in same format");
                }

                Operation operation = Operation.byOpsSign(ops.opsSign);

                int opsResult = calculate(operand1.getValue(), operand2.getValue(), operation);

                result = new Number(opsResult, operand1.getNumberFormat());
                break;
            }
        }
        return result;
    }

    private static int calculate(int operand1, int operand2, Operation ops) {

        Integer result = null;

        switch (ops){
            case ADD:
                result = operand1 + operand2;
                break;
            case SUB:
                result = operand1 - operand2;
                break;
            case MULT:
                result = operand1 * operand2;
                break;
            case DIV:
                result = operand1 / operand2;
                break;
        }
        return result;
    }

    private static Number checkNumberFormat(String number) {

        int numberValue;
        try {
            numberValue = Integer.valueOf(number.trim());
            return new Number(numberValue, NumberFormatType.ARAB);
        } catch (NumberFormatException e1) {
            try {
                numberValue = RomanToNumber.romanToDecimal(number.trim());
            }catch (Exception e2){
                throw new RuntimeException("Incorrect Number Format");
            }
            return new Number(numberValue, NumberFormatType.ROMAN);
        }
    }

}
