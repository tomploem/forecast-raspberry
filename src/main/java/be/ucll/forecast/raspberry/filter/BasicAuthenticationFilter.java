package be.ucll.forecast.raspberry.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import org.glassfish.jersey.internal.util.Base64;

/**
 * Created by tompl on 12/6/2016.
 */

@Provider
public class BasicAuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";

    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
            .entity("You cannot access this resource").build();

    private static final Response ACCESS_APPROVED = Response.status(Response.Status.ACCEPTED)
            .entity("You cannot access this resource").build();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        //Get request headers
        final MultivaluedMap<String, String> headers = requestContext.getHeaders();

        //Fetch authorization header
        final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

        //If no authorization information present; block access
        if(authorization == null || authorization.isEmpty())
        {
            requestContext.abortWith(ACCESS_DENIED);
            return;
        }else {

            //Get encoded username and password
            final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

            //Decode username and password
            //Decode username and password
            String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));

            //Split username and password tokens
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();

            //Is user valid?
            if (!isUserAllowed(username, password)) {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }
        }
    }

        private boolean isUserAllowed(final String username, final String password) {

            boolean isAllowed = false;
            //String userRole = userMgr.getUserRole(username);

            if(username.equals("tom") && password.equals("ploem"))
            {
                return true;
            }

            return isAllowed;
        }

    }

