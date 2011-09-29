package hr.brbulic.asserts;

import android.util.AndroidRuntimeException;

/**
 * Created by IntelliJ IDEA.
 * User: Bruno
 * Date: 29.09.11.
 * Time: 11:33
 * Base class for Runtime assertion Exceptions. Inherits from {@link <AndroidRuntimeException>}, the unchecked exception in runtime.
 */
public class AssertException extends AndroidRuntimeException {

    private String reason = null;

    public String getReason() {
        return reason;
    }

    public AssertException(String message) {
        super(message);
    }

    public AssertException(String message, String reason) {
        this(message);
        this.reason = reason;
    }

}
