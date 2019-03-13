package controller;

import model.command.Command;
import model.command.CommandFactory;
import model.exception.PageNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MainServlet.class);
    private static final String PAGE_SUFFIX = "jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processUser(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processUser(req, resp);
    }

    private void processUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        CommandFactory commandFactory = CommandFactory.getInstance();
        Command command = commandFactory.getCommand(req);
        String page = command.execute(req);
        resolvePagePath(page,req,resp);
//        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
//        dispatcher.forward(req, resp);
    }

    private void resolvePagePath(String page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            if (page.contains(PAGE_SUFFIX))
                forwardToPage(page, request, response);
            else
                redirectToPage(page, request, response);
        } catch (PageNotFoundException e) {
            logger.debug("Threw a PageNotFoundException, stack trace: "+e);
            forwardToNotFoundErrorPage(request, response);
        }
    }

    private void forwardToPage(String page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Forward to: "+page);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/"+page);
        dispatcher.forward(request, response);
    }

    private void redirectToPage(String page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Redirect to "+page);
        response.sendRedirect(request.getContextPath() + "/"+page);
    }

    private void forwardToNotFoundErrorPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = "/error/error404.jsp";
        forwardToPage(page, request, response);
    }

}
