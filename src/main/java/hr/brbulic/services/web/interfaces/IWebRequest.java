package hr.brbulic.services.web.interfaces;

import hr.brbulic.services.web.HttpRequestType;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Bruno
 * Date: 29.09.11.
 * Time: 13:38
 * To change this template use File | Settings | File Templates.
 */
public interface IWebRequest {

    IHttpWebResponse getRequestStream(final String url);

    IHttpWebResponse getResultWritableStream(String url, byte [] writableData);
}