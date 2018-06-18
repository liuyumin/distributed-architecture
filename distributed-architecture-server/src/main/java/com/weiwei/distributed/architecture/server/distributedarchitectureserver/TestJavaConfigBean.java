package com.weiwei.distributed.architecture.server.distributedarchitectureserver;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.beans.factory.annotation.Value;

public class TestJavaConfigBean {

    @ApolloConfig("application")
    private Config config;

    @Value("${test:test}")
    public String name;

    @ApolloConfigChangeListener("application")
    private void anotherOnChange(ConfigChangeEvent changeEvent) {

        ConfigChange change = changeEvent.getChange("test");
        System.out.println(String.format("Found change - key: %s, oldValue: %s,"
                + " newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
    }
}
