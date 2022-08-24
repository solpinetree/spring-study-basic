package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {

    @Test
    @DisplayName("")
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자가 10,000원 주문
        statefulService1.order("userA", 10000);
        //ThreadB: B사용자가 10,000원 주문
        statefulService2.order("userB", 20000);

        // StateService의 price 필드는 공유되는 필드인데, stateService2가 값을 변경(10,000->20,000)
        // 스프링 빈은 항상 무상태(stateless) 로 설계해야하는 이유 !

        //ThreadA: 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    @Configuration
    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}