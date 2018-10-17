package com.uguryazici.uwallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.uguryazici.uwallet.entity")
public class UWalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(UWalletApplication.class, args);
    }
}
