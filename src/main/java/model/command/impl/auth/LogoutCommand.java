package model.command.impl.auth;



import model.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static model.command.CommandConstants.USER;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(USER);
        return "controller?action=main";
    }
}
