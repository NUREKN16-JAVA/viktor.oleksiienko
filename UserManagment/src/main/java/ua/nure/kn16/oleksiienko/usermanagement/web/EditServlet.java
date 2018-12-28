package ua.nure.kn16.oleksiienko.usermanagement.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

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
            showPage(req, resp);
        }
    }

    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/edit.jsp").forward(req, resp);
    }

    protected void doCancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/browse").forward(req, resp);
    }

    protected void doOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;
        try {
            user = getUser(req);
        } catch (Exception e1) {
            req.setAttribute("error", e1.getMessage());
            showPage(req, resp);
            return;
        }

        try {
            processUser(user);
        } catch (DatabaseException e) {
            e.printStackTrace();
            new ServletException(e);
        }

        req.getRequestDispatcher("/browse").forward(req, resp);
    }

    protected void processUser(User user) throws DatabaseException {
        try {
            DAOFactory.getInstance().getUserDAO().update(user);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    private User getUser(HttpServletRequest req) throws Exception {
        User user = new User();
        String idStr = req.getParameter("id");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String dateStr = req.getParameter("date");

        if(dateStr == null) {
            throw new Exception("Date is empty");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        LocalDate date;

        try {
            date = sdf.parse(dateStr).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e1) {
            throw new Exception("Date format is incorrect");
        }

        if(firstName == null) {
            throw new Exception("First name is empty");
        }

        if(lastName == null) {
            throw new Exception("Last name is empty");
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
