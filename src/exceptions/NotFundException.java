
package exceptions;
public class NotFundException extends RuntimeException {

    public NotFundException(String message ) {
        System.out.println("Saldo insuficiente:" + message);
    }


}
