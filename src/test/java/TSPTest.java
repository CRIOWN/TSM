import com.criown.utils.Dijkstra;
import com.criown.utils.TxTsp;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TSPTest {

    @Test
    public void test1() throws IOException {
        TxTsp.main();
    }

    @Test
    public void test2() throws IOException   {
        List list =new ArrayList<>();
        for(int i=0;i<5;i++)        list.add(0,i);
        System.out.println(list);
        System.out.println(list.get(0));
        int y=100;
        y*=0.8;
        System.out.println(y);

    }

    @Test
    public void test3(){

    }

}
