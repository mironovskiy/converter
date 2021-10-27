import converter.Converter;
import converter.exception.ConverterException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class DataDrivenTest {

    @Parameterized.Parameters(name = "{index}: {0} - {1}")
    public static Collection<Object> data() {
        return Arrays.asList(new Object[][] {
                {"0", "ноль"},
                {"00000000", "ноль"},
                {"001000", "одна тысяча"},
                {"1", "один"},
                {"-1", "минус один"},
                {"-0", "ноль"},
                {"12", "двенадцать"},
                {"100", "сто"},
                {"118", "сто восемнадцать"},
                {"1000", "одна тысяча"},
                {"102000", "сто две тысячи"},
                {"102523", "сто две тысячи пятьсот двадцать три"},
                {"2000000", "два миллиона"},
                {"25100000", "двадцать пять миллионов сто тысяч"},
                {"101002000122100", "сто один триллион два миллиарда сто двадцать две тысячи сто"},
                {"-101002000122100", "минус сто один триллион два миллиарда сто двадцать две тысячи сто"}
        });
    }

    public String input;
    public String expected;

    public DataDrivenTest(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void testConvertNumberToText() throws ConverterException {
        Assert.assertEquals(expected, new Converter().convertNumberToText(input));
    }

}