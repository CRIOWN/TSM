import com.criown.utils.CityEnum;
import com.criown.utils.DateUtil;
import com.criown.utils.MD5;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Date;

public class utilstest {


    @Test
    public void test1()
    {
        String arr="1,2,3";
        String[] idarr = arr.split(",");

        System.out.println(idarr);
        System.out.println(idarr.length);
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
        System.out.println("连接成功");
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
    }

    @Test
    public void red2()
    {
        Jedis jedis =new Jedis("localhost");
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
    }

    @Test
    public void redison()
    {
        Jedis jedis =new Jedis("localhost");
        //jedis.set("openredis", String.valueOf(1));
       // jedis.set("openredis", String.valueOf(0));
        System.out.println("Redis is ::"+jedis.get("openredis"));
    }

}
