package distributed.architecture.server.browser;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonView;
import com.weiwei.distributed.architecture.api.browser.dto.UserQueryCondition;
import com.weiwei.distributed.architecture.api.browser.models.LocalFileInfo;
import com.weiwei.distributed.architecture.api.browser.models.User;
import distributed.architecture.server.component.DeferredResultHolder;
import distributed.architecture.server.component.MockQueue;
import distributed.architecture.services.dao.RememberMeTokenRepository;
import distributed.architecture.services.entity.PersistentLogins;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.print.Pageable;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.Callable;

@RestController
@RequestMapping(value = "/user")
public class SecurityDemoBrowser {
    private final static Logger logger = LoggerFactory.getLogger(SecurityDemoBrowser.class);

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Autowired
    private RememberMeTokenRepository rememberMeTokenRepository;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @PostMapping("/register")
    public void register(User user,HttpServletRequest request){
        String userId = user.getUserName();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        providerSignInUtils.doPostSignUp(userId,new ServletWebRequest(request));
    }

    @GetMapping("/social")
    public ModelAndView getSocialUserInfo(HttpServletRequest request){
//        SocialUserInfo socialUserInfo = new SocialUserInfo();
////        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
////        socialUserInfo.setProviderId(connection.getKey().getProviderId());
////        socialUserInfo.setProviderUserId(connection.getKey().getProviderUserId());
////        socialUserInfo.setHeadimg(connection.getImageUrl());
////        socialUserInfo.setNickname(connection.getDisplayName());
////        Map<String, Object> map = new HashMap<>();
////        map.put("user",socialUserInfo);
        Map<String, Object> map = new HashMap<>();
        return new ModelAndView("test-index", map);
    }

    @GetMapping("/hello")
    public String securityDemoBrowser(){
        List<PersistentLogins> persistentLoginsList = rememberMeTokenRepository.getLocalAllByUsernameOrderByDate("wangww");
        return "hello spring security >>>>>>>>>";
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @JsonView(User.UserSimpleView.class)
    public List<User> query(@RequestParam(name = "userName",required = false,defaultValue = "tom") String userName){
        logger.info(">>>>userName>>>>==[{}]",userName);
        List<User> list = new ArrayList<>();
        list.add(new User("tong","123"));
        list.add(new User("tom","123"));
        list.add(new User("zhangsan","123"));
        return list;
    }

    @RequestMapping(value = "/userQuery",method = RequestMethod.GET)
    public List<String> queryUser(@RequestParam UserQueryCondition userQueryCondition, @PageableDefault(size = 20,page = 2,sort = "age desc") Pageable pageable){
        List<String> list = new ArrayList<>();
        list.add("a");list.add("b");list.add("c");
        return list;
    }

    @GetMapping(value = "/details/{userName}")
    public User detailQuery(@PathVariable String userName){

        User user = new User();
        user.setUserName(userName);
        return user;
    }

    @PostMapping(value = "/create")
    public User create(@Valid @RequestBody User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().stream().forEach(objectError ->{
                FieldError fieldError = (FieldError)objectError;
                logger.info("fieldName:[{}]-->error:[{}]",fieldError,objectError.getDefaultMessage());});
        }

        logger.info("==========create==========,create_body:=[{}]",JSONUtil.toJsonStr(user));

        throw new RuntimeException("test the interceptor");
//        throw new SystemBizException("01011","出现系统异常");
//        return user;
    }

    @PutMapping(value = "/update/{userName}")
    public User update(@PathVariable String userName){
        logger.info("==========create==========,update_body:=[{}]",JSONUtil.toJsonStr(userName));
        User user = new User(); user.setUserName(userName);
        return user;
    }

    @PostMapping(value = "/file")
    @ApiOperation(value = "用户文件上传")
    public LocalFileInfo uploadFile(MultipartFile multipartFile) throws Exception{
        logger.info("------fileSize=[{}],fileOriginName=[{}],fileName=[{}]--------"
                ,multipartFile.getSize(),multipartFile.getOriginalFilename(),multipartFile.getName());

        String savePath = "E:\\tmp";

        File saveFilePath = new File(savePath,new Date().getTime()+".txt");
        multipartFile.transferTo(saveFilePath);

        return new LocalFileInfo(saveFilePath.getPath());
    }

    @GetMapping(value = "/downFile/{downFilePath}")
    @ApiOperation(value = "用户文件下载")
    public void downFile(@ApiParam(value = "文件下载路径") @PathVariable String downFilePath, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{

        String savePath = "E:\\tmp";
        try(
                InputStream inputStream = new FileInputStream(new File(savePath,downFilePath+".txt"));
                OutputStream outputStream = httpServletResponse.getOutputStream();
                )
        {
            httpServletResponse.setContentType("application/x-download");
            httpServletResponse.addHeader("Content-Disposition","attachment;filename=downTest.txt");
            IoUtil.copy(inputStream,outputStream);
        }
    }

    @RequestMapping(value = "/simpleOrder")
    public String order() throws Exception{
        logger.info("主线程开始");
        Thread.sleep(1000);
        logger.info("主线程结束");
        return "ok";
    }

    @RequestMapping(value = "/callableOrder")
    public Callable<String> callableOrder() throws Exception{
        logger.info("-----------------主线程开始-----------------");
        Callable<String> callback = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("-----------------副线程开始---------------");
                Thread.sleep(1000);
                logger.info("-----------------副线程开始---------------");
                return "success";
            }
        };
        Thread.sleep(1000);
        logger.info("-----------------主线程结束-----------------");
        return callback;
    }

    @RequestMapping(value = "/deferredOrder")
    public DeferredResult<String> deferredResultOrder() throws Exception{
        logger.info("-----------------主线程开始-----------------");
        String serialNum = RandomUtil.randomNumbers(8);
        mockQueue.setPlaceOrder(serialNum);

        DeferredResult<String> deferredResult = new DeferredResult<>();
        deferredResultHolder.getMap().put(serialNum,deferredResult);

        logger.info("-----------------主线程结束-----------------");
        return deferredResult;
    }

}
