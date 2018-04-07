package joins;

import definitions.cacheJoin;
import definitions.L1_TriggerRecord;
import definitions.L2_DPI;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteException;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.lang.IgniteBiPredicate;
import org.apache.ignite.lifecycle.LifecycleBean;
import org.apache.ignite.lifecycle.LifecycleEventType;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.springframework.cache.annotation.CacheConfig;

import javax.cache.CacheException;
import java.util.List;

public class StartUp implements LifecycleBean {

    @IgniteInstanceResource
    private Ignite ignite;

    @Override
    public void onLifecycleEvent(LifecycleEventType evt) throws IgniteException {
        if (evt == LifecycleEventType.AFTER_NODE_START) {
            IgniteCache<String, L1_TriggerRecord> IGNITE_CACHE_OBJECT_L1_TRIGGERRECORD = ignite.cache("CACHE_L1_TRIGGER_RECORD");
            IgniteCache<String, L2_DPI> IGNITE_CACHE_OBJECT_L2_DPI = ignite.cache("CACHE_L2_DPI");

            try {
                IGNITE_CACHE_OBJECT_L1_TRIGGERRECORD.loadCache(new IgniteBiPredicate<String, L1_TriggerRecord>() {
                    @Override
                    public boolean apply(String s, L1_TriggerRecord l1_triggerRecord) {
                        System.out.println(s);
                        return false;
                    }
                });
                IGNITE_CACHE_OBJECT_L2_DPI.localLoadCache(null);
                System.out.println(IGNITE_CACHE_OBJECT_L1_TRIGGERRECORD.get("1"));
            } catch (CacheException e) {
                System.out.println(e.getMessage());
            }





            L1_TriggerRecord l1_record = new L1_TriggerRecord("1","1","ahmed_reda","2");
            IGNITE_CACHE_OBJECT_L1_TRIGGERRECORD.put("2",l1_record);

            L2_DPI l2_record = new L2_DPI("1", "1", "T", "123123", "123", "3213", "21312", "123", "2312", "213", "12312","123");
            IGNITE_CACHE_OBJECT_L2_DPI.put("1", l2_record);


            L2_DPI l3_record = new L2_DPI("1", "2", "T", "123123", "123", "3213", "21312", "123", "2312", "213", "12312","123");
            IGNITE_CACHE_OBJECT_L2_DPI.put("2", l3_record);

            System.out.println(IGNITE_CACHE_OBJECT_L1_TRIGGERRECORD.get("1"));

            System.out.println(IGNITE_CACHE_OBJECT_L2_DPI.get("1"));
            System.out.println(IGNITE_CACHE_OBJECT_L2_DPI.get("2"));


            // Execute query to get names of all employees.
            SqlFieldsQuery sql = new SqlFieldsQuery(
                    "select a.*, b.application from CACHE_L1_TRIGGER_RECORD.L1_TriggerRecord as a inner join CACHE_L2_DPI.L2_DPI as b on a.srcId = b.srcId where a.srcId = '1' and b.rat_type='123'");


            try (QueryCursor<List<?>> cursor = IGNITE_CACHE_OBJECT_L1_TRIGGERRECORD.query(sql.setDistributedJoins(true).setEnforceJoinOrder(true))){
                for (Object row : cursor)
                    System.out.println(row);
            }


//            while (true) {
//                try {
//                    System.out.println(IGNITE_CACHE_OBJECT_L1_TRIGGERRECORD.get("magdi"));
//                } catch (Exception ex){}
//            }


            }
    }
}
