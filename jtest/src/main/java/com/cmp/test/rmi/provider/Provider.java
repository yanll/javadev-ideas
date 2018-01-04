package com.cmp.test.rmi.provider;

import com.cmp.test.rmi.facade.IService;

import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by YANLL on 2017/12/27.
 */
public class Provider {

    public static void main(String[] args) throws RemoteException, NamingException, MalformedURLException, AlreadyBoundException {
        System.out.println("Constructing server implementation");
        IService service = new ServiceImpl();
        System.out.println("Binding server implementation to registry");
        LocateRegistry.createRegistry(1099);
        Naming.bind("rmi://localhost:1099/service", service);
        System.out.println("Waiting for invocations from clients ...");
    }


}
