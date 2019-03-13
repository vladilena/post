package model.command.impl.redirect;

import model.command.Command;

import javax.servlet.http.HttpServletRequest;

public class RegisterRedirectCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "register.jsp";
    }
}


