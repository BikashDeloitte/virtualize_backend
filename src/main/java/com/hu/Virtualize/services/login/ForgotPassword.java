package com.hu.Virtualize.services.login;

public interface ForgotPassword {
    public boolean sendEmail(String subject, String message, String to);
}
