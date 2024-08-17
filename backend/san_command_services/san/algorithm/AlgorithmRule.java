package com.ponsun.san.algorithm;

import com.ponsun.san.algorithm.cdo.CriminalRuleCDO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlgorithmRule {
    private final KieContainer kieContainer;
//    public boolean isCriminalRulePassed(final String value1, final String value2,final double jaroWinkler,final double value4,final double matching_score) {
//        if(StringUtils.isBlank(value1)) {
//            //throw error;
//        }
//        if(StringUtils.isBlank(value2)) {
//            //throw error;
//        }
//        double value1d = Double.parseDouble(value1);
//        double value2d = Double.parseDouble(value2);
//        return isCriminalRulePassed(value1d, value2d,jaroWinkler,value4,matching_score);
//    }

    public boolean isCriminalRulePassed(final double value1,final double value2,final double jaroWinkler,final double value4,final double matching_score) {
        final KieSession ruleSession = kieContainer.newKieSession();
        try {
            final CriminalRuleCDO criminalRuleCDO = new CriminalRuleCDO();
            criminalRuleCDO.setScore1(value1);
            criminalRuleCDO.setScore2(value2);
            criminalRuleCDO.setScore3(jaroWinkler);
            criminalRuleCDO.setScore2(value4);
            criminalRuleCDO.setSearchscore(matching_score);
            ruleSession.insert(criminalRuleCDO);
            ruleSession.fireAllRules();

            if(StringUtils.isNoneBlank(criminalRuleCDO.getStatus())
                    && criminalRuleCDO.getStatus().equalsIgnoreCase("success")) {
                return true;
            }
            return false;
        } finally {
            ruleSession.dispose();
        }
    }
}
