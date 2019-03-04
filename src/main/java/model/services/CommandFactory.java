package model.services;

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
        comands.put("delete", new DeleteMessageCommand());
        comands.put("changecategory", new ChangeCategory());
        comands.put("getmailbycategory", new GetMailByCategoryCommand());
        comands.put("findbytitle", new FindMessageByTitleCommand());
        comands.put("findbytag", new FindMessageByTagCommand());
        comands.put("reportedspam", new ReportedSpamCommand());
        comands.put("findbysenderorrecipient", new FindMailBySenderOrRecipient());
        comands.put("findbytimeperiod", new FindMailsInTimePeriodCommand());


    }

    public Command getCommand(HttpServletRequest request) {
        String action = request.getParameter("action");
        Command command = comands.get(action);
        return command;
    }

}