package distributed.architecture.server.browser;

import cn.hutool.json.JSONUtil;
import com.weiwei.distributed.architecture.api.browser.models.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SecurityDemoBrowserJunitTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception{
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .param("userName","jerry")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();
        System.out.println("----------->>>>>>>>>>--------[{}]"+result);
    }

    @Test
    public void whenQuerySuccess02() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/userQuery")
                .param("userName","jerry")
                .param("userPwd","123456")
                .param("userAge","16")
                .param("size","15")
                .param("page","4")
                .param("sort","age desc")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

    @Test
    public void whenGenInfoSuccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/details/wangww").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("wangww"));
    }

    @Test
    public void whenCreateInfoSuccess() throws Exception{
        User user = new User("tom","tom123");
//        User user = new User(null,"123");
        user.setBirthday(new Date());
        String content = JSONUtil.toJsonStr(user);
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/create")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("tom"))
                .andReturn().getResponse().getContentAsString();
        log.info("==============print whenCreateInfoSuccess response result:=[{}]",result);

    }

    @Test
    public void whenUpdateInfoSuccess() throws Exception{
        String result = mockMvc.perform(MockMvcRequestBuilders.put("/update/tom")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("tom"))
                .andReturn().getResponse().getContentAsString();
        log.info("==============print whenUpdateInfoSuccess response result:=[{}]",result);
    }

    @Test
    public void whenDeleteInfoSuccess() throws Exception{

    }

    @Test
    public void uploadFile() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/user/file")
                .file(new MockMultipartFile("multipartFile","test.txt","multipart/form-data","hello upload file".getBytes("utf-8"))))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
