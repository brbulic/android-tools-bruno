package hr.brbulic.services.web.interfaces;

/**
 * Created by IntelliJ IDEA.
 * User: brbulic
 * Date: 9/29/11
 * Time: 12:28 AM
 *
 * TODO: Write some class comments on this one :)
 */
public interface IWebResultEventArgs {

    String getResultString();
    Exception getError();
    Object getUserState();

}
