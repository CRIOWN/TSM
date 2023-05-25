import com.criown.utils.CityEnum;
import com.criown.utils.DateUtil;
import com.criown.utils.MD5;
import com.criown.utils.Redisutils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class utilstest {


    @Test
    public void test1()
    {
        String arr="1,2,3";
        String[] idarr = arr.split(",");

        System.out.println(idarr);
        System.out.println(idarr.length);

        List<Integer> list= new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
        System.out.println(list.get(0));
    }

    //time
    @Test
    public void  test2()
    {
        Date date = new Date(1675750025000L);
        System.out.println(DateUtil.formatDate1(date));
        System.out.println(DateUtil.formatDate2(date));

    }

    //mapper
    @Test
    public void  testmapper()
    {
        System.out.println(CityEnum.getNameByValue(1));
    }

    @Test
    public void MD5test()
    {
        String str="333333";
        String end= MD5.getMD5(str);
        System.out.println(str+"：："+end);
    }

    @Test
    public void red()
    {
        Jedis jedis =new Jedis("localhost");
        jedis.del("pathlist");
        jedis.set("redisFunction","0");
    }

    @Test
    public void red2()
    {
        Redisutils redisutils= new Redisutils();
        List list= redisutils.getPath();
        System.out.println(list);

    }


    @Test
    public void redison()
    {
        //Jedis jedis =new Jedis("localhost");
        // jedis.set("openredis", String.valueOf(1));
        // jedis.set("openredis", String.valueOf(0));

//        List<Integer> list =new ArrayList<>();
//        for(int i=0;i<5;i++)list.add(i);
//        System.out.println("list is ::"+list);
//        for(Integer i :list)
//            jedis.lpush("pathlist",i.toString());

        //jedis.lpush("pathlist","5");
        //System.out.println(jedis.lpop("pathlist"));

        Redisutils redisutils = new Redisutils();
        int i =redisutils.judge();
        System.out.println(i);
    }

    //Path::[0, 6, 5, 11, 3, 2, 1, 4, 9, 8, 12, 10, 7, 0]
    @Test
    public void Path()
    {
        Jedis jedis =new Jedis("localhost");
        List<Integer> path =new ArrayList<Integer>(Arrays.asList(0, 6, 5, 11, 3, 2, 1, 4, 9, 8, 12, 10, 7, 0));
        System.out.println(path);
        for(Integer i :path) jedis.rpush("pathlist",i.toString());
        List<String> stringList= jedis.lrange("pathlist",0,-1);
        System.out.println(stringList);
        List<Integer> list = new ArrayList<>();
        for(String s: stringList)
            list.add(Integer.parseInt(s));
    }

    @Test
    public void Listandnums()
    {
        Integer[] nums = new Integer[13];
        for (int i=0;i<13;i++) nums[i] = 1;
        System.out.println(nums);
        nums[2]++;
        System.out.println(nums);
        List<Integer> list = Arrays.asList(nums);
        System.out.println(list);
    }

    @Test
    public void sd()
    {
        Integer[] nums = new Integer[13];
        for (int i=0;i<13;i++) nums[i] = 0;
        List<String> cities = Arrays.asList(
                "南京市", "苏州市", "无锡市", "常州市", "南通市", "扬州市",
                "镇江市", "徐州市", "淮安市", "盐城市", "连云港", "泰州市", "宿迁市"
        );
        String s ="苏州市";
        int index = cities.indexOf(s);
        nums[index]++;
        System.out.println(index);
        System.out.println(nums[index]);
    }
}
