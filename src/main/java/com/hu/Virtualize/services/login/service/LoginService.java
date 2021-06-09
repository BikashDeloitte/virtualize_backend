package com.hu.Virtualize.services.login.service;

import com.hu.Virtualize.commands.login.LoginCommand;

public interface LoginService {
    Object login(LoginCommand loginCommand);
}
