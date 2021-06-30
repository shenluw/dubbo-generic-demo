package demo;

import java.util.List;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author shenluw
 * @date 2021/6/30 20:02
 */
@DubboService
public class BiServiceImpl implements BiService {

  @Override
  public Integer work(String name, List<Integer> val) {
    System.out.println("work " + name);
    return val.size();
  }
}
