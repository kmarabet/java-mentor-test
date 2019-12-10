package com.java_mentor;

import java.util.Scanner;

public class Main {

    static final int NUMBERS_IN_ONE_OPS = 2;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String continueResponse;
        String operation;

        NumberWithFormat calculationResult;
        System.out.println("***************************** ПРОГРАММА КАЛЬКУЛЯТОР *****************************");
        System.out.println("*** Работает с арабскими или римскими числами (числа от 1 до 10 включительно) ***");
        System.out.println("*********************************************************************************");

        do {
            System.out.println("Введите операцию: (ex. 1 + 3 or VI / III)");
            operation = scan.nextLine();
            calculationResult = parseArithmeticOperation(operation);
            if (calculationResult.getNumberFormat() == NumberFormatType.ARAB) {
                System.out.println("Результат: " + calculationResult.getValue());
            } else if (calculationResult.getNumberFormat() == NumberFormatType.ROMAN) {
                if (calculationResult.getValue() <= 0 ){
                    throw new CalculatorRuntimeException("Roman numerals do not support 0 and negative numbers");
                }
                System.out.println("Результат: " + RomanToNumber.decimalToRoman(calculationResult.getValue()));
            }
            System.out.println("Продолжить? (y/n)");
            continueResponse = scan.next();
            scan.nextLine();
        } while (!continueResponse.equals("n") && !continueResponse.equals("N"));
    }

    public static NumberWithFormat parseArithmeticOperation(final String inputString) {

        String[] rawOperands = inputString.split("[-+/\\*]");

        if (rawOperands.length != NUMBERS_IN_ONE_OPS) {
            throw new CalculatorRuntimeException("The arithmetic expression should consists from 2 numbers (from 1 to 10 value) with one operator (ex. 2 + 10)");
        }

        final NumberWithFormat operand1 = getNumberWithFormat(rawOperands[0]);
        validateNumberValue(operand1);
        final NumberWithFormat operand2 = getNumberWithFormat(rawOperands[1], operand1.getNumberFormat());
        validateNumberValue(operand2);

        ArithmeticOperation[] opsList = ArithmeticOperation.values();

        ArithmeticOperation arithmeticOperation;
        int opsResult ;
        NumberWithFormat result = null;

        for (ArithmeticOperation ops: opsList) {
            if (inputString.contains(ops.sign)){
                arithmeticOperation = ArithmeticOperation.byOpsSign(ops.sign);
                opsResult = calculate(arithmeticOperation, operand1.getValue(), operand2.getValue());
                result = new NumberWithFormat(opsResult, operand1.getNumberFormat());
                break;
            }
        }
        return result;
    }

    private static void validateNumberValue(final NumberWithFormat number) {
        if (number.getValue() > 10 || number.getValue() < 0) {
            if (number.getNumberFormat() == NumberFormatType.ROMAN)
                throw new CalculatorRuntimeException("Number value should be from I to X included.");
            else
                throw new CalculatorRuntimeException("Number value should be from 1 to 10 included.");
        }
    }

    private static int calculate(final ArithmeticOperation ops, final int operand1, final int operand2) {

        Integer result = null;

        switch (ops) {
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
            default:
                throw new CalculatorRuntimeException("Not supported operation sign: (+, -, *, /) should be provided");
        }
        return result;
    }

    private static NumberWithFormat getNumberWithFormat(final String rawNumber) {

        int numberValue;
        NumberWithFormat result;
        try {
            numberValue = Integer.parseInt(rawNumber.trim());
            result = new NumberWithFormat(numberValue, NumberFormatType.ARAB);
        } catch (NumberFormatException e1) {
            numberValue = RomanToNumber.romanToDecimal(rawNumber.trim());
            result = new NumberWithFormat(numberValue, NumberFormatType.ROMAN);
        }
        return result;
    }

    private static NumberWithFormat getNumberWithFormat(final String rawNumber, final NumberFormatType requiredNumberFormatType) {

        NumberWithFormat result = getNumberWithFormat(rawNumber);

        if (requiredNumberFormatType != result.getNumberFormat()) {
            throw new CalculatorRuntimeException("Numbers should be in the same format - Arabic(Decimal) or Roman");
        }
        return result;
    }

}
