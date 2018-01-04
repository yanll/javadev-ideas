package com.cmp.test.rmi.consumer;

import com.cmp.test.rmi.facade.IService;
import com.cmp.test.rmi.facade.UserVO;

import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by YANLL on 2017/12/27.
 */
public class Consumer {

    public static void main(String[] args) throws NamingException, RemoteException, MalformedURLException, NotBoundException {
        System.out.println("RMI registry binding:");
        String url = "rmi://localhost:1099/service";
        IService service = (IService) Naming.lookup(url);
        UserVO user = service.getUser(1000, "admin");
        System.out.println(user.getId() + ":" + user.getName());
    }

}
