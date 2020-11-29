package top.jisy.docs.util;

import org.springframework.stereotype.Component;
import top.jisy.docs.constant.FieldValues;
import top.jisy.docs.pojo.User;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Context;

@Component
public class SessionUtils {

    /**
     * The client's HTTP request to an API service provided by context sensitive
     * injection
     */
    private HttpServletRequest request;

    /**
     * This constructor is only applied for context sensitive injection by JAX-RS
     * for the class to be proxyable by the container. Do not use this constructor.
     * Instead inject this utility into your bean with the {@code @Inject} annotation
     * like described above.
     */
    public SessionUtils() {
    }

    /**
     * Constructor used for injecting the user HTTP request into the session utility.
     * The context aware injection is automatically done by the proxy implementation
     * the container generates. The request parameter may not be {@code null} because
     * in that case a {@link NullPointerException} is thrown. Therefore this constructor
     * should not be used directly as the injection automatically cares about the correct
     * instantiation. Use this class by injecting this utility into your class as described
     * above.
     *
     * @param request the HTTP servlet request of the user containing a reference to the
     *                user session
     * @see Context
     */
    @Inject
    public SessionUtils(@NotNull @Context HttpServletRequest request) {
        this.request = requireNotNull(request);
    }

    /**
     * Creates a new session for the specified {@code user} if there is no active session
     * for the user yet. This is the case if the user is not authenticated at the
     * application or his original session expired. The new session is associated with
     * the user object of the current user and a boolean value whether the user is logged
     * in or not.
     *
     * @param user the user for which a new session will be created
     * @return true if a new session was created, false if the user already has an
     * active session.
     */
    public boolean createSession(User user) {
        if (isLoggedIn()) {
            return false;
        }

        HttpSession session = request.getSession(true);
        session.setAttribute(FieldValues.SESSION_USER, user);
        session.setAttribute(FieldValues.SESSION_IS_LOGGED_IN, true);

        return true;
    }

    /**
     * Retrieves the information whether the current user is logged in or not. This is
     * achieved by checking if an active session is being maintained for the user and
     * if so, whether the session is bind to the user object.
     *
     * @return true if the current user is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return request.getSession(false) != null && getUser() != null;
    }

    /**
     * Invalidates the session of the current user if there is an active session being
     * maintained. If the user is authenticated and has an active session the session
     * attributes are removed firstly and the session is destroyed and invalidated
     * secondly.
     *
     * @return true if the session was invalidated, false if the user is not authenticated
     * and thus has no active session.
     */
    public boolean invalidateSession() {
        if (!isLoggedIn()) {
            return false;
        }

        HttpSession session = request.getSession(false);
        session.removeAttribute(FieldValues.SESSION_USER);
        session.removeAttribute(FieldValues.SESSION_IS_LOGGED_IN);
        session.invalidate();

        return true;
    }

    /**
     * Retrieves the user object of the current user from the session. It is checked
     * if the user has an active session, and in that case the user object is loaded
     * from this session and returned. The method returns {@code null} if there is no
     * actively maintained session at the server or if the session is not bound to
     * any user object.
     *
     * @return the concrete user object loaded from the session or {@code null} if
     * the user has no active session or the session does not contain a user object.
     */
    public User getUser() {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }

        return (User) session.getAttribute(FieldValues.SESSION_USER);
    }

    /**
     * Checks if the applied HTTP servlet request is {@code null} or not. If the
     * request parameter is null a {@link NullPointerException} is thrown. Otherwise
     * the request is returned as is. This method should be invoked in a case where
     * a non-null parameter is required.
     *
     * @param request the HTTP servlet request to be checked against {@code null}
     * @return the request as is if it is not {@code null}, otherwise throws
     * {@link NullPointerException}.
     * @see java.util.Objects#requireNonNull(Object)
     */
    private HttpServletRequest requireNotNull(HttpServletRequest request) {
        if (request == null) {
            throw new NullPointerException("HTTP request is null");
        }

        return request;
    }
}
