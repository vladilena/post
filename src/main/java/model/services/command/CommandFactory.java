package model.services.command;

import model.services.command.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static CommandFactory factory = new CommandFactory();

    private Map<String, Command> comands = new HashMap<>();

    private CommandFactory() {

    }

    public static CommandFactory commandFactory() {
        if (factory == null) {
            factory = new CommandFactory();
        }
        return factory;
    }

    {
        comands.put("register", new RegisterCommand());
        comands.put("login", new LoginCommand());
        comands.put("logout", new LogoutCommand());
        comands.put("main", new MainCommand());
        comands.put("addcategory", new CreateCategoryCommand());
        comands.put("send", new SendMailCommand());
        comands.put("delete", new DeleteMailCommand());
        comands.put("changecategory", new ChangeCategoryCommand());
        comands.put("getmailbycategory", new FindMailByCategoryCommand());
        comands.put("findbytitle", new FindMailByTitleCommand());
        comands.put("findbytag", new FindMailByTagCommand());
        comands.put("reportedspam", new ReportedSpamCommand());
        comands.put("findbysenderorrecipient", new FindMailBySenderOrRecipient());
        comands.put("findbytimeperiod", new FindMailInTimePeriodCommand());


    }

    public Command getCommand(HttpServletRequest request) {
        String action = request.getParameter("action");
        Command command = comands.get(action);
        return command;
    }

}
