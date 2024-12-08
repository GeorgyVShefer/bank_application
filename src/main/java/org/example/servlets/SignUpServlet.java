package org.example.servlets;

import org.example.exception.EmptyFieldException;
import org.example.exception.UserIsAlreadyExistsException;
import org.example.service.AccountService;
import org.example.service.impl.MyAccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    private AccountService service;

    @Override
    public void init() throws ServletException {
        service = new MyAccountServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/signup.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            service.saveAccount(req.getParameter("email"),
                    req.getParameter("name"),
                    req.getParameter("password"));
        resp.sendRedirect("/login");
        } catch (UserIsAlreadyExistsException | EmptyFieldException e) {
            doGet(req,resp);
        }
    }
}
