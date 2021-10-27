package converter.periodConverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OnesPeriodConverter {

    private static String[] hundreds;
    private static String[] tens;
    private static String[] ones;

    static {
        try {
            hundreds = new String[10];
            tens = new String[8];
            ones = new String[20];

            File file = new File("hundreds.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            hundreds[0] = line;
            int count = 1;
            line = reader.readLine();
            while (line != null){
                hundreds[count] = line;
                line = reader.readLine();
                count++;
            }
            reader.close();

            file = new File("tens.txt");
            reader = new BufferedReader(new FileReader(file));
            count = 0;
            line = reader.readLine();
            while (line != null){
                tens[count] = line;
                line = reader.readLine();
                count++;
            }
            reader.close();

            file = new File("ones.txt");
            reader = new BufferedReader(new FileReader(file));
            line = "";
            ones[0] = line;
            count = 1;
            line = reader.readLine();
            while (line != null){
                ones[count] = line;
                line = reader.readLine();
                count++;
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String[] convertPeriodToText(short number, int numberOfPeriod){
        String[] result = new String[3];

        if (number != 0) {
            result[0] = hundreds[number/100];
            number = (short) (number % 100);

            if ((number / 10) >= 2){
                result[1] = tens[number/10 - 2];

                number = (short) (number % 10);
                result[2] = ones[number%10];
            } else {
                result[1] = "";
                result[2] = ones[number];
            }
        }

        return result;
    }
}
