package ua.nure.kn16.oleksiienko.usermanagement.web;

import ua.nure.kn16.oleksiienko.usermanagement.User;
import ua.nure.kn16.oleksiienko.usermanagement.db.DAOFactory;
import ua.nure.kn16.oleksiienko.usermanagement.db.DatabaseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class BrowseServlet extends HttpServlet {

    public static void createServlet(Class browseServletClass) {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        browse(req, resp);
    }

    private void browse (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<User> users;
        try {
            users = DAOFactory.getInstance().getUserDAO().findAll();
            req.getSession().setAttribute("users", users);
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
        } catch (DatabaseException | ReflectiveOperationException e) {
            throw new ServletException(e);
        }

    }
}
