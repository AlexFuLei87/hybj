package cn.hybj.util;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import java.util.Date;

public class WhFastJsonUtil {
    private static SerializeConfig configDatetime = new SerializeConfig();

    public WhFastJsonUtil() {
    }

    public static SerializeConfig getConfigDatetime() {
        return configDatetime;
    }

    static {
        configDatetime.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
    }
}
