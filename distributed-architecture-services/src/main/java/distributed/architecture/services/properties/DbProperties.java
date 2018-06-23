package distributed.architecture.services.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class DbProperties {

    @Value("${jdbc.driverClassName}")
    private String dbDriverClassName;

    @Value("${jdbc.username}")
    private String dbUserName;

    @Value("${jdbc.password}")
    private String dbPassword;

    @Value("${jdbc.url}")
    private String dbUrl;

    @Value("${jdbc.initialSize}")
    private int initialSize;

    @Value("${jdbc.minIdle}")
    private int minIdle;

    @Value("${jdbc.maxActive}")
    private int maxActive;

    @Value("${jdbc.maxWait}")
    private int maxWait;

    /**
     * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
     */
    @Value("${jdbc.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    /**
     * 配置一个连接在池中最小生存的时间，单位是毫秒
     */
    @Value("${jdbc.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${jdbc.validationQuery}")
    private String validationQuery;

    @Value("${jdbc.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${jdbc.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${jdbc.testOnReturn}")
    private boolean testOnReturn;

    /***
     * 打开PSCache，并且指定每个连接上PSCache的大小，mysql设置为false
     */
    @Value("${jdbc.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${jdbc.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${jdbc.statementExecutableSqlLogEnable}")
    private boolean statementExecutableSqlLogEnable;

    @Value("${jdbc.setSlowSqlMillis}")
    private int slowSqlMillis;


}
