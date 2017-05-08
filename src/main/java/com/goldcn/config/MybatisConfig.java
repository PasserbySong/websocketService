package com.goldcn.config;

/**
 * Created by Administrator on 2017/4/17.
 */
import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.github.pagehelper.PageHelper;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan({"com.goldcn.*.dao"})
@ConfigurationProperties(
        prefix = "mybatis"
)
public class MybatisConfig {
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    public MybatisConfig() {
    }

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = this.getTomcatPoolingDataSource();
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(this.dataSource());
    }

    private DataSource getTomcatPoolingDataSource() {
        DruidXADataSource dataSource = new DruidXADataSource();
        dataSource.setDriverClassName(this.driverClassName.trim());
        dataSource.setUrl(this.url.trim());
        dataSource.setUsername(this.username.trim());
        dataSource.setPassword(this.password.trim());
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000L);
        dataSource.setRemoveAbandonedTimeout(180);
        dataSource.setRemoveAbandoned(true);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(1800000L);
        return dataSource;
    }

    @Bean(
            name = {"transactionManager"}
    )
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(this.dataSource());
    }

    @Bean(
            name = {"sqlSessionFactory"}
    )
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(this.dataSource());
        sessionFactory.setTypeAliasesPackage("com.goldcn.*.model");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        Interceptor[] plugins = new Interceptor[]{pageHelper};
        sessionFactory.setPlugins(plugins);
        return sessionFactory.getObject();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}
