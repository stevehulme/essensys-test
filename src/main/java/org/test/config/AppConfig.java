package org.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.test.data.CallCostMap;
import org.test.factory.CallInformationFactory;
import org.test.factory.CallInformationWIthCostFactory;
import org.test.service.*;

@Configuration
public class AppConfig {

    @Bean
    public CallRaterService callRaterService() {
        return new CallRaterService();
    }

    @Bean
    public CallCostMap callCostMap() {
        return new CallCostMap();
    }

    @Bean
    public CallInformationFactory callInformationFactory() {
        return new CallInformationFactory();
    }

    @Bean
    public CallInformationWIthCostFactory callInformationWIthCostFactory() {
        return new CallInformationWIthCostFactory();
    }

    @Bean
    public CallCostCalculator callCostCalculator() {
        return new CallCostCalculator();
    }

    @Bean
    public CallInformationWIthCostSerialiser callInformationWIthCostSerialiser() {
        return new CallInformationWIthCostSerialiser();
    }

    @Bean
    public CallRaterRowProcessor callRaterRowProcessor() {
        return new CallRaterRowProcessor();
    }

    @Bean
    public CallRaterProcessor callRaterProcessor() { return new CallRaterProcessor(); }

    @Bean
    public FilesReaderWrapper filesReaderWrapper() {
        return new FilesReaderWrapper();
    }

    @Bean
    public FilesWriterWrapper filesWriterWrapper() { return  new FilesWriterWrapper();}
}
