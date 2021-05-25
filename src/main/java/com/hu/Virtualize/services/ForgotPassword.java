package com.hu.Virtualize.services;

public interface ForgotPassword {
    public boolean sendEmail(String subject, String message, String to);
}
