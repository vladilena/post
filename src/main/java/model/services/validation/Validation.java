package model.services.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static model.services.validation.RegexContainer.*;


public class Validation {
    public boolean isLoginValid(String login) {
        return (login != null && login.matches(REGEX_EMAIL));
    }

    public boolean isPasswordValid(String password) {
        return (password != null && password.matches(REGEX_PASSWORD));
    }

    public boolean isNameValid(String name) {
        Pattern p = Pattern.compile(REGEX_NAME, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(name);
        return (m.matches());
    }

    public boolean isCategoryValid(String category) {
        Pattern p = Pattern.compile(REGEX_CATEGORY, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(category);
        return (m.matches());
    }

    public boolean isRecipientEmailValid(String email) {
        return (email != null && email.matches(REGEX_EMAIL));
    }

    public boolean isTitleValid(String title) {
        Pattern p = Pattern.compile(REGEX_TITLE, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(title);
        return (m.matches());
    }

    public boolean isTagsValid(String tags) {
        Pattern p = Pattern.compile(REGEX_TAGS, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(tags);
        return (m.matches());
    }

    public boolean isMessageValid(String message) {
        Pattern p = Pattern.compile(REGEX_MESSAGE, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(message);
        return (m.matches());
    }

    public boolean isDateTimeValid(String datetime) {
        return (datetime != null && datetime.matches(REGEX_DATETIME));
    }
}


