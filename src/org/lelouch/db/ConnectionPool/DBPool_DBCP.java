package org.lelouch.db.ConnectionPool;

import org.apache.commons.dbcp.BasicDataSource;
import org.lelouch.exception.RException;
import org.lelouch.tools.Check;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * <b>DBCP连接池</b>
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
 * @require commons-dbcp-1.4.jar
 * @require commons-pool-1.6.jar.jar
 */
public class DBPool_DBCP extends DBConnectionPool {

    private static Logger log = LoggerFactory.getLogger(DBPool_DBCP.class);

    private BasicDataSource dataSource = null;

    /**
     * @param poolConfig
     */
    private void _intit(PoolConfig poolConfig) {
        dataSource = new BasicDataSource();
        dataSource.setUrl(poolConfig.getJdbcUrl());
        if (poolConfig.getUsername() != null){
            dataSource.setUsername(poolConfig.getUsername());
        }
        if (poolConfig.getPassword() != null){
            dataSource.setPassword(poolConfig.getPassword());
        }
        dataSource.setDriverClassName(poolConfig.getDriver());
        //初始数量
        dataSource.setInitialSize(poolConfig.getSize_poolInit());
        //最大数量
        dataSource.setMaxActive(poolConfig.getSize_poolMax());
        //最大等待时间
        dataSource.setMaxWait(poolConfig.getWaitTime());
        if(Check.isNotNULL(poolConfig.getParameters())){

        }
    }

    private Connection _getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            closeConnection(connection);
            RException.run("getConnection ", "getConnection error " + RException.getStackTraceInfo(e));
            return null;
        }
        return connection;
    }


    private void _shutdown() {
        try {
            this.dataSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DataSource _getDataSource() {
        return this.dataSource;
    }

    public static void closeConnection(Connection connection) {
        DBConnectionPool.closeConnection(connection);
    }

    /**
     * @param poolConfig
     */
    public DBPool_DBCP(PoolConfig poolConfig) {
        super(poolConfig);
        _intit(poolConfig);
    }

    @Override
    public Connection getConnection() {
        return _getConnection();
    }

    @Override
    public void shutdown() {
        _shutdown();
    }

    @Override
    public DataSource getDataSource() {
        return _getDataSource();
    }

}
