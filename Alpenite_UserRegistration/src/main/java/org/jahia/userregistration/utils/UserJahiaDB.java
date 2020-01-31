package org.jahia.userregistration.utils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import java.sql.*;

public class UserJahiaDB {
    private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(UserJahiaDB.class);

    //private static final String	CONNECTION_STRING = "jdbc:mysql://localhost:3306/userjahia?user=root&password=TeaUnisys2012"; //qta
    private static final String	CONNECTION_STRING = "jdbc:mysql://10.100.100.156:3306/userjahia?user=root&password=TeaUnisys2012"; //db qta
    //private static final String	CONNECTION_STRING = "jdbc:mysql://10.100.100.154:3306/userjahia?user=root&password=TeaUnisys2012"; //db prod

    /**
     * Database connection
     * @return
     */
    public Connection connectedDB(){
        Connection connection = null;
        /*try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("ALPENITE UserJahiaDB - ERRORE DRIVER MYSQL ClassNotFoundException: " + e.getMessage());
        }*/

        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            logger.info("ALPENITE UserJahiaDB - DB CONNESSO");
        } catch (SQLException e) {
            logger.error("ALPENITE UserJahiaDB - ERRORE DRIVER MYSQL SQLException: " + e.getMessage());
        }

        return connection;
    }

    public void closeConnection (Connection connection){
        try {
            if (connection != null) {
                logger.info("ALPENITE UserJahiaDB - Chiudo Connessione");
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("ALPENITE UserJahiaDB - ERROR ERRORE CHIUSURA CONNESSIONE: " + e.getMessage());
        }
    }

    /**
     * Check if username exist
     * @param connection
     * @param username
     * @return
     * @throws SQLException
     */
    public boolean checkUsername (Connection connection, String username) throws SQLException {
        logger.info("ALPENITE UserJahiaDB - check if username exist in db");
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("select * from user_registration where username='" + username + "'");
        return rs.next();
    }

    /**
     *
     * @param connection
     * @param username
     * @param parameterName
     * @param parameterValue
     * @return
     * @throws SQLException
     */
    public boolean checkParameterInDB(Connection connection, String username, String parameterName, String parameterValue) throws SQLException {
        logger.info("ALPENITE UserJahiaDB - check if email exist in db");
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("select * from user_registration where username='" + username + "'");

        if (rs.next()) {
            String propertyDB = rs.getString(parameterName);
            logger.info("ALPENITE UserJahiaDB - propertyDB: " + propertyDB);
            if (StringUtils.isNotEmpty(propertyDB) && propertyDB.equals(parameterValue)) {
                return true;
            }
        }
        logger.info("ALPENITE UserJahiaDB - username not exist in db");
        return false;
    }

    /**
     *
     * @param connection
     * @param property
     * @param newProperty
     * @param username
     */
    public void updateDBProperty (Connection connection, String property, String newProperty, String username) throws SQLException {
        //update mail in db
        logger.info("ALPENITE UserJahiaDB - update: " + property);
        PreparedStatement updateProperty = connection.prepareStatement("update user_registration set " + property + "= '"+ newProperty +"' where username= ? ");
        updateProperty.setString(1, username);
        int updateProperty_done = updateProperty.executeUpdate();
        logger.info("ALPENITE UserJahiaDB - updateProperty_" + property + "_done: " + updateProperty_done);
        updateProperty.close();
    }
}
