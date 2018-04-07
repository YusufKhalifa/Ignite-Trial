package joins;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;

import java.util.List;

public class JoinCache {
    public static void main(String[] args) {
        Ignite ignite = Ignition.start("config/example-default.xml");
    }
}
