package com.les.controller;

import com.les.po.Interface;
import com.les.service.IInterfaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lydia
 * @ClassName: InterfaceController
 * @Description:
 * @date 2016/8/26
 */
@Controller
public class InterfaceController {
    private static final Logger logger = LoggerFactory.getLogger(InterfaceController.class);

    @Resource
    private IInterfaceService interfaceService;

    @RequestMapping("/getInterfaceList")
    @ResponseBody
    public List<Interface> getInterfaceList(){
        logger.info("从数据库读取接口List");
        List<Interface> interfaceList = interfaceService.getInterfaceList();
        return interfaceList;
    }

}
