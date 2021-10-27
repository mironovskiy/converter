import converter.Converter;
import converter.exception.ConverterException;

public class Main {
    private static final String number = "-101002000122100";

    public static void main(String[] args) throws ConverterException {
        System.out.println(new Converter().convertNumberToText(number));
    }
}
