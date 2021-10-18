import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Converter {

    private final String[] hundreds = {"", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
    private final String[] tens = {"двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
    private final String[] units = {"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять",
            "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "деввятнадцать"};
    private String[] degrees;
    private List<String> resultList;

    {
        try {
            File file = new File("degrees.txt");
            if (!file.exists()){
                file.createNewFile();

                FileWriter writer = new FileWriter (file);
                writer.write("миллион\nмиллиард\nтриллион");
                writer.close();
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = "";
            int count = 0;
            while (line != null) {
                line = reader.readLine();
                count++;
            }

            degrees = new String[count];
            reader.close();
            reader = new BufferedReader(new FileReader(file));

            line = "";
            count = 0;
            while (line != null){
                line = reader.readLine();
                degrees[count] = line;
                count++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String convertNumberToText(String number){
        if (number.equals("0")){
            return "ноль";
        }

        resultList = new ArrayList<String>();

        Short currentClass;
        int count = 0;
        while (number != "") {

            if (number.length()<=3){
                currentClass = new Short(number);
            } else {
                currentClass = new Short(number.substring(number.length() - 3));
            }

            if (currentClass != 0) {
                String[] classArr = convertClassToString(currentClass);

                switch (count){
                    case 0:
                        addData(classArr);
                        break;
                    case 1:
                        convertThousandToString(classArr, currentClass);
                        break;
                    default:
                        convertDegreeToString(classArr, currentClass, degrees[count-2]);
                        break;
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

        return result.substring(1);
    }

    private void convertDegreeToString(String[] classArr, short number, String degree){
        if ((number % 100 > 4) && (number % 100 < 21)){
            resultList.add(degree + "ов");
            addData(classArr);
            return;
        }

        switch (number % 10){
            case 1:
                resultList.add(degree);
                break;
            case 2:
            case 3:
            case 4:
                resultList.add(degree + "а");
                break;
            default:
                resultList.add(degree + "ов");
        }
        addData(classArr);
    }

    private void convertThousandToString(String[] classArr, short number){
        if ((number % 100 > 4) && (number % 100 < 21)){
            resultList.add("тысяч");
            addData(classArr);
            return;
        }

        switch (number % 10){
            case 1:
                classArr[2] = "одна";
                resultList.add("тысяча");
                break;
            case 2:
                classArr[2] = "две";
                resultList.add("тысячи");
                break;
            case 3:
            case 4:
                resultList.add("тысячи");
                break;
            default:
                resultList.add("тысяч");
        }
        addData(classArr);
    }

    private void addData(String[] classArr){
        for (int i = classArr.length-1; i >= 0; i--) {
            resultList.add(classArr[i]);
        }
    }

    private String[] convertClassToString(short number){
        String[] result = new String[3];

        if (number != 0) {
            result[0] = hundreds[number/100];
            number = (short) (number % 100);

            if ((number / 10) >= 2){
                result[1] = tens[number/10 - 2];

                number = (short) (number % 10);
                result[2] = units[number%10];
            } else {
                result[1] = "";
                result[2] = units[number];
            }
        }

        return result;
    }
}