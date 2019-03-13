package model.services;

import model.entity.User;

public interface LoginService {

    /**
     * Method checks whether there is a {@link User} which find by {@code login}
     * and the associated {@code password}
     *
     * @param login {@link User} login
     * @param password {@link User} password
     * @return returns the {@link User} who has been found or {@code null}
     */
    public User login(String login, String password);
}
