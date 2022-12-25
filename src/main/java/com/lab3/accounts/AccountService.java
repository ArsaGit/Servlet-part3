package com.lab3.accounts;

import com.lab3.database.DBService;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private static final DBService dbService = new DBService();
    private static final Map<String, UserProfile> sessionIdToProfile = new HashMap<>();

    public AccountService() {

    }

    public void addNewUser(UserProfile userProfile) { dbService.addUser(userProfile); }

    public UserProfile getUserByLogin(String login) {
        return dbService.getUserByLogin(login);
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
