package be.ucll.forecast.raspberry.controller;

import be.ucll.forecast.raspberry.domain.User;
import be.ucll.forecast.raspberry.util.KeyGenerator;
import be.ucll.forecast.raspberry.util.PasswordUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

/**
 * Created by tompl on 12/20/2016.
 */

@Path("/users")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
public class Auth {

    @Inject
    private KeyGenerator keyGenerator;

    @PersistenceContext
    private EntityManager em;

    @Context
    private UriInfo uriInfo;

    @POST
    @Path("/login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("login") String login,
                                     @FormParam("password") String password) {

        System.out.println("DOES HE AUTHENTICATE?");

        try {

            // Authenticate the user using the credentials provided
            authenticate(login, password);

            // Issue a token for the user
            String token = issueToken(login);

            // Return the token on the response
            return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();

        } catch (Exception e) {
            System.out.println(e.getMessage()+"\n\n\n\n\n");
            return Response.status(UNAUTHORIZED).build();
        }
    }

    private String issueToken(String login) {
        Key key = keyGenerator.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(login)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return jwtToken;
    }

    private void authenticate(String login, String password) throws Exception {
        TypedQuery<User> query = em.createNamedQuery(User.FIND_BY_LOGIN_PASSWORD, User.class);
        query.setParameter("username", login);
        query.setParameter("password", PasswordUtils.digestPassword(password));
        User user = query.getSingleResult();

        if (user == null)
            throw new SecurityException("Invalid user/password");
    }

    @POST
    public Response create(User user) {
        em.persist(user);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(user.getUsername()).build()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") String id) {
        User user = em.find(User.class, id);

        if (user == null)
            return Response.status(NOT_FOUND).build();

        return Response.ok(user).build();
    }

    @GET
    public Response findAllUsers() {
        TypedQuery<User> query = em.createNamedQuery(User.FIND_ALL, User.class);
        List<User> allUsers = query.getResultList();

        if (allUsers == null)
            return Response.status(NOT_FOUND).build();

        return Response.ok(allUsers).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") String id) {
        em.remove(em.getReference(User.class, id));
        return Response.noContent().build();
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}