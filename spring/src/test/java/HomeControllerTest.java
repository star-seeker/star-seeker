import com.book.spring.chapter5.config.RootConfig;
import com.book.spring.chapter5.config.WebConfig;
import com.book.spring.chapter5.model.Spittle;
import com.book.spring.chapter5.web.HomeController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * @author zhangyoubao
 * @version 2021/4/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, RootConfig.class})
public class HomeControllerTest {

    @Test
    public void testHomePage() throws Exception {
        HomeController controller = new HomeController();
        MockMvc mockMvc = standaloneSetup(controller).build();  // 搭建MockMvc

        mockMvc.perform(get("/homepage"))    // 对"/"执行GET请求
                .andExpect(view().name("home"));  // 预期得到home视图
    }

    @Test
    public void shoudlShowRecentSpittles() {
        List<Spittle> expectedSpittles = createSpittleList(20);

    }

    private List<Spittle> createSpittleList(int count) {
        List<Spittle> spittles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            spittles.add(new Spittle("Spittle " + i, new Date()));
        }
        return spittles;
    }
}
