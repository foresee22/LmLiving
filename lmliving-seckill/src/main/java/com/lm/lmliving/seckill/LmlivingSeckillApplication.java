package com.lm.lmliving.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lm
 * @version 1.0
 */
@SpringBootApplication
@MapperScan("com.lm.lmliving.seckill.mapper")
public class LmlivingSeckillApplication {
    public static void main(String[] args) {
        SpringApplication.run(LmlivingSeckillApplication.class, args);
    }
}
