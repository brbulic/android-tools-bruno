package hr.brbulic.asserts;

/**
 * Created by IntelliJ IDEA.
 * User: Bruno
 * Date: 29.09.11.
 * Time: 11:33
 * To change this template use File | Settings | File Templates.
 */
public final class AssertUtils {

    private static final String NRE_MESSAGE = "Object must not be null!";
    private static final String OUT_OF_RANGE_MESSAGE = "Object must not be null!";


    private static AssertException fabricateThrowableFromReason(String message, String reason) {

        AssertException currentException;

        if (reason == null || reason.isEmpty())
            currentException = new AssertException(message);
        else
            currentException = new AssertException(message, reason);

        return currentException;
    }

    public static void notNull(Object testable, String reason) {

        if (testable == null)
            throw fabricateThrowableFromReason(NRE_MESSAGE, reason);
    }

    public static void notNull(Object testable) throws AssertException {
        notNull(testable, null);

    }

    public static void IsValueValid(Boolean predicateResult, String reason) {
        if (predicateResult)
            throw ValueOutOfRangeException.encapsulateBase(fabricateThrowableFromReason(OUT_OF_RANGE_MESSAGE, reason));
    }

    public static void IsValueValid(Boolean predicateResult) {
        IsValueValid(predicateResult, null);
    }
}
