package org.example.servlets;


import org.example.exception.EmptyFieldException;
import org.example.exception.UserIsAlreadyExistsException;
import org.example.model.Account;
import org.example.repository.AccountRepository;
import org.example.repository.impl.InMemoryAccountRepositoryImpl;
import org.example.service.AccountService;
import org.example.service.impl.MyAccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    private AccountService service;

    @Override
    public void init() throws ServletException {
        service = new MyAccountServiceImpl();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("accounts", service.allAccounts());
        req.getRequestDispatcher("/view/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            service.saveAccount(req.getParameter("name"),
                    req.getParameter("password"),
                    Double.valueOf(req.getParameter("balance")));

        } catch (UserIsAlreadyExistsException e) {
           req.setAttribute("accountExistError", "true");
        } catch (EmptyFieldException e) {
            req.setAttribute("emptyField", "true");
        }finally {
            doGet(req,resp);
        }
    }

    @Override
    public void destroy() {
        System.out.println("====== DESTROY ======");
    }
}
