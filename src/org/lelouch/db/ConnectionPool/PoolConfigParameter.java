package org.lelouch.db.ConnectionPool;


public enum PoolConfigParameter {

    //    /**
//     * 配置参数
//     */
//    public static enum Parameter {
    g_testOnBorrow("testOnBorrow", "Boolean", "申请连接检测"),//
    //---------------
    c3p0_1("c3p0_1", "String", "c3p0_1"),
    //---------------
    DBCP_1("c3p0_1", "String", "c3p0_1"),
    //---------------
    Druid_1("c3p0_1", "String", "c3p0_1"),
    //---------------
    HikariCP_1("c3p0_1", "String", "c3p0_1"),
    //---------------
    ;
    /**
     * 名称
     */
    private String id;
    /**
     * 数据类型
     */
    private String type;
    /**
     * 描述
     */
    private String describe;


    /**
     * 构造方
     *
     * @param id
     * @param type
     * @param describe
     */
    private PoolConfigParameter(String id, String type, String describe) {
        this.id = id;
        this.type = type;
        this.describe = describe;
    }

    public String getId() {
        return id;
    }

    public String getDescribe() {
        return describe;
    }

    /**
     * @param id
     *
     * @return
     */
    public static PoolConfigParameter getById(String id) {
        for (PoolConfigParameter parameter : values()) {
            if (parameter.getId().equals(id)){
                return parameter;
            }
        }
        return null;
    }
//    }
}
