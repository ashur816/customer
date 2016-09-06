package com.les.dao.mapper;

import com.les.po.Interface;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Lydia
 * @ClassName: InterfaceMapper
 * @Description:
 * @date 2016/8/25
 */
@Component
public interface InterfaceMapper {

    List<Interface> getInterfaceList();
}
