import com.book.spring.chapter4.aspect.TrackCounter;
import com.book.spring.chapter4.config.BeanConfig;
import com.book.spring.chapter4.config.TrackCounterConfig;
import com.book.spring.chapter4.ifs.CompactDisc;
import javax.inject.Inject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhangyoubao
 * @version 2021/4/9
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BeanConfig.class, TrackCounterConfig.class})
public class AspectTest {

    @Inject
    private CompactDisc cd;

    @Inject
    private TrackCounter counter;

    @Test
    public void test() {
        cd.playTrack(1);
        cd.playTrack(2);
        cd.playTrack(3);
        cd.playTrack(3);
        cd.playTrack(3);
        cd.playTrack(4);
        cd.playTrack(4);

        Assert.assertEquals(1, counter.getPlayCount(1));
        Assert.assertEquals(1, counter.getPlayCount(2));
        Assert.assertEquals(3, counter.getPlayCount(3));
        Assert.assertEquals(2, counter.getPlayCount(4));
    }

}
