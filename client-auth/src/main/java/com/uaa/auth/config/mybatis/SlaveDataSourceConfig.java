package com.uaa.auth.config.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author wyf
 * @Date 2018-12-12.16:42
 */
@Configuration
@PropertySource("classpath:properties/dbconfig2.properties")
public class SlaveDataSourceConfig
{
//    private Logger logger = Logger.getLogger(SlaveDataSourceConfig.class);

    @Value("${slave.datasource.url}")
    private String dbUrl;

    @Value("${slave.datasource.username}")
    private String username;

    @Value("${slave.datasource.password}")
    private String password;

    @Value("${slave.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${slave.datasource.initialSize}")
    private int initialSize;

    @Value("${slave.datasource.minIdle}")
    private int minIdle;

    @Value("${slave.datasource.maxActive}")
    private int maxActive;

    @Value("${slave.datasource.maxWait}")
    private int maxWait;

    @Value("${slave.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${slave.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${slave.datasource.validationQuery}")
    private String validationQuery;

    @Value("${slave.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${slave.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${slave.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${slave.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${slave.datasource.filters}")
    private String filters;

    @Value("${slave.datasource.maxOpenPreparedStatements}")
    private int maxOpenPreparedStatements;

    @Value("${slave.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${slave.datasource.type}")
    private String dbType;

    @Value("${slave.datasource.publicKey}")
    private String publicKey;

    @Value("${slave.datasource.passwordCallback}")
    private String passwordCallbackClassName;

    @Value("${slave.datasource.connectionProperties}")
    private String connectionProperties;

    @Bean
    public ServletRegistrationBean slaveDruidServlet()
    {
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //添加初始化参数：initParams
        //白名单：
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean slaveFilterRegistrationBean()
    {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
        return filterRegistrationBean;
    }

    @Bean(name = "slaveDataSource")
    public DataSource slaveDataSource()
    {
        DruidDataSource slaveDataSource = new DruidDataSource();
        slaveDataSource.setUrl(this.dbUrl);
        slaveDataSource.setUsername(username);
        slaveDataSource.setPassword(password);
        slaveDataSource.setDriverClassName(driverClassName);
        slaveDataSource.setInitialSize(initialSize);
        slaveDataSource.setMinIdle(minIdle);
        slaveDataSource.setMaxActive(maxActive);
        slaveDataSource.setMaxWait(maxWait);
        slaveDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        slaveDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        slaveDataSource.setValidationQuery(validationQuery);
        slaveDataSource.setTestWhileIdle(testWhileIdle);
        slaveDataSource.setTestOnBorrow(testOnBorrow);
        slaveDataSource.setTestOnReturn(testOnReturn);
        slaveDataSource.setPoolPreparedStatements(poolPreparedStatements);
        slaveDataSource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
        slaveDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        slaveDataSource.setConnectionProperties(connectionProperties);
        /*try
        {
            datasource.setPasswordCallbackClassName(passwordCallbackClassName);
        }
        catch (Exception e)
        {
            logger.error("druid configuration initialization passwordCallbackClassName", e);
        }*/
        try
        {
            slaveDataSource.setFilters(filters);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            // logger.error("druid configuration initialization filter", e);
        }
        return slaveDataSource;
    }
}
