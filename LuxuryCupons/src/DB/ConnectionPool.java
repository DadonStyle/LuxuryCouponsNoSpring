package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

/**
 * The Connection pool class
 * enables the management of the connections to the database
 */
public class ConnectionPool {
    //Fields
    /**
     * NUM_OF_CONS field - indicates the number of connections we would like to limit for protection
     */
    private static final int NUM_OF_CONS = 10;
    /**
     * instance field - indicates that the connection pool class is null
     */
    private static ConnectionPool instance = null;
    /**
     * connections field - indicates a stack list of all connections
     */
    private final Stack<Connection> connections = new Stack<>();



    /**
     * Constructor that uses the method that open all the connections
     *
     * @throws SQLException - throws an error if we got an error by wrong SQL statement
     */
    private ConnectionPool() throws SQLException {
        //Open all connections
        openAllConnections();
    }


    /**
     * Get instance method
     * since ConnectionPool is a singleton
     *
     * @return ConnectionPool instance
     */
    public static ConnectionPool getInstance() {
        //Before locking the critical code...
        if (instance == null) {
            //Create the connection pool
            synchronized (ConnectionPool.class) {
                //Before creating the code.....
                if (instance == null) {
                    try {
                        instance = new ConnectionPool();
                    } catch (SQLException err) {
                        System.out.println("ConnectionPool - 'Get instance' method" + ":\n" + err.getMessage()+"\n");
                    }
                }
            }
        }
        return instance;
    }


    /**
     * Get connection
     * checks if there is a free connection to use
     * if not, waits for a return connection.
     *
     * @return connection ready for use
     * @throws InterruptedException - throws an error if we got an error by an interrupted thread
     */
    public Connection getConnection() throws InterruptedException {
        synchronized (connections) {
            if (connections.isEmpty()) {
                //Wait until we will get a connection back
                connections.wait();
            }
            return connections.pop();
        }
    }


    /**
     * Return connection
     * this method will notify the getConnection method that there's a free connection
     * ready for use.
     *
     * @param connection - gets a connection to return
     */
    public void returnConnection(Connection connection) {
        synchronized (connections) {
            connections.push(connection);
            //Notify that we got back a connection from the user...
            connections.notify();
        }
    }


    /**
     * Open all connections
     * This method will open 10 connections in advance
     *
     * @throws SQLException - throws an error if we got an error by wrong SQL statement
     */
    private void openAllConnections() throws SQLException {
        for (int index = 0; index < NUM_OF_CONS; index += 1) {
            //Load the configuration file and update the data for connection
            try {
                DatabaseManager.getConfiguration();
            } catch (Exception err) {
                System.out.println("ConnectionPool - 'Open all connections' method" + ":\n" + err.getMessage()+"\n");
            }
            //Make the connection ......
            Connection connection = DriverManager.getConnection(DatabaseManager.url, DatabaseManager.username, DatabaseManager.password);
            connections.push(connection);
        }
    }


    /**
     * Close all connections
     * this method closes all the 10 connections if all of them are out of use
     *
     * @throws InterruptedException -throws an error if we got an error by an interrupted thread
     */
    @SuppressWarnings("unused")
    public void closeAllConnection() throws InterruptedException {
        synchronized (connections) {
            while (connections.size() < NUM_OF_CONS) {
                connections.wait();
            }
            connections.removeAllElements();
            instance = null;
        }
    }
}
