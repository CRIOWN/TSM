import com.criown.entity.Client;
import com.criown.mapper.AdminLogMapper;
import com.criown.service.AdminLogService;
import com.criown.service.AdminService;
import com.criown.service.ClientService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;


public class mybatisTest {

    @Autowired
    @Qualifier("AdminServiceImpl")
    private AdminService adminService;

    @Autowired
    @Qualifier("ClientServiceImpl")
    private ClientService clientService;


    @Test
    public void mytest(){
        int id=1;
        String str ="";
        //str=adminService.selectUserpwdByUserid(id);
        System.out.println(str);
    }

    @Test
    public void delbylist()
    {
        List<Integer> integerList=new ArrayList<>();
        List<String> list=new ArrayList<>();
        integerList.add(49);
        integerList.add(50);
        integerList.add(51);

        System.out.println(integerList);
        List<Client> list1= clientService.selectAll();
        System.out.println(list1);
        //clientService.delById(integerList);
    }

    @Test
    public void Stringad(){
        String a=null;
        System.out.println(a);
        if(a==null) System.out.println("NULL");

        String b="null";
        System.out.println(b);
        if(!b.equals("null")) System.out.println(true);
    }
}
