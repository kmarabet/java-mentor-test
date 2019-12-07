package com.java_mentor;

import java.util.Scanner;

public class Main {

    //final static CharSequence[] opsList = {"+","-","*","/"};
    static final int NUMBERS_IN_ONE_OPS = 2;

    public static void main(String[] args) {
        // write your code here
        Scanner scan = new Scanner(System.in);
        String continueResponse;
        String operation;

        Number calculationResult;
        System.out.println("*** ПРОГРАММА КАЛЬКУЛЯТОР ***");

        do {
            System.out.println("Введите операцию: (ex. 1 + 3 or VI / III)");
            operation = scan.nextLine();
            calculationResult = parseOperationString(operation);
            if (calculationResult.getNumberFormat() == NumberFormatType.ARAB){
                System.out.println("Результат: " + calculationResult.getValue());
            }else if (calculationResult.getNumberFormat() == NumberFormatType.ROMAN){
                System.out.println("Результат: " + RomanToNumber.decimalToRoman(calculationResult.getValue()));
            }
            System.out.println("Продолжить? (y/n)");
            continueResponse = scan.next();
            scan.nextLine();
        } while(continueResponse.equals("y") || continueResponse.equals("Y"));
    }

    public static Number parseOperationString(String inputString){

        Number result = null; boolean arithmeticSignFound = false;

        ArithmeticOperation[] opsList = ArithmeticOperation.values();

        for (ArithmeticOperation ops: opsList){

            if (inputString.contains(ops.sign)) {
                arithmeticSignFound = true;
                String[] rawOperands = inputString.split(ops.regExpSplitter);

                if (rawOperands.length != NUMBERS_IN_ONE_OPS) {
                    throw new RuntimeException("Only 2 numbers with one arithmetic sign between them (ex. 1 + 3) should be provided");
                }

                final Number operand1 = getNumberWithFormat(rawOperands[0]);
                final Number operand2 = getNumberWithFormat(rawOperands[1]);

                if (operand1.getNumberFormat() != operand2.getNumberFormat()) {
                    throw new RuntimeException("Numbers should be in the same format");
                }

                final ArithmeticOperation arithmeticOperation = ArithmeticOperation.byOpsSign(ops.sign);

                final int opsResult = calculate(operand1.getValue(), operand2.getValue(), arithmeticOperation);

                result = new Number(opsResult, operand1.getNumberFormat());
                break;
            }
        }
        if (!arithmeticSignFound){
            throw new RuntimeException("Arithmetic sign (+,-,* or /) should be provided");
        }
        return result;
    }

    private static int calculate(int operand1, int operand2, ArithmeticOperation ops) {

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

    private static Number getNumberWithFormat(String rawNumber) {

        int numberValue;
        Number result;
        try {
            numberValue = Integer.valueOf(rawNumber.trim());
            if (numberValue > 10 || numberValue < 0) {
                throw new RuntimeException("Number value should be from 1 to 10 included.");
            }
            result = new Number(numberValue, NumberFormatType.ARAB);
        } catch (NumberFormatException e1) {
            try {
                numberValue = RomanToNumber.romanToDecimal(rawNumber.trim());
            }catch (Exception e2){
                throw new RuntimeException("Incorrect Number Format");
            }
            result =  new Number(numberValue, NumberFormatType.ROMAN);
        }
        return result;
    }

}
