package org.lelouch.db.ConnectionPool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.lelouch.exception.RException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * <b>c3p0连接池</b>
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
 * @require c3p0-0.9.1.2.jar
 */
public class DBPool_c3p0 extends DBConnectionPool {

    private static Logger log = LoggerFactory.getLogger(DBPool_c3p0.class);

    private ComboPooledDataSource dataSource = null;

    private void _intit(PoolConfig poolConfig) {
        dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl(poolConfig.getJdbcUrl());
        if (poolConfig.getUsername() != null){
            dataSource.setUser(poolConfig.getUsername());
        }
        if (poolConfig.getPassword() != null){
            dataSource.setPassword(poolConfig.getPassword());
        }
        try {
            dataSource.setDriverClass(poolConfig.getDriver());
            //初始数量
            dataSource.setInitialPoolSize(poolConfig.getSize_poolInit());
            //最大数量
            dataSource.setMaxPoolSize(poolConfig.getSize_poolMax());
            //最大等待时间
            dataSource.setCheckoutTimeout((int) poolConfig.getWaitTime());

//            if (Check.isNotNULL(poolConfig.getParameters())){
//                Map<PoolConfigParameter, Object> parameters = poolConfig.getParameters();
//                if (Check.isNotNULL_MapKey(parameters, PoolConfigParameter.g_testOnBorrow)){
//                    dataSource.setTestConnectionOnCheckin(Boolean.valueOf(parameters.get(PoolConfigParameter.g_testOnBorrow).toString()));
//                }
//            }
        } catch (PropertyVetoException e) {
            log.error("DBPool_c3p0 ConnectionPool error " + RException.getStackTraceInfo(e));
            e.printStackTrace();
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
        this.dataSource.close();
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
    public DBPool_c3p0(PoolConfig poolConfig) {
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
