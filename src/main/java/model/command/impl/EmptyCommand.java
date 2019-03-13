package model.command.impl;

import model.command.Command;
import model.exception.PageNotFoundException;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        throw new PageNotFoundException("An empty command");
    }
}


