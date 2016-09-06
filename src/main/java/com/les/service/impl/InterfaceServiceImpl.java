package com.les.service.impl;

import com.les.dao.mapper.InterfaceMapper;
import com.les.po.Interface;
import com.les.service.IInterfaceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lydia
 * @ClassName: InterfaceServiceImpl
 * @Description:
 * @date 2016/8/25
 */
@Service
public class InterfaceServiceImpl  implements IInterfaceService {
    @Resource
    private InterfaceMapper interfaceMapper;

    @Override
    public List<Interface> getInterfaceList(){
        return interfaceMapper.getInterfaceList();
    }

}
