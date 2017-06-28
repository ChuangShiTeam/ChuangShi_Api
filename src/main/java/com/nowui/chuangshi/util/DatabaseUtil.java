package com.nowui.chuangshi.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.nowui.chuangshi.constant.Config;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final DruidDataSource druidDataSource = new DruidDataSource();

    static {
        druidDataSource.setDriverClassName(Config.driver_class);
        druidDataSource.setUrl(Config.jdbc_url);
        druidDataSource.setUsername(Config.user);
        druidDataSource.setPassword(Config.password);
        druidDataSource.setInitialSize(Config.initial_size);
        druidDataSource.setMinIdle(Config.min_idle);
        druidDataSource.setMaxActive(Config.max_activee);

        try {
            druidDataSource.setFilters("stat,wall");
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        }
    }

    public static Connection getConnection() {
        try {
            return druidDataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("SQLException: ", e);
        }
    }

}
