import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcp.board.admin.AdminBoard;
import com.wcp.board.main.MainBoard;
import org.junit.jupiter.api.Test;

public class DirtyTest {

    Gson gson = new GsonBuilder().create();

    @Test
    public void ceilTest(){
        int a = 1;
        int b = 2;
//        gson.to
    }

    @Test
    public void gsonTest(){

        String json = "{\"header\":\"b\"}";

        AdminBoard adminBoard = (AdminBoard)toObj(json, AdminBoard.class);
        
        System.out.println(adminBoard.toString());

    }

    public <cls> Object toObj(String json, Class cls){
        return ((cls) gson.fromJson(json, cls));

    }
}
