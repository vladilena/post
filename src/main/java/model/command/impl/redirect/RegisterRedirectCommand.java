package model.command.impl.redirect;

import model.command.Command;
import model.util.PathManager;

import javax.servlet.http.HttpServletRequest;

public class RegisterRedirectCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PathManager.getProperty("path.page.registration");
    }
}


