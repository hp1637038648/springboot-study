package com.hp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.hp.controller.HelloWorldController;
/**
 * 独立安装测试
 * @author hp
 * @Date 2019/08/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootIntroductionApplicationTest {
	private MockMvc mvc;
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
    }
	@Test
	public void getHello() throws Exception {
		//MockMvcRequestBuilders.get("/user/1")构建一个get请求
		mvc.perform(MockMvcRequestBuilders.get("/hello")
				//accept指定客户端能够接收的内容类型，
				//MediaType.APPLICATION_JSON表示互联网媒体类型的json数据格式
				.accept(MediaType.APPLICATION_JSON_UTF8)) 
		        //验证HTTP请求的状态是否为200
				.andExpect(status().isOk())
				//验证响应后的结果是否为“Hello World”
				.andExpect(content().string(equalTo("Hello World")))
				.andExpect(handler().methodName("index"))
				//输出整个响应结果信息，可以在调试的时候使用
				.andDo(MockMvcResultHandlers.print())
				.andReturn()
				.getResponse();
	}
}
