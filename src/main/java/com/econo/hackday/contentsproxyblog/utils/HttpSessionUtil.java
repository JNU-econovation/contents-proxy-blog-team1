package com.econo.hackday.contentsproxyblog.utils;

import com.econo.hackday.contentsproxyblog.model.Account;

import javax.servlet.http.HttpSession;

public class HttpSessionUtil {
	public static final String ACCOUNT_SESSION_KEY = "sessionedAccount";

	public static boolean isLogin(HttpSession httpSession) {
		Object sessionedAccount = httpSession.getAttribute(ACCOUNT_SESSION_KEY);
		if (sessionedAccount == null) {
			return false;
		}
		return true;
	}

	public static Account getSessionedAccount(HttpSession httpSession) {
		if (!isLogin(httpSession)) {
			throw new RuntimeException("account not logged-in");
		}
		return (Account) httpSession.getAttribute(ACCOUNT_SESSION_KEY);
	}

}
