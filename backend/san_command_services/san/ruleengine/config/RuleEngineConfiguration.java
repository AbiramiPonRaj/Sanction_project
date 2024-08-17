package com.ponsun.san.ruleengine.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RuleEngineConfiguration {
    private static final KieServices kieServices = KieServices.Factory.get();

    private static final String[] ALL_DRL_RULE_FILES = {
            "criminal-score-rule.drl"
    };

    @Bean
    public KieContainer kieContainer() {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        for(String rule: ALL_DRL_RULE_FILES) {
            String ruleDrlPath = "rules/"+rule;
            kieFileSystem.write(ResourceFactory.newClassPathResource(ruleDrlPath));
        }
        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }
}
