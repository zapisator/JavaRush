package com.javarush.task.task37.task3709.connectors;

import com.javarush.task.task37.task3709.security.SecurityChecker;
import com.javarush.task.task37.task3709.security.SecurityCheckerImpl;

public class SecurityProxyConnector implements Connector {

    private final SimpleConnector connector;
    private final SecurityChecker checker = new SecurityCheckerImpl();

    public SecurityProxyConnector(String s) {
        connector = new SimpleConnector(s);
    }

    @Override
    public void connect() {
        if (checker.performSecurityCheck()) {
            connector.connect();
        }

    }
}
