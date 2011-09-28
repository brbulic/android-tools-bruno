package hr.brbulic.services.web.interfaces;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: brbulic
 * Date: 9/29/11
 * Time: 12:29 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IWebResultDelegate {

    void onWebServiceResult(UUID requestUuid, IWebResultEventArgs resultArgs);
}
