package org.lelouch.db.ConnectionPool;

import org.lelouch.tools.Check;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * <b>连接池</b>
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
 */
public abstract class DBConnectionPool {

    private static Logger log = LoggerFactory.getLogger(DBConnectionPool.class);

//    private PoolConfig poolConfig = null;
//
//    private DruidDataSource dataSource = null;

    /**
     * @param poolConfig
     */
    public DBConnectionPool(PoolConfig poolConfig) {

    }

    /**
     * 获取一个Connection对象
     *
     * @return
     */
    abstract public Connection getConnection();
    /**
     * 获取一个Connection对象
     *
     * @return
     */
    abstract public DataSource getDataSource();

    /**
     * 关闭数据源
     *
     * @return
     */
    abstract public void shutdown();

    public static void closeConnection(Connection connection) {
        if (Check.isNotNULL(connection)){
            try {
                if (connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
