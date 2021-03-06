/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.sip.credentiallist;

import com.twilio.base.Updater;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class CredentialUpdater extends Updater<Credential> {
    private String pathAccountSid;
    private final String pathCredentialListSid;
    private final String pathSid;
    private String password;

    /**
     * Construct a new CredentialUpdater.
     * 
     * @param pathCredentialListSid The credential_list_sid
     * @param pathSid The sid
     */
    public CredentialUpdater(final String pathCredentialListSid, 
                             final String pathSid) {
        this.pathCredentialListSid = pathCredentialListSid;
        this.pathSid = pathSid;
    }

    /**
     * Construct a new CredentialUpdater.
     * 
     * @param pathAccountSid The account_sid
     * @param pathCredentialListSid The credential_list_sid
     * @param pathSid The sid
     */
    public CredentialUpdater(final String pathAccountSid, 
                             final String pathCredentialListSid, 
                             final String pathSid) {
        this.pathAccountSid = pathAccountSid;
        this.pathCredentialListSid = pathCredentialListSid;
        this.pathSid = pathSid;
    }

    /**
     * The password.
     * 
     * @param password The password
     * @return this
     */
    public CredentialUpdater setPassword(final String password) {
        this.password = password;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated Credential
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Credential update(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.POST,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/SIP/CredentialLists/" + this.pathCredentialListSid + "/Credentials/" + this.pathSid + ".json",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Credential update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }

        return Credential.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (password != null) {
            request.addPostParam("Password", password);
        }
    }
}