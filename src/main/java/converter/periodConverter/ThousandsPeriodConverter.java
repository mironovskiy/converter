package converter.periodConverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ThousandsPeriodConverter extends OnesPeriodConverter {

    private static String[] periods;

    static {
        try {
            File file = new File("periods.txt");

            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = "";
            int count = 0;
            while (line != null) {
                line = reader.readLine();
                count++;
            }

            periods = new String[count];
            reader.close();
            reader = new BufferedReader(new FileReader(file));

            line = "";
            count = 0;
            while (line != null){
                line = reader.readLine();
                periods[count] = line;
                count++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] convertPeriodToText(short number, int numberOfPeriod) {
        String[] thousandArr = new String[4];
        String[] onesArr = super.convertPeriodToText(number, numberOfPeriod);
        for (int i = 0; i < 3; i++) {
            thousandArr[i] = onesArr[i];
        }

        if ((number % 100 > 4) && (number % 100 < 21)){
            thousandArr[3] = periods[0];
            return thousandArr;
        }

        switch (number % 10){
            case 1:
                thousandArr[2] = "одна";
                thousandArr[3] = periods[0] + "а";
                break;
            case 2:
                thousandArr[2] = "две";
                thousandArr[3] = periods[0] + "и";
                break;
            case 3:
            case 4:
                thousandArr[3] = periods[0] + "и";
                break;
            default:
                thousandArr[3] = periods[0];
        }

        return thousandArr;
    }
}
