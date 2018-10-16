package com.uguryazici.uwallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.uguryazici.uwallet.entity")
@EnableJpaRepositories(basePackages = "com.uguryazici.uwallet.repository")
public class UWalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(UWalletApplication.class, args);
    }
}
