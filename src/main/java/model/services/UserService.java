package model.services;

import model.entity.User;

import java.util.Set;

public interface UserService {

    /**
     * Method to create {@link User}
     *
     * @param user this {@code user} will be created
     * @return returns {@code true} if {@link User} was created
     *         or else {@code false}
     */
boolean create (User user);


    /**
     * Method to get all {@link User} emails
     *
     * @return return {@link Set} of all {@link User} emails
     */
Set<String> getAllUsersEmails();

    /**
     * Method to get all {@link User}
     *
     * @return return {@link Set} of all {@link User}
     */
Set <User> getAllUsers();

    /**
     * Method to get {@link User} by {@code email} and {@code password}
     * @param email user email
     * @param password user password
     *
     * @return return {@link User}
     */
User getUserByEmailAndPassword(String email, String password);

    /**
     * Method to get {@link User} by {@code id}
     * @param id user id
     *
     * @return return {@link User}
     */
User getUserById (long id);

}
