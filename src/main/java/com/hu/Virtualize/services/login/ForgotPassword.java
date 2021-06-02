package com.hu.Virtualize.services.login;

public interface ForgotPassword {
    boolean sendEmail(String subject, String message, String to);
}
