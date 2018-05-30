package org.lelouch.db.ConnectionPool;

import com.alibaba.druid.pool.DruidDataSource;
import org.lelouch.exception.RException;
import org.lelouch.tools.Check;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * <b>druid连接池</b>
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
 * @require druid-1.1.9.jar
 */
public class DBPool_Druid extends DBConnectionPool {

    private static Logger log = LoggerFactory.getLogger(DBPool_Druid.class);

    private DruidDataSource dataSource = null;

    /**
     * @param poolConfig
     */
    private void _intit(PoolConfig poolConfig) {
        dataSource = new DruidDataSource();
        dataSource.setUrl(poolConfig.getJdbcUrl());
        dataSource.setDriverClassName(poolConfig.getDriver());
        if (poolConfig.getUsername() != null){
            dataSource.setUsername(poolConfig.getUsername());
        }
        if (poolConfig.getPassword() != null){
            dataSource.setPassword(poolConfig.getPassword());
        }
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
        this.dataSource.close();
    }

    private DataSource _getDataSource() {
        return dataSource;
    }

    public static void closeConnection(Connection connection) {
        DBConnectionPool.closeConnection(connection);
    }

    /**
     * @param poolConfig
     */
    public DBPool_Druid(PoolConfig poolConfig) {
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
