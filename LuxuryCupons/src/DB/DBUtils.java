package DB;

import java.sql.*;
import java.util.Map;

/**
 * The DB Utils class
 * contains auxiliary methods that receive sql queries and run them in the data base
 */
public class DBUtils {

    /**
     * Run query
     * this method will run a row of SQL statement
     * does not return result
     *
     * @param sql - get SQL string to be executed
     */
    public static void runQuery(String sql) {
        //Method doesn't return result
        Connection connection = null;
        try {
            //Taking a connection from connection pool
            connection = ConnectionPool.getInstance().getConnection();
            //Run the sql command
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        } catch (Exception err) {
            System.out.println("DBUtils -'Run query' method"+ ":\n" + err.getMessage()+"\n");
        } finally {
            //Close the connection
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }


    /**
     * Get query
     * this method will get a result from our database by sending sql statement
     * returns result
     *
     * @param sql - run a row SQL data to the server
     * @return ResultSet-ResultSet from DB
     */
    @SuppressWarnings("unused")
    public static ResultSet getQuery(String sql) {
        //Method return result
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            //Taking a connection from connection pool
            connection = ConnectionPool.getInstance().getConnection();
            //Run the sql command
            PreparedStatement statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
        } catch (InterruptedException | SQLException e) {
            System.out.println("DBUtils - 'Get query' method" + ":\n" + e.getMessage()+"\n");
        } finally {
            //Close the connection
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return resultSet;
    }

    /**
     * Run Query
     * this method will take a params list by Map collection and run the statements in the DB
     *
     * @param query  - row SQL query to database
     * @param params - get params to be inserted to the SQL statement
     * @throws SQLException - if we got an error by wrong SQL statement
     */
    public static void runQuery(String query, Map<Integer, Object> params) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            params.forEach((key, value) -> {
                try {
                    //int,string,date,boolean,double,float
                    if (value instanceof Integer) {
                        statement.setInt(key, (int) value);
                    } else if (value instanceof String) {
                        statement.setString(key, String.valueOf(value));
                    } else if (value instanceof Date) {
                        statement.setDate(key, (java.sql.Date) value);
                    } else if (value instanceof Boolean) {
                        statement.setBoolean(key, (boolean) value);
                    } else if (value instanceof Double) {
                        statement.setDouble(key, (double) value);
                    } else if (value instanceof Float) {
                        statement.setFloat(key, (float) value);
                    } else if (value instanceof Timestamp) {
                        statement.setTimestamp(key, (Timestamp) value);
                    }
                } catch (SQLException err) {
                    System.out.println("DBUtils -'Run query' method"  + ":\n" + err.getMessage()+"\n");
                }
            });
            statement.execute();
        } catch (Exception err) {
            System.out.println("DBUtils -'Run query' method" + ":\n" + err.getMessage()+"\n");
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    /**
     * Get Query
     * this method will get Statement and Params list and return a ResultSet from the DB
     *
     * @param query  SQL statement query
     * @param params SQL params list to be injected
     * @return return a result from the DB
     * @throws SQLException throw an error if we have a problem with SQL statement
     */
    public static ResultSet getQuery(String query, Map<Integer, Object> params) throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            params.forEach((key, value) -> {
                try {
                    //int,string,date,boolean,double,float
                    if (value instanceof Integer) {
                        statement.setInt(key, (int) value);
                    } else if (value instanceof String) {
                        statement.setString(key, String.valueOf(value));
                    } else if (value instanceof Date) {
                        statement.setDate(key, (java.sql.Date) value);
                    } else if (value instanceof Boolean) {
                        statement.setBoolean(key, (boolean) value);
                    } else if (value instanceof Double) {
                        statement.setDouble(key, (double) value);
                    } else if (value instanceof Float) {
                        statement.setFloat(key, (float) value);
                    } else if (value instanceof Timestamp) {
                        statement.setTimestamp(key, (Timestamp) value);
                    }
                } catch (SQLException err) {
                    System.out.println("DBUtils -'Get query' method"  + ":\n" + err.getMessage()+"\n");
                }
            });
            //statement execute
            resultSet = statement.executeQuery();
        } catch (Exception err) {
            System.out.println("DBUtils -'Get query' method" + ":\n" + err.getMessage()+"\n");
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return resultSet;
    }
}
