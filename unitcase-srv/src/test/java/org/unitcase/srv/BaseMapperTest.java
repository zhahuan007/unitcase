package org.unitcase.srv;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * mybaits单元测试
 * 每个mapper继承这个类
 * @author zhahuan
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-ds-test.xml", "classpath:applicationContext-mybatis.xml"})
public abstract class BaseMapperTest {
	
	@Before
	public void init(){
		PropertyConfigurator.configureAndWatch("classpath:log4j.properties", 60*1000); // log4j配置文件，设置为1分钟自动加载一次配置
	}
}
