import com.book.spring.chapter2.config.CDPlayerConfig;
import com.book.spring.chapter2.ifs.CompactDisc;
import com.book.spring.chapter2.ifs.MediaPlayer;
import com.book.spring.chapter3.config.BeanConfig;
import com.book.spring.chapter3.ifs.Cold;
import com.book.spring.chapter3.ifs.Creamy;
import com.book.spring.chapter3.ifs.Dessert;
import javax.inject.Inject;
import javax.inject.Named;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhangyoubao
 * @version 2021/4/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CDPlayerConfig.class, BeanConfig.class})
@ActiveProfiles("dev")
public class CDPlayerTest {

    @Inject
    @Named(value = "sgtPeppers")
    private CompactDisc cd;

    @Inject
    private MediaPlayer player;

    private Dessert dessert;

    @Autowired
    @Cold
    @Creamy
    public void setDessert(Dessert dessert) {
        this.dessert = dessert;
    }

    @Test
    public void cdShouldNotBeNull() {
        Assert.assertNotNull(cd);
        cd.play();
    }

    @Test
    public void play() {
        player.play();
    }

    @Test
    public void test() {
        System.out.println(dessert.toString());
    }
}
