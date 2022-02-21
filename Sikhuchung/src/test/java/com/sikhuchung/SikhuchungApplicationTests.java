package com.sikhuchung;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.sikhuchung.domain.NoticeDTO;
import com.sikhuchung.mapper.SikhuchungMapper;

@SpringBootTest
class SikhuchungApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private SqlSessionFactory sessionFactory;

    @Autowired
    private SikhuchungMapper sikhuchungMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testByApplicationContext() {
        try {
            System.out.println("=========================");
            System.out.println(context.getBean("sqlSessionFactory"));
            System.out.println("=========================");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBySqlSessionFactory() {
        try {
            System.out.println("=========================");
            System.out.println(sessionFactory.toString());
            System.out.println("=========================");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOfInsert() {
        NoticeDTO params = new NoticeDTO();
        params.setUserId("test");
        params.setNoticeTitle("테스트 게시글 제목");
        params.setNoticeContent("테스트 게시글 내용");

        int result = sikhuchungMapper.insertNotice(params);
        System.out.println("결과는 " + result + "입니다.");
    }

}
