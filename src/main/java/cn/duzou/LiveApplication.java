package cn.duzou;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@MapperScan("cn.duzou.*.mapper")
@SpringBootApplication
public class LiveApplication{

    public static void main(String[] args) {
            SpringApplication.run(LiveApplication.class, args);
    }

}
