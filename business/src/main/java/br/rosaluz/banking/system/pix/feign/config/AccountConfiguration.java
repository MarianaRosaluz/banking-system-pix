package br.rosaluz.banking.system.pix.feign.config;

import feign.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;
@Configuration
public class AccountConfiguration {
    @Value("${account-connectTimeoutMillis}")
    private int connectTimeout;

    @Value("${account-readTimeoutMillis}")
    private int readTimeout;

    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeout, TimeUnit.MILLISECONDS, readTimeout, TimeUnit.MILLISECONDS, true);
    }
}
