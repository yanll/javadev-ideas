package com.cmp.test.rmi.provider;

import com.cmp.test.rmi.facade.IService;
import com.cmp.test.rmi.facade.UserVO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by YANLL on 2017/12/27.
 */
public class ServiceImpl extends UnicastRemoteObject implements IService {

    @Override
    public UserVO getUser(int id, String name) throws RemoteException {
        UserVO u = new UserVO();
        u.setId(id);
        u.setName(name);
        return u;
    }

    protected ServiceImpl() throws RemoteException {
        super();
    }


}
