package com.reactive.CreditCardsService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class CreditCardsServiceApplicationTests {

	
	public static Logger loggertest = LoggerFactory.getLogger(CreditCardsServiceApplication.class);

	@Test
	void contextLoads() {
		loggertest.info("test case executing");
		assertEquals(true, true);
	}

}
