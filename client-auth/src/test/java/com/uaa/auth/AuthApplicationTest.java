package com.uaa.auth;


import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wyf
 * @date 2016/12/5
 */

@RunWith(SpringRunner.class)
@SpringBootTest("classpath:application-test.yml")
@DirtiesContext
//@ContextConfiguration(locations = {"classpath:application-test.yml"})
public class AuthApplicationTest  {

}
