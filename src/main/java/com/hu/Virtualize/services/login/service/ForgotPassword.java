package com.hu.Virtualize.services.login.service;

public interface ForgotPassword {
    boolean sendEmail(String subject, String message, String to);
}
