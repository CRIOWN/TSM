import com.criown.utils.CityEnum;
import com.criown.utils.DateUtil;
import com.criown.utils.MD5;
import org.junit.Test;

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



}
