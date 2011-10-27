package hr.brbulic.services.web.interfaces;

import java.net.URI;

/**
 * Created by IntelliJ IDEA.
 * User: Bruno
 * Date: 29.09.11.
 * Time: 13:38
 *
 * TODO: Write some class comments on this one :)
 */
public interface IHttpRequestBase {

    IHttpWebResponse getRequestStream(final String url);

    IHttpWebResponse getRequestStream(final URI uri);

    IHttpWebResponse getResultWritableStream(String url, byte [] writableData);
}