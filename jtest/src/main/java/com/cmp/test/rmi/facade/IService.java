package com.cmp.test.rmi.facade;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by YANLL on 2017/12/27.
 */
public interface IService extends Remote {

    public UserVO getUser(int id, String name) throws RemoteException;

}
