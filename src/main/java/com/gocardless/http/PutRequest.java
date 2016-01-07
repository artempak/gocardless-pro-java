package com.gocardless.http;

import java.io.Reader;

/**
 * Base class for PUT requests.
 *
 * @param <T> the type of the item returned by this request.
 */
public abstract class PutRequest<T> extends ApiRequest<T> {
    protected PutRequest(HttpClient httpClient) {
        super(httpClient);
    }

    /**
     * Executes this request.
     *
     * Returns the response entity.
     *
     * @throws com.gocardless.GoCardlessException
     */
    public T execute() {
        return getHttpClient().executeWithRetries(this);
    }

    /**
     * Executes this request.
     *
     * Returns a {@link com.gocardless.http.ApiResponse} that wraps the
     * response entity.
     *
     * @throws com.gocardless.GoCardlessException
     */
    public ApiResponse<T> executeWrapped() {
        return getHttpClient().executeWrapped(this);
    }

    @Override
    protected T parseResponse(Reader stream, ResponseParser responseParser) {
        return responseParser.parseSingle(stream, getEnvelope(), getResponseClass());
    }

    @Override
    protected final String getMethod() {
        return "PUT";
    }

    protected abstract Class<T> getResponseClass();
}