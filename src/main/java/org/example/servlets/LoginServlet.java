package org.example.servlets;

import org.example.service.AccountService;
import org.example.service.impl.MyAccountServiceImpl;
import org.example.util.MyPasswordHashing;
import org.example.util.PasswordHashing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private AccountService service;
    private final System.Logger logger = System.getLogger(LoginServlet.class.getName());
    private PasswordHashing hashing;

    @Override
    public void init() throws ServletException {
        try{
            service = new MyAccountServiceImpl();
            hashing = new MyPasswordHashing();
        }catch (Exception e){
            logger.log(System.Logger.Level.ERROR, e);
            throw new RuntimeException();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String hashedPass = hashing.doHashing(email, password);

        if (service.allAccounts().containsKey(email) ||
                service.allAccounts().get(email).getPassword().equals(hashedPass)){
            HttpSession session = req.getSession();
            session.setAttribute("user", email);
            resp.sendRedirect("/hello");
        }else {
            doGet(req, resp);
        }
    }
}
