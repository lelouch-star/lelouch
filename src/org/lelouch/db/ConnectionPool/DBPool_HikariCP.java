package org.lelouch.db.ConnectionPool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.pool.HikariPool;
import org.lelouch.exception.RException;
import org.lelouch.tools.Check;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * <b>HikariCP连接池</b>
 * <p>
 * <pre>
 *          ┌─┐       ┌─┐
 *       ┌──┘ ┴───────┘ ┴──┐
 *       │                 │
 *       │       ───       │
 *       │  ─┬┘       └┬─  │
 *       │                 │
 *       │       ─┴─       │
 *       │                 │
 *       └───┐         ┌───┘
 *           │         │
 *           │         │
 *           │         │
 *           │         └──────────────┐
 *           │                        │
 *           │                        ├─┐
 *           │      author:lelouch    ┌─┘
 *           │                        │
 *           └─┐  ┐  ┌───────┬──┐  ┌──┘
 *             │ ─┤ ─┤       │ ─┤ ─┤
 *             └──┴──┘       └──┴──┘
 *                 神兽保佑
 *                 代码无BUG!
 * </pre>
 *
 * @author lelouch
 * @require HikariCP-java7-2.4.13.jar
 */
public class DBPool_HikariCP extends DBConnectionPool {

    private static Logger log = LoggerFactory.getLogger(DBPool_HikariCP.class);

    private HikariPool dataSource = null;

    /**
     * @param poolConfig
     */
    private void _intit(PoolConfig poolConfig) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(poolConfig.getDriver());
        hikariConfig.setJdbcUrl(poolConfig.getJdbcUrl());
        if (poolConfig.getUsername() != null){
            hikariConfig.setUsername(poolConfig.getUsername());
        }
        if (poolConfig.getPassword() != null){
            hikariConfig.setPassword(poolConfig.getPassword());
        }
        hikariConfig.setMinimumIdle(poolConfig.getSize_poolInit());
        hikariConfig.setMaximumPoolSize(poolConfig.getSize_poolMax());
        hikariConfig.setConnectionTimeout(poolConfig.getWaitTime());
        if (Check.isNotNULL(poolConfig.getParameters())){

        }
        dataSource = new HikariPool(hikariConfig);
    }

    private Connection _getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            log.info("DBPool_HikariCP Connection!");
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection(connection);
            RException.run("getConnection ", "getConnection error " + RException.getStackTraceInfo(e));
            return null;
        }
        return connection;
    }

    private void _shutdown() {
        try {
            this.dataSource.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private DataSource _getDataSource() {
        return dataSource.getUnwrappedDataSource();
    }

    public static void closeConnection(Connection connection) {
        DBConnectionPool.closeConnection(connection);
    }

    /**
     * @param poolConfig
     */
    public DBPool_HikariCP(PoolConfig poolConfig) {
        super(poolConfig);
        _intit(poolConfig);
    }

    @Override
    public Connection getConnection() {
        return _getConnection();
    }

    @Override
    public DataSource getDataSource() {
        return _getDataSource();
    }

    @Override
    public void shutdown() {
        _shutdown();
    }


}
