package com.ponsun.san.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class HazelConfig {
    @Bean
    public Config hazelcastConfig() {
        Config config = new Config();
//        config.setInstanceName("hazelcast-instance");
//        NetworkConfig networkConfig = config.getNetworkConfig();
//        networkConfig.setPort(5701);
//        networkConfig.setPortAutoIncrement(true);
        return config;
    }

    @Bean
    public HazelcastInstance hazelcastInstance(Config config) {
        return Hazelcast.newHazelcastInstance(config);
    }
//    @Bean
//    public HazelcastInstance hazelcastInstance(){
//        System.out.println("Hazelcast instance started.");
//
//
//        return Hazelcast.newHazelcastInstance();
////        // Create Hazelcast configuration
////        Config config = new Config();
////
////        // Network configuration
////        NetworkConfig networkConfig = config.getNetworkConfig();
////        networkConfig.getJoin().getMulticastConfig().setEnabled(true); // Enable multicast
////        networkConfig.getJoin().getTcpIpConfig().setEnabled(true); // Enable TCP/IP
////        networkConfig.getJoin().getTcpIpConfig().addMember("127.0.0.1"); // Add member
////
////        // Map configuration
////        config.getMapConfig("default")
////                .setBackupCount(1)
////                .setAsyncBackupCount(1)
////                .setTimeToLiveSeconds(3600); // Time-to-live for cache entries
//
//        // Management center configuration
////        config.setManagementCenterConfig(new ManagementCenterConfig()
////                .setEnabled(true)
////                .setUrl("http://localhost:8080/mancenter"));
//
//        // Management Center configuration
////        ManagementCenterConfig managementCenterConfig = new ManagementCenterConfig();
////        managementCenterConfig.setUrl("http://localhost:8080/mancenter");
////
////        config.setManagementCenterConfig(managementCenterConfig);
//
//        // Create Hazelcast instance
//        //HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
//
//        // Do something with the Hazelcast instance
////        System.out.println("Hazelcast instance started.");
////        return Hazelcast.newHazelcastInstance(config);
//    }
}
//CSE