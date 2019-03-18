package model.command;

import model.command.impl.*;
import model.command.impl.auth.LoginCommand;
import model.command.impl.auth.LogoutCommand;
import model.command.impl.auth.RegisterCommand;
import model.command.impl.category.CreateCategoryCommand;
import model.command.impl.find.*;
import model.command.impl.mail.ChangeCategoryCommand;
import model.command.impl.mail.DeleteMailCommand;
import model.command.impl.mail.ReportedSpamCommand;
import model.command.impl.mail.SendMailCommand;
import model.command.impl.redirect.LoginRedirectCommand;
import model.command.impl.redirect.RegisterRedirectCommand;
import model.command.impl.redirect.SendRedirectCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Logger logger = LogManager.getLogger(CommandFactory.class);

    private static volatile CommandFactory factory;

    private final Map<String, Command> commands;

    private CommandFactory() {
        logger.debug("Initialization of commands hashmap");
        commands = new HashMap<>();
        commands.put("register", new RegisterCommand());
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("main", new MainCommand());
        commands.put("addcategory", new CreateCategoryCommand());
        commands.put("send", new SendMailCommand());
        commands.put("delete", new DeleteMailCommand());
        commands.put("changecategory", new ChangeCategoryCommand());
        commands.put("getmailbycustomcategory", new FindMailByCustomCategoryCommand());
        commands.put("getmailbycategory", new FindMailByCategoryCommand());
        commands.put("findbytitle", new FindMailByTitleCommand());
        commands.put("findbytag", new FindMailByTagCommand());
        commands.put("reportedspam", new ReportedSpamCommand());
        commands.put("findbysenderorrecipient", new FindMailBySenderOrRecipientCommand());
        commands.put("findbytimeperiod", new FindMailInTimePeriodCommand());
        commands.put("loginredir", new LoginRedirectCommand());
        commands.put("registerredir", new RegisterRedirectCommand());
        commands.put("sendredir", new SendRedirectCommand());

    }

    public static CommandFactory getInstance() {
        CommandFactory localInstance = factory;
        if (localInstance == null) {
            synchronized (CommandFactory.class) {
                localInstance = factory;
                if (localInstance == null) {
                    factory = new CommandFactory();
                    logger.debug("Create first CommandFactory instance");
                }
            }
        }
        logger.debug("Return CommandFactory instance");
        return factory;
    }


    public Command getCommand(HttpServletRequest request) {
        Command current = new EmptyCommand();
        String action = request.getParameter("action");
        if (isInvalidCommand(action)) {
            logger.info("There is no such command" + request.getMethod() + request.getRequestURI());
            return current;
        }
        logger.info("There is such command" + action);
        current = commands.getOrDefault(action, current);
        return current;
    }

    private boolean isInvalidCommand(String action) {
        return action == null || action.isEmpty();
    }

}
