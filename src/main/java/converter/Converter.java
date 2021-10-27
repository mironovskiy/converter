package converter;

import converter.exception.ConverterException;
import converter.periodConverter.AfterThousandsPeriodConverter;
import converter.periodConverter.OnesPeriodConverter;
import converter.periodConverter.ThousandsPeriodConverter;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    private static final String REGEX = "\\d+";

    public String convertNumberToText(String number) throws ConverterException {
        if (number.equals("")){
            return "ноль";
        }

        List<String> resultList = new ArrayList<String>();
        boolean isNegative = false;

        if (number.charAt(0) == '-'){
            isNegative = true;
            number = number.substring(1);
        }

        if (!number.matches(REGEX)){
            throw new ConverterException("Invalid input data");
        }


        short currentPeriod;
        int count = 0;
        while (number != "") {

            if (number.length()<=3){
                currentPeriod = Short.parseShort(number);
            } else {
                currentPeriod = Short.parseShort(number.substring(number.length() - 3));
            }

            if (currentPeriod != 0) {
                OnesPeriodConverter periodConverter;
                switch (count){
                    case 0: periodConverter = new OnesPeriodConverter(); break;
                    case 1: periodConverter = new ThousandsPeriodConverter(); break;
                    default: periodConverter = new AfterThousandsPeriodConverter(); break;
                }

                String[] currentPeriodArr = periodConverter.convertPeriodToText(currentPeriod, count);

                for (int i = currentPeriodArr.length-1; i >= 0; i--) {
                    resultList.add(currentPeriodArr[i]);
                }
            }

            if (number.length()<=3){
                number = "";
            } else {
                number = number.substring(0, number.length() - 3);
            }

            count++;
        }

        String result = "";
        for (int i = resultList.size()-1; i >= 0; i--) {
            if (!resultList.get(i).equals("")){
                result += " " + resultList.get(i);
            }
        }

        if (resultList.size() == 0){
            return "ноль";
        }

        result = result.substring(1);
        if (isNegative){
            result = "минус " + result;
        }

        return result;
    }

}