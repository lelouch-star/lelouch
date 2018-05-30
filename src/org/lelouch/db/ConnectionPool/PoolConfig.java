package org.lelouch.db.ConnectionPool;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>连接池 配置</b>
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
public class PoolConfig {

    /**
     * 用户
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * URL
     */
    private String jdbcUrl;
    /**
     * 驱动类名
     */
    private String driver;

    /**
     * 初始化连接数
     */
    private int size_poolInit = 0;


    /**
     * 最小连接数
     */
    private int size_poolMin = 0;
    /**
     * 最大连接数
     */
    private int size_poolMax = 8;
    /**
     * 最大等待时间 (毫秒)
     */
    private long waitTime = 0;

    /**
     * 配置参数
     */
    private Map<PoolConfigParameter, Object> poolConfigParameters = new HashMap<>();

    public PoolConfig(String jdbcUrl, String driver) {
        this.jdbcUrl = jdbcUrl;
        this.driver = driver;
    }

    public PoolConfig() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public int getSize_poolInit() {
        return size_poolInit;
    }

    public void setSize_poolInit(int size_poolInit) {
        this.size_poolInit = size_poolInit;
    }

    public int getSize_poolMin() {
        return size_poolMin;
    }

    public void setSize_poolMin(int size_poolMin) {
        this.size_poolMin = size_poolMin;
    }

    public int getSize_poolMax() {
        return size_poolMax;
    }

    public void setSize_poolMax(int size_poolMax) {
        this.size_poolMax = size_poolMax;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }

    /**
     * 获取 配置参数
     *
     * @param parameter
     */
    public Object getParameter(PoolConfigParameter parameter) {
        return this.poolConfigParameters.get(parameter);
    }

    /**
     * 获取 配置参数
     *
     * @param
     */
    public Map<PoolConfigParameter, Object> getParameters() {
        return this.poolConfigParameters;
    }

    /**
     * 添加 配置参数
     *
     * @param parameter
     * @param value
     */
    public void addParameter(PoolConfigParameter parameter, Object value) {
        this.poolConfigParameters.put(parameter, value);
    }

    /**
     * 添加 配置参数
     *
     * @param poolConfigParameters
     */
    public void addParameters(Map<PoolConfigParameter, Object> poolConfigParameters) {
        this.poolConfigParameters.putAll(poolConfigParameters);
    }
}
