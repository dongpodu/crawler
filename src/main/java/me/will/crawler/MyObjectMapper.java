package me.will.crawler;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class MyObjectMapper {
    private MyObjectMapper(){ }
    public static ObjectMapper getObjectMapper(){
        return SingletonHolder.objectMapper;
    }
    private static class SingletonHolder {
        private static final ObjectMapper objectMapper=new ObjectMapper();
        static {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.findAndRegisterModules();
            objectMapper.registerModule(new ParameterNamesModule())
                    .registerModule(new Jdk8Module())
                    .registerModule(new JavaTimeModule()); // new module, NOT JSR310Module
        }
    }
}
