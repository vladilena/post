package controller.validation;

public interface Validation {

    boolean isLoginValid(String login);

    boolean isPasswordValid(String password);

    boolean isNameValid(String name);

    boolean isCategoryValid(String category);

    boolean isRecipientEmailValid(String email);

    boolean isTitleValid(String title);

    boolean isTagsValid(String tags);

    boolean isMessageValid(String message);

    boolean isDateTimeValid(String datetime);

    boolean isUserValid(String email, String password);

    boolean isTagValid(String tag);
}


