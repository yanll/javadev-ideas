package com.cmp.test.jackson;

import com.cmp.common.json.UtilJackson;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by YAN on 2015/09/01.
 */
public class Main {


    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void test() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UtilJackson.mapper.setDateFormat(DEFAULT_DATE_FORMAT);
        String str = "{\"name\":\"admin\",\"age\":18}";
        Person person = UtilJackson.readValue(str, Person.class);
        System.out.println(person.getName());
        JsonNode node = UtilJackson.mapper.readTree(str);
        System.out.println(node.get("name"));
        System.out.println(node.get("name").asText());
        System.out.println(node.toString());
        System.out.println(node.textValue());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", "A");
        map.put("b", "B");
        map.put("c", new Date());
        System.out.println(UtilJackson.writeValueAsString(map));

    }


    @Test
    public void createNewJson() {

        ArrayNode arrayNode = UtilJackson.createArrayNode();
        ObjectNode objectNode = UtilJackson.createObjectNode();
        objectNode.put("name", "admin");
        objectNode.put("pwd", "admin");
        arrayNode.add(objectNode);
        objectNode = UtilJackson.createObjectNode();
        objectNode.put("user", "yll");
        objectNode.put("pwd", "yll");
        arrayNode.add(objectNode);
        objectNode = UtilJackson.createObjectNode();
        objectNode.put("user", "zhangsan");
        objectNode.put("pwd", "zhangsan");
        arrayNode.add(objectNode);
        arrayNode.add(1);
        arrayNode.add(2);
        System.out.println(arrayNode);

    }

    @Test
    public void createJsonFromBean() {

        Person person = new Person();
        person.setName("admin");
        person.setAge(18);
        person.setBirthday(new Date());
        String jsonString = UtilJackson.writeValueAsString(person);
        System.out.println(jsonString);

    }

    @Test
    public void testObjectMapper() {
        UtilJackson.factory.copy();
        UtilJackson.mapper.copy();

    }

    @Test
    public void testR() {

        List<Person> list = new ArrayList<Person>();
        Person p1 = new Person("d1", 1, new Date(), new Date());
        Person p2 = new Person(2, new Date(), new Date());
        Person p3 = new Person("d3", new Date(), new Date());
        Person p4 = new Person("", 4, new Date(), new Date());
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        String str = UtilJackson.toJSON(list);
        System.out.println(str);
        List<Person> l1 = UtilJackson.fromJSON(str, List.class);
//        for (Person p : l1) {
//            System.out.println(String.format("%,%,%,%", p.getName(), p.getAge(), p.getBirthday(), p.getD()));
//        }


        Map<String, Object> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", "sss");
        map.put("3", new Date());

        String s = UtilJackson.toJSON(map);
        //System.out.println(s);
        Map pp = UtilJackson.fromJSON(s, Map.class);
        //System.out.println(pp);

    }


    /*
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    Person person = new Person();
                    person.setName("admin");
                    person.setAge(18);
                    person.setBirthday(new Date());
                    System.out.println(UtilJackson.writeValueAsString(person));
                }
            });
        }


        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //此处若修改mapper，则其他线程会受影响
                //UtilJackson.mapper.setDateFormat(DEFAULT_DATE_FORMAT);
                Person person = new Person();
                person.setName("yanll");
                person.setAge(20);
                person.setBirthday(new Date());
                System.out.println(UtilJackson.writeValueAsString(person));
            }
        });
        threadPool.shutdown();
    }
    */


    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    Person person = new Person();
                    person.setName("admin");
                    person.setAge(18);
                    person.setBirthday(new Date());
                    System.out.println(UtilJackson.writeValueAsString(person));
                }
            });
        }


        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //此处若修改mapper，则其他线程会受影响
                //UtilJackson.mapper.setDateFormat(DEFAULT_DATE_FORMAT);
                Person person = new Person();
                person.setName("yanll");
                person.setAge(20);
                person.setBirthday(new Date());
                System.out.println(UtilJackson.writeValueAsString(person));
            }
        });
        threadPool.shutdown();
    }

}
