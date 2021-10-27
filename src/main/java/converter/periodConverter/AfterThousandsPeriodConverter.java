package converter.periodConverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AfterThousandsPeriodConverter extends OnesPeriodConverter{
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
        String[] result = new String[4];
        String[] onesArr = super.convertPeriodToText(number, numberOfPeriod);
        for (int i = 0; i < 3; i++) {
            result[i] = onesArr[i];
        }

        if ((number % 100 > 4) && (number % 100 < 21)){
            result[3] = periods[numberOfPeriod - 1] + "ов";
            return result;
        }

        switch (number % 10){
            case 1:
                result[3] = periods[numberOfPeriod - 1];
                break;
            case 2:
            case 3:
            case 4:
                result[3] = periods[numberOfPeriod - 1] + "а";
                break;
            default:
                result[3] = periods[numberOfPeriod - 1] + "ов";
        }

        return result;
    }
}
