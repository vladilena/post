package model.services;

import static model.services.RegexContainer.*;

public class Validation {
    boolean isLoginValid(String login) {
        return (login != null && login.matches(REGEX_LOGIN));
    }

    boolean isPasswordValid(String password) {
        return (password != null && password.matches(REGEX_PASSWORD));
    }

    boolean isNameValid(String name) {
        return (name != null && name.matches(REGEX_NAME_LAT));
    }

    boolean isCategoryValid(String category) {
        return (category != null && category.matches(REGEX_CATEGORY));
    }

    boolean isRecipientEmailValid(String email) {
        return (email != null && email.matches(REGEX_EMAIL));
    }

    boolean isTitleValid(String title) {
        return (title != null && title.matches(REGEX_TITLE));
    }

    boolean isTagsValid(String tags) {
        return (tags != null && tags.matches(REGEX_TAGS));
    }

    boolean isMessageValid(String message) {
        return (message != null && message.matches(REGEX_MESSAGE));
    }

    boolean isDateTimeValid(String datetime) {
        return (datetime != null && datetime.matches(REGEX_DATETIME));
    }
}


