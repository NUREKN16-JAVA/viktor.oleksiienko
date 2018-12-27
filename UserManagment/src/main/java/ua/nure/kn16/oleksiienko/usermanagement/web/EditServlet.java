package ua.nure.kn16.oleksiienko.usermanagement.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kn16.oleksiienko.usermanagement.User;
import ua.nure.kn16.oleksiienko.usermanagement.db.DAOFactory;
import ua.nure.kn16.oleksiienko.usermanagement.db.DatabaseException;

public class EditServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("okButton") != null) {
            doOk(req, resp);
        } else if (req.getParameter("cancelButton") != null) {
            doCancel(req, resp);
        } else {
            showPage();
        }
    }

    private void showPage() {
        // TODO Auto-generated method stub

    }

    private void doCancel(HttpServletRequest req, HttpServletResponse resp) {
        // TODO Auto-generated method stub

    }

    private void doOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUser(req);
        try {
            processUser(user);
        } catch (DatabaseException e) {
            e.printStackTrace();
            new ServletException(e);
        }
        req.getRequestDispatcher("/browse").forward(req, resp);

    }

    private void processUser(User user) throws DatabaseException {
        try {
            DAOFactory.getInstance().getUserDAO().update(user);
        } catch (ReflectiveOperationException e) {
            throw new DatabaseException(e.toString());
        }
    }

    private User getUser(HttpServletRequest req) {
        User user = new User();
        String idStr = req.getParameter("id");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String dateStr = req.getParameter("date");

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        LocalDate date = LocalDate.now();

        try {
            date = sdf.parse(dateStr).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        if(idStr != null) {
            user.setId(new Long(idStr));
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);

        user.setDateOfBirth(date);

        return user;
    }

}
