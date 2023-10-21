package it.academy.dao.datasource.impl;

public class JdbcDataSourceProperties {

    private final String user;
    private final String password;
    private final String url;
    private final String driver;

    public JdbcDataSourceProperties(
            final String user,
            final String password,
            final String url,
            final String driver
    ) {
        this.user = user;
        this.password = password;
        this.url = url;
        this.driver = driver;
    }

    public final String getUser() {
        return user;
    }

    public final String getPassword() {
        return password;
    }

    public final String getUrl() {
        return url;
    }

    public final String getDriver() {
        return driver;
    }
}
