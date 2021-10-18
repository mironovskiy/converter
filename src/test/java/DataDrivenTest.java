import org.junit.Assert;
import org.junit.jupiter.api.Test;


class DataDrivenTest {

    @Test
    void convertNumberToText() {
        Converter converter = new Converter();

        Assert.assertEquals("ноль", converter.convertNumberToText("0"));
        Assert.assertEquals("один", converter.convertNumberToText("1"));
        Assert.assertEquals("двенадцать", converter.convertNumberToText("12"));
        Assert.assertEquals("сто", converter.convertNumberToText("100"));
        Assert.assertEquals("сто восемнадцать", converter.convertNumberToText("118"));
        Assert.assertEquals("одна тысяча", converter.convertNumberToText("1000"));
        Assert.assertEquals("сто две тысячи", converter.convertNumberToText("102000"));
        Assert.assertEquals("сто две тысячи пятьсот двадцать три", converter.convertNumberToText("102523"));
        Assert.assertEquals("два миллиона", converter.convertNumberToText("2000000"));
        Assert.assertEquals("двадцать пять миллионов сто тысяч", converter.convertNumberToText("25100000"));
        Assert.assertEquals("сто один триллион два миллиарда сто двадцать две тысячи сто", converter.convertNumberToText("101002000122100"));
    }
}