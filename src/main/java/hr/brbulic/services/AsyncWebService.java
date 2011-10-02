package hr.brbulic.services;

import android.util.AndroidRuntimeException;
import android.util.Log;
import hr.brbulic.concurrency.Dispatcher;
import hr.brbulic.services.web.HttpRequestType;
import hr.brbulic.services.web.WebRequestActions;
import hr.brbulic.services.web.interfaces.*;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by IntelliJ IDEA.
 * User: Bruno
 * Date: 01.10.11.
 * Time: 17:10
 * <p/>
 * This class is the reference implementation of the IWebService that hanldles Asynchronous requests.
 */
public class AsyncWebService implements IWebService {


	private static final String TAG = "AsyncWebService";


	private final Queue<IWebServiceRequestData> requestDataQueue = new ConcurrentLinkedQueue<IWebServiceRequestData>();

	private final List<IWebServiceRequestData> cancellableRequests = new ArrayList<IWebServiceRequestData>();

	private final List<IWebServiceRequestData> runningServices = new ArrayList<IWebServiceRequestData>();

	private final Object objectLock = new Object();

	private final IHttpRequestInitiators coreProcessor;

	private volatile Boolean isRunning;

	private final Thread thread;


	private final int MAX_CONCURRENT_REQUESTS = 3;

	private AsyncWebService(IHttpRequestInitiators coreProcessor) {
		this.coreProcessor = coreProcessor;
		isRunning = true;
		thread = new Thread(new WebExecutor());
		thread.start();
	}

	private static IWebService _myInstance;

	public static IWebService getInstance() {
		if (_myInstance == null) {
			_myInstance = new AsyncWebService(new WebRequestActions());
		}

		return _myInstance;
	}


	@Override
	public UUID initiateRequest(HttpRequestType requestType, String uri, Map<String, String> parameters, Object userData, IWebResultDelegate callback) {

		final UUID uuid = UUID.randomUUID();

		final IWebServiceRequestData data = new WebRequestDataInternal(requestType, uri, parameters, userData, callback, uuid);

		synchronized (objectLock) {
			requestDataQueue.add(data);
			objectLock.notify();
		}

		return uuid;
	}

	@Override
	public Boolean isRequestRunning(UUID request) {
		return false;
	}

	@Override
	public Boolean cancelRequest(UUID request) {
		return false;
	}

	@Override
	public void StopService() {
		synchronized (objectLock) {
			isRunning = Boolean.FALSE;
			objectLock.notify();
		}
	}


	private final class WebExecutor implements Runnable {

		private final Queue<IWebServiceRequestData> requestDataQueue_internal = new ConcurrentLinkedQueue<IWebServiceRequestData>();
		private final Queue<IWebResultEventArgs> pendingFinalized = new ConcurrentLinkedQueue<IWebResultEventArgs>();


		@Override
		public void run() {
			Thread.currentThread().setName(String.format("%1$s_%2$s", TAG, this.hashCode()));

			while (isRunning) {

				synchronized (objectLock) {

					if (requestDataQueue.size() == 0 && pendingFinalized.size() == 0 && requestDataQueue_internal.size() == 0) {
						try {
							objectLock.wait();
						} catch (InterruptedException e) {
							Log.i(TAG, "New requests found... waking up!");
						}
					}

					if (!isRunning)
						break;


					while (requestDataQueue.size() != 0)
						requestDataQueue_internal.add(requestDataQueue.remove());
				}


				for (int i = 0; (i < requestDataQueue_internal.size()) && (i < MAX_CONCURRENT_REQUESTS); i++) {
					final IWebServiceRequestData requestData = requestDataQueue_internal.remove();

					switch (requestData.getRequestType()) {

						case DEFAULT:
						case REQUEST_TYPE_GET:
							Dispatcher.BeginThreadPoolInvoke(new Runnable() {
								@Override
								public void run() {
									IWebResultEventArgs result = coreProcessor.beginRequestGet(
											requestData.getUri(),
											requestData.getParameters(),
											requestData);
									synchronized (objectLock) {
										pendingFinalized.add(result);
										objectLock.notify();
									}

								}
							});
							Thread.yield();
							break;
						case REQUEST_TYPE_POST:
							throw new AndroidRuntimeException("Request type out of range!");
					}
				}

				for (int i = 0; (i < pendingFinalized.size()) && (i < MAX_CONCURRENT_REQUESTS); i++) {

					IWebResultEventArgs resultEventArgs;

					synchronized (objectLock) {
						resultEventArgs = pendingFinalized.remove();
						objectLock.notify();
					}

					final IWebServiceRequestData requestData = (IWebServiceRequestData) resultEventArgs.getUserState();

					final IWebResultEventArgs finalResult = new WebRequestActions.WebResultMessengerWithBuilder(resultEventArgs.getResultString(), resultEventArgs.getError(), requestData.getUserData());

					requestData.getResultDelegate().onWebServiceResult(requestData.getRequestUuid(), finalResult);

				}


				Thread.yield();


			}


		}
	}


	private final class WebRequestDataInternal implements IWebServiceRequestData {

		private final String requestUri;

		private final Map<String, String> params;

		private final IWebResultDelegate resultDelegate;

		private final Object userObject;

		private final UUID uuid;

		private final HttpRequestType requestType;

		public WebRequestDataInternal(HttpRequestType requestType, String requestUri, Map<String, String> params, Object userObject, IWebResultDelegate resultDelegate, UUID uuid) {
			this.requestType = requestType;
			this.requestUri = requestUri;
			this.params = params;
			this.userObject = userObject;
			this.resultDelegate = resultDelegate;
			this.uuid = uuid;
		}


		@Override
		public String getUri() {
			return requestUri;
		}

		@Override
		public Map<String, String> getParameters() {
			return params;
		}

		@Override
		public Object getUserData() {
			return userObject;
		}

		@Override
		public IWebResultDelegate getResultDelegate() {
			return resultDelegate;
		}

		@Override
		public UUID getRequestUuid() {
			return uuid;
		}

		@Override
		public HttpRequestType getRequestType() {
			return requestType;
		}
	}


}
