package com.github.alexgoncharov06.server.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBInitializingListener implements ServletContextListener {
    private static final Logger log = Logger
            .getLogger(DBInitializingListener.class.getName());
    private static final String TEST_TABLE_NAME = "users";
    private static BufferedReader bf;

    private static void tryconnect(String string, Connection con, URL url) {
        Statement s = null;
        try {

            s = con.createStatement();
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet table = dbm.getTables(null, null, string, null);
            if ((table.next()) == false) {
                try {

                    bf = new BufferedReader(new InputStreamReader(url.openStream()));
                    while (bf.ready()) {
                        String s1 = bf.readLine();
                        s.execute(s1);
                        System.out.println(s1);
                    }
                    con.commit();

                } catch (IOException e) {
                    log.severe("Error reading file: " + string + ".sql "
                            + e.getMessage());
                }
            }
        } catch (SQLException sqle) {
            log.severe("Error executing database initialization SQL 'Users': "
                    + sqle.getMessage());
        }

    }

    public void contextInitialized(ServletContextEvent event) {
        log.info("Servlet Users Content Initialized");
        ServletContext ctx = event.getServletContext();

        try {
            log.info("Users.Loading hsqldb DB Driver...");
            Class.forName("org.hsqldb.jdbcDriver");
//			Class.forName("org.h2.Driver");
            URL url = ctx.getResource("/WEB-INF/login_users.sql");
            initializeDatabase(url);
        } catch (ClassNotFoundException e) {
            log.log(Level.SEVERE, "Users.Could not load H2 Driver!", e);
        } catch (SQLException sqle) {
            log.log(Level.SEVERE, "Users.Fatal Database Error!", sqle);
        } catch (MalformedURLException e) {
            log.log(Level.SEVERE, "Users.Fatal Database Error!", e);
        }
    }

    private void initializeDatabase(URL url) throws SQLException {
        Connection connection = null;
        try {
            log.info("Users. Starting up H2 DB...");
            connection = DriverManager
//					.getConnection("jdbc:h2:~/users");
                    .getConnection("jdbc:hsqldb:file:db;shutdown=true;hsqldb.write_delay=false");
            if (!schemaHasBeenInitialized(connection)) {
                initializeSchema(connection, url);
            }
        } catch (SQLException sqle) {
            log.log(Level.SEVERE, "Could not connect to H2 DB Users!",
                    sqle);
            throw sqle;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private boolean schemaHasBeenInitialized(Connection con)
            throws SQLException {
        final DatabaseMetaData metaData = con.getMetaData();
        ResultSet tablesResultSet = null;

        try {
            tablesResultSet = metaData.getTables(null, null, null,
                    new String[]{"users"});

            while (tablesResultSet.next()) {
                final String tableName = tablesResultSet
                        .getString("TABLE_NAME");
                if (tableName != null
                        && TEST_TABLE_NAME.equalsIgnoreCase(tableName)) {
                    log.info("Users. Schema has already been initialized...");
                    return true;
                }
            }
        } finally {
            if (tablesResultSet != null) {
                tablesResultSet.close();
            }
        }
        log.info("Users. Schema has not already been initialized...");
        return false;
    }

    private void initializeSchema(Connection con, URL url) {
        Connection connection = null;
        log.info("Users. Initializing Database Schema...");

        try {
            connection = DriverManager
                    .getConnection("jdbc:hsqldb:file:db;shutdown=true;hsqldb.write_delay=false");
//					.getConnection("jdbc:h2:~/users");
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Could not connect to H2 Users!",
                    e);
        }
        tryconnect("users", connection, url);
        log.info("Database  'Users' initialization is complete!");
    }

    public void contextDestroyed(ServletContextEvent event) {
        log.info("Servlet Context Destroyed");
        try {
            log.info("Shutting down H2 DB...");
            DriverManager
                    .getConnection("jdbc:hsqldb:file:db;shutdown=true;hsqldb.write_delay=false");
//					.getConnection("jdbc:h2:./users;");
        } catch (SQLException sqle) {
            if (sqle.getMessage().equals("Database 'Users' shutdown.")) {
                log.info("H2 DB Shutdown successfully!");
            } else {
                throw new RuntimeException(
                        "An error occurred shutting down the H2 instance!",
                        sqle);
            }
        }
    }
}