import converter.Converter;
import converter.exception.ConverterException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class DataDrivenTestException {

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Collection<Object> data() {
        return Arrays.asList(new Object[][] {
                {"-"},
                {"123__ddd"},
                {"  333"},
                {"3 "},
                {"ggg"},
                {"-34f"}
        });
    }

    public String input;

    public DataDrivenTestException(String input) {
        this.input = input;
    }

    @Test(expected = ConverterException.class)
    public void testConvertNumberToText() throws ConverterException {
        new Converter().convertNumberToText(input);
    }
}
