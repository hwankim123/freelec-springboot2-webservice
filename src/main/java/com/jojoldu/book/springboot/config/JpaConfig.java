package com.jojoldu.book.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// HelloController가 WebMvcTest이기 때문에 EnableJpaAuditing을 인식할 수 없다는 오류가 터지고,
// 그래서 EnableJpaAuditing과 SpringBootApplication을 분리하기 위해 JpaConfig 파일을 따로 둠
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
