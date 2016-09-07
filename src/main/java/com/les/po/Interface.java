package com.les.po;

import java.io.Serializable;

/**
 * @author Lydia
 * @ClassName: Interface
 * @Description:
 * @date 2016/8/25
 */
public class Interface implements Serializable {
    private static final long serialVersionUID = 2120869894112984147L;

//    private int interface_Id;
    private String interfaceName;
    private String interfaceURL;
    private String interfaceDiscription;

//    public void setInterface_Id(int interface_Id) { this.interface_Id = interface_Id;}
    public void setInterfaceName(String interfaceName) { this.interfaceName = interfaceName;}
    public void setInterfaceURL(String interfaceURL) { this.interfaceURL = interfaceURL;}
    public void setInterfaceDiscription(String interfaceDiscription) {
        this.interfaceDiscription = interfaceDiscription;
    }

//    public int getInterface_Id() { return interface_Id;}
    public String getInterfaceName() { return interfaceName;}
    public String getInterfaceURL() { return interfaceURL;}
    public String getInterfaceDiscription() {
        return interfaceDiscription;
    }
}
