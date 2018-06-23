package distributed.architecture.services.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import distributed.architecture.services.properties.DbProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
@Configuration
public class DbConfig {

    /**
     * sqlSessionFactoryBean sql会化工厂
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Value(value = "#{dataSource}") DataSource dataSource,PageHelper pageHelper) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        return sqlSessionFactoryBean;
    }

    /***
     * 事务管理
     */
    @Bean
    public DataSourceTransactionManager transactionManager(@Value(value = "#{dataSource}") DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    /***
     * 数据源定义
     */
    @Bean
    public DataSource dataSource(DbProperties dbProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dbProperties.getDbDriverClassName());
        dataSource.setUrl(dbProperties.getDbUrl());
        dataSource.setUsername(dbProperties.getDbUserName());
        dataSource.setPassword(dbProperties.getDbPassword());
        dataSource.setInitialSize(dbProperties.getInitialSize());
        dataSource.setMaxActive(dbProperties.getMaxActive());
        dataSource.setTestWhileIdle(false);

        try {
            dataSource.getConnection();
        }catch (Exception ex){
            log.error("Datasource get connection failed",ex);
            System.exit(1);
        }

        dataSource.setProxyFilters(getFileters(dbProperties));
        return dataSource;
    }

    /***
     * 获取过滤之后对象信息列表
     * @param dbProperties
     * @return
     */
    private List<Filter> getFileters(DbProperties dbProperties){
        List<Filter> filters = new ArrayList<>();
        Slf4jLogFilter slf4jLogFilter = new Slf4jLogFilter();
        slf4jLogFilter.setStatementExecutableSqlLogEnable(dbProperties.isStatementExecutableSqlLogEnable());
        filters.add(slf4jLogFilter);

        StatFilter statFilter = new StatFilter();
        statFilter.setMergeSql(true);
        statFilter.setLogSlowSql(true);
        statFilter.setSlowSqlMillis(dbProperties.getSlowSqlMillis());
        filters.add(statFilter);
        return filters;
    }

    /**
     * 分页插件信息
     */
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
