package com.lym.spring.framework.test.v3;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ApplicationContextTest.class, BeanDefinitonTest.class, ConstructorResolverTest.class })
public class V3AllTests {

}
