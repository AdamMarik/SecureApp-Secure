package com.bmiapp;

import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static boolean isLoggedIn(HttpSession session) {
        return session != null && session.getAttribute("userId") != null;
    }

    public static int getLoggedInUserId(HttpSession session) {
        return (int) session.getAttribute("userId");
    }
}