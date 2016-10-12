package com.cmp.study.hadoopdemo.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.io.Serializable;

/**
 * Created by YANLL on 2016/09/28.
 */
public class ZooKeeperClient {


    public static void main(String[] args) {
        String node_path = "/user";

        //zk集群的地址
        String ZKServers = "192.168.244.132:2181,192.168.244.132:2182,192.168.244.132:2183";
        /*String ZKServers = "192.168.244.132:21818";//docker*/
        /**
         * 创建会话
         * new SerializableSerializer() 创建序列化器接口，用来序列化和反序列化
         */
        ZkClient zkClient = new ZkClient(ZKServers, 10000, 10000, new SerializableSerializer());
        System.out.println("conneted ok!");

        User user = new User();
        user.setId(1000);
        user.setName("Admin");
        /**
         * "/testUserNode" :节点的地址
         * user：数据的对象
         * CreateMode.PERSISTENT：创建的节点类型
         */

        boolean e = zkClient.exists(node_path);
        if (e) {
            zkClient.delete(node_path);
        }

        //返回 true表示节点存在 ，false表示不存在
        System.out.println(e);

        String path = zkClient.create(node_path, user, CreateMode.PERSISTENT);
        System.out.println("created path:" + path);


        Stat stat = new Stat();
        //获取 节点中的对象
        User u = zkClient.readData(node_path, stat);
        System.out.println(u.getName());
        System.out.println(stat);
    }
}


class User implements Serializable {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
