package com.hu.Virtualize.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginCommand {
    private String id;
    private String password;

    // type must be ("USER", "ADMIN" )
    private String type;

}
