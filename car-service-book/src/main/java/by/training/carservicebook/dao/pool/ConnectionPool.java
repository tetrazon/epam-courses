package by.training.carservicebook.dao.pool;

import by.training.carservicebook.dao.exception.DaoException;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;

@Log4j2
final public class ConnectionPool {

	private String url;
	private String user;
	private String password;
	private int maxSize;
	private int checkConnectionTimeout;

	private BlockingQueue<PooledConnection> freeConnections = new LinkedBlockingQueue<>();
	private Set<PooledConnection> usedConnections = new ConcurrentSkipListSet<>();

	private ConnectionPool() {}

	public synchronized Connection getConnection() throws DaoException {
		PooledConnection connection = null;
		while(connection == null) {
			try {
				if(!freeConnections.isEmpty()) {
					connection = freeConnections.take();
					if(!connection.isValid(checkConnectionTimeout)) {
						try {
							connection.getConnection().close();
						} catch(SQLException e) {}
						connection = null;
					}
				} else if(usedConnections.size() < maxSize) {
					connection = createConnection();
				} else {
					log.error("The limit of number of database connections is exceeded");
					throw new DaoException();
				}
			} catch(InterruptedException | SQLException e) {
				log.error("It is impossible to connect to a database", e);
				throw new DaoException(e);
			}
		}
		usedConnections.add(connection);
		log.debug(String.format("Connection was received from pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
		return connection;
	}

	synchronized void freeConnection(PooledConnection connection) {
		try {
			if(connection.isValid(checkConnectionTimeout)) {
				connection.clearWarnings();
				connection.setAutoCommit(true);
				usedConnections.remove(connection);
				freeConnections.put(connection);
				log.debug(String.format("Connection was returned into pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
			}
		} catch(SQLException | InterruptedException e1) {
			log.warn("It is impossible to return database connection into pool", e1);
			try {
				connection.getConnection().close();
			} catch(SQLException e2) {}
		}
	}

	public synchronized void init(String driverClass, String url, String user, String password, int startSize, int maxSize, int checkConnectionTimeout) throws DaoException {
		try {
			destroy();
			Class.forName(driverClass);
			this.url = url;
			this.user = user;
			this.password = password;
			this.maxSize = maxSize;
			this.checkConnectionTimeout = checkConnectionTimeout;
			for(int counter = 0; counter < startSize; counter++) {
				freeConnections.put(createConnection());
			}
		} catch(ClassNotFoundException | SQLException | InterruptedException e) {
			log.fatal("It is impossible to initialize connection pool", e);
			throw new DaoException(e);
		}
	}

	private static ConnectionPool instance = new ConnectionPool();

	public static ConnectionPool getInstance() {
		return instance;
	}

	private PooledConnection createConnection() throws SQLException {
		return new PooledConnection(DriverManager.getConnection(url, user, password));
	}

	public synchronized void destroy() {
		usedConnections.addAll(freeConnections);
		freeConnections.clear();
		for(PooledConnection connection : usedConnections) {
			try {
				connection.getConnection().close();
			} catch(SQLException e) {}
		}
		usedConnections.clear();
	}

	@Override
	protected void finalize() throws Throwable {
		destroy();
	}
}
