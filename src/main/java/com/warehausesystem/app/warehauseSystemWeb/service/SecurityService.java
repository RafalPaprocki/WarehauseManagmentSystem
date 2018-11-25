package com.warehausesystem.app.warehauseSystemWeb.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}