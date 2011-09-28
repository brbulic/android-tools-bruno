package hr.brbulic.services.web.interfaces;

/**
 * Created by IntelliJ IDEA.
 * User: brbulic
 * Date: 9/29/11
 * Time: 12:28 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IWebResultEventArgs {

    String getResultString();
    Exception getError();
    Object getUserState();

}
