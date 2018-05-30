package org.lelouch.db;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lelouch.db.ConnectionPool.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class T_DBConnectionPool {
    private static Logger log = LoggerFactory.getLogger(T_DBConnectionPool.class);

    public static PoolConfig poolConfig = null;
    public static DBConnectionPool dbConnectionPool = null;
    public static Connection con = null;

    @BeforeClass
    public static void init() {
        log.info("init start");
        poolConfig = new PoolConfig("jdbc:mysql://localhost:3306/storage?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true", "com.mysql.jdbc.Driver");
        poolConfig.setWaitTime(2000l);
        poolConfig.setSize_poolMin(3);
        poolConfig.setSize_poolInit(3);
        poolConfig.setSize_poolMax(20);
        poolConfig.setUsername("root");
        poolConfig.setPassword("root");
        poolConfig.setDriver("com.mysql.jdbc.Driver");
        log.info("init end ");
    }

    @Test
    public void DBPool_c3p0() {
        log.info("DBPool_c3p0 start");
        dbConnectionPool = new DBPool_c3p0(poolConfig);
        poolConfig.addParameter(PoolConfigParameter.g_testOnBorrow,true);
        con = dbConnectionPool.getConnection();
        Assert.assertNotNull(dbConnectionPool.getDataSource());
        Assert.assertNotNull(con);
        log.info("DBPool_c3p0 end " + con);
    }

    @Test
    public void DBPool_DBCP() {
        log.info("DBPool_DBCP start");
        dbConnectionPool = new DBPool_DBCP(poolConfig);
        con = dbConnectionPool.getConnection();
        Assert.assertNotNull(dbConnectionPool.getDataSource());
        Assert.assertNotNull(con);
        log.info("DBPool_DBCP end " + con);
    }

    @Test
    public void DBPool_Druid() {
        log.info("DBPool_Druid start");
        dbConnectionPool = new DBPool_Druid(poolConfig);
        con = dbConnectionPool.getConnection();
        Assert.assertNotNull(dbConnectionPool.getDataSource());
        Assert.assertNotNull(con);
        log.info("DBPool_Druid end " + con);
    }

    @Test
    public void DBPool_HikariCP() {

        log.info("DBPool_HikariCP start");
        dbConnectionPool = new DBPool_HikariCP(poolConfig);
        con = dbConnectionPool.getConnection();
        Assert.assertNotNull(dbConnectionPool.getDataSource());
        Assert.assertNotNull(con);
        log.info("DBPool_HikariCP end " + con);

    }

    @After
    public void After() {
        log.info("After start");
        dbConnectionPool.closeConnection(con);
        dbConnectionPool.shutdown();
        log.info("After end");
    }


}
