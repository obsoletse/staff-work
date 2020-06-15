import org.junit.jupiter.api.Test;
import com.linbin.utils.VerifyCodeUtils;
import com.linbin.entity.VerifyImg;

import java.io.IOException;

public class test {
    @Test
    public void test() throws IOException {
        VerifyImg rs = VerifyCodeUtils.VerifyCode(40,20,4);
        System.out.println(rs.getImg());
        System.out.println(rs.getCode());

    }
}
