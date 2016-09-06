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
    private String interface_Name;
    private String interface_URL;
    private String interface_discription;

//    public void setInterface_Id(int interface_Id) { this.interface_Id = interface_Id;}
    public void setInterface_Name(String interface_Name) { this.interface_Name = interface_Name;}
    public void setInterface_URL(String interface_URL) { this.interface_URL = interface_URL;}
    public void setInterface_discription(String interface_discription) {
        this.interface_discription = interface_discription;
    }

//    public int getInterface_Id() { return interface_Id;}
    public String getInterface_Name() { return interface_Name;}
    public String getInterface_URL() { return interface_URL;}
    public String getInterface_discription() {
        return interface_discription;
    }
}
