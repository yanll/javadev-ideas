package com.cmp.test;

import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:*.xml","classpath:mappers/*.xml"})
@Transactional
@Ignore
public class Base extends TestCase {

    private static final Logger logger = LoggerFactory.getLogger(Base.class);

}
