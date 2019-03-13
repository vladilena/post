package controller.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DefaultValidation implements Validation {
    private static final Logger logger = LogManager.getLogger(Validation.class);
    private static volatile Validation validation;

    private DefaultValidation() {
    }

public static Validation getInstance (){
    Validation localInstance = validation;
    if(localInstance == null) {
        synchronized (DefaultValidation.class) {
            localInstance = validation;
            if(localInstance == null) {
                validation = new DefaultValidation();
                logger.debug("Create first DefaultValidation instance");
            }
        }
    }
    logger.debug("Return DefaultValidation instance");
    return validation;
}


    public boolean isLoginValid(String login) {
        return (login != null && login.matches(RegexContainer.REGEX_EMAIL));
    }


    public boolean isPasswordValid(String password) {
        return (password != null && password.matches(RegexContainer.REGEX_PASSWORD));
    }

    public boolean isNameValid(String name) {
        if (name == null) {
            return false;
        }
        Pattern p = Pattern.compile(RegexContainer.REGEX_NAME, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(name);
        return (m.matches());
    }

    public boolean isCategoryValid(String category) {
        Pattern p = Pattern.compile(RegexContainer.REGEX_CATEGORY, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(category);
        return (m.matches());
    }

    public boolean isRecipientEmailValid(String email) {
        return (email != null && email.matches(RegexContainer.REGEX_EMAIL));
    }

    public boolean isTitleValid(String title) {
        Pattern p = Pattern.compile(RegexContainer.REGEX_TITLE, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(title);
        return (m.matches());
    }

    public boolean isTagsValid(String tags) {
        Pattern p = Pattern.compile(RegexContainer.REGEX_TAGS, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(tags);
        return (m.matches());
    }

    public boolean isMessageValid(String message) {
        Pattern p = Pattern.compile(RegexContainer.REGEX_MESSAGE, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(message);
        return (m.matches());
    }

    public boolean isDateTimeValid(String datetime) {
        return (datetime != null && datetime.matches(RegexContainer.REGEX_DATETIME));
    }

    public boolean isUserValid(String email, String password) {
        return isLoginValid(email) && isPasswordValid(password);
    }

    public boolean isTagValid(String tags) {
        Pattern p = Pattern.compile(RegexContainer.REGEX_TAG, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(tags);
        return (m.matches());
    }
}


