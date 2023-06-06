package com.example.learn.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "features")
public class FeatureEndpoint {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class MyFeature{
        private boolean isEnabled;

    }
    private final Map<String, MyFeature> featureMap = new ConcurrentHashMap<>();

    public FeatureEndpoint(){
        featureMap.put("Department", new MyFeature(true));
        featureMap.put("UserExperience", new MyFeature(false));
        featureMap.put("Authentication", new MyFeature(false));
    }

    @ReadOperation
    public Map<String, MyFeature> getAllFeatures(){
        return this.featureMap;
    }

    @ReadOperation
    public MyFeature getOneFeature(@Selector String featureName){
        return this.featureMap.get(featureName);
    }
}
