package com.lym.spring.framework.test.v1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ApplicationConextTest.class, BeanFactoryTest.class, ResourceTest.class })
public class V1AllTests {

}
