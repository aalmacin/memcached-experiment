import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MemcachedExperiment {
    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException, MemcachedException {
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(
                AddrUtil.getAddresses("10.98.144.4:11211")
        );
        builder.setCommandFactory(new BinaryCommandFactory());
        builder.setConnectTimeout(1000);
        builder.setEnableHealSession(true);
        builder.setHealSessionInterval(1000);

        MemcachedClient mc = builder.build();

        mc.set("foo", 0, "bar");
        String val = mc.get("foo");
        System.out.println(val);
    }
}
