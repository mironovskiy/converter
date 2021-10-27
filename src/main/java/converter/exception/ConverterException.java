package converter.exception;

public class ConverterException extends Exception {
    public ConverterException(){
        super();
    }

    public ConverterException (String message){
        super(message);
    }

    public ConverterException (Exception e){
        super(e);
    }

    public ConverterException (String message, Exception e){
        super(message);
    }
}
