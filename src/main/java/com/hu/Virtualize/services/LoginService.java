package com.hu.Virtualize.services;

import com.hu.Virtualize.commands.LoginCommand;

public interface LoginService {
    Object login(LoginCommand loginCommand);
}
