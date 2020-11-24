package com.xc.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class XcTestApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(XcTestApplication.class);
        System.out.println("0.0==============服务重启完毕=============0.0");
    }
}
