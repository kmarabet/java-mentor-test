package com.java_mentor;

public class RomanToNumber{

    public static String decimalToRoman(int number) {

        if (number <= 0 || number >= 4000) {
            throw new CalculatorRuntimeException("Roman Number values supported from 1 to 4000");
        }

        StringBuilder sb = new StringBuilder();
        int times = 0;
        String[] romans = new String[] { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M" };
        int[] ints = new int[] { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000 };
        for (int i = ints.length - 1; i >= 0; i--) {
            times = number / ints[i];
            number %= ints[i];
            while (times > 0) {
                sb.append(romans[i]);
                times--;
            }
        }
        return sb.toString();
    }

    public static int romanToDecimal(final String str) {
        int result = 0;

        for (int i=0; i < str.length(); i++) {
            final int s1 = RomanLiteralSymbol.getValueByLiteral(str.charAt(i));

            if (i+1 <str.length()) {
                final int s2 = RomanLiteralSymbol.getValueByLiteral(str.charAt(i+1));

                if (s1 >= s2){
                    result = result + s1;
                } else{
                    result = result + s2 - s1;
                    i++;
                }
            } else{
                result = result + s1;
                i++;
            }
        }
        return result;
    }

    public enum RomanLiteralSymbol {

        I(1), V(5), X(10), L(50), C(100), D(500), M(1000);

        int value;

        RomanLiteralSymbol(int value) {
            this.value = value;
        }

        static int getValueByLiteral(final char romanLiteralChar){

            for (RomanLiteralSymbol romanLiteral: RomanLiteralSymbol.values()){
                if (romanLiteral.toString().charAt(0) == romanLiteralChar){
                    return romanLiteral.value;
                }
            }
            throw new IllegalArgumentException("Not supported Roman literal symbol '" + romanLiteralChar + "'");
        }
    }

}
