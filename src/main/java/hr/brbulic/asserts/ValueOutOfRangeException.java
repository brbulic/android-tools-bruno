package hr.brbulic.asserts;

/**
 * Created by IntelliJ IDEA.
 * User: Bruno
 * Date: 29.09.11.
 * Time: 11:37
 *
 * Exception thrown during runtime if an Assertion fails
 */
public class ValueOutOfRangeException extends AssertException {

    /**
	 * Some version UUID
	 */
	private static final long serialVersionUID = 8713437578384105015L;


	public ValueOutOfRangeException(String message) {
        super(message);
    }

    public ValueOutOfRangeException(String message, String reason) {
        super(message, reason);
    }


    /**
     * Create a ValueOutOfRangeException from the base {@link <AssertException>} class.
     * @param base Base exception
     * @return
     */
    public static ValueOutOfRangeException encapsulateBase(AssertException base) {
        return new ValueOutOfRangeException(base.getMessage(), base.getReason());
    }
}
