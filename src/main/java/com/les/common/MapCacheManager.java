package com.les.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: MapCache
 * @Description:
 * @author Lydia
 * @date 2016/9/7 15:27
 */
public class MapCacheManager {
    private final static Log log = LogFactory.getLog(MapCacheManager.class);

    private volatile static MapCacheManager mapCacheObject;// 缓存实例对象

    private static Map<String, String> cacheMap = new ConcurrentHashMap<>();// 缓存容器 key=token value=userId_loginTime

    private MapCacheManager() {
    }

    /**
     * 采用单例模式获取缓存对象实例
     *
     * @return
     */
    public static MapCacheManager getInstance() {
        if (null == mapCacheObject) {
            synchronized (MapCacheManager.class) {
                if (null == mapCacheObject) {
                    mapCacheObject = new MapCacheManager();
                }
            }
        }
        return mapCacheObject;
    }

    /**
     * 更新缓存
     */
    public void updateCache(String key, String value) {
        Set<Map.Entry<String, String>> entrySet = cacheMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            if (entry.getValue().equals(value)) {
                cacheMap.remove(entry.getKey());
            }
        }
        String tmpValue = value + "_" + System.currentTimeMillis();
        cacheMap.put(key, tmpValue);
        log.info("用户[" + value + "]已登录");
    }

    /**
     * 删除某个用户登陆缓存
     */
    public void removeCache(String key) {
        cacheMap.remove(key);
    }

    /**
     * 返回缓存对象
     *
     * @return
     */
    public Map<String, String> getMapCache(String token) {
        long currentTime = System.currentTimeMillis();
        Map<String, String> retMap = new HashMap<>();
        if (!cacheMap.containsKey(token)) {
            retMap.put("userId", null);
        } else {
            String tmpValue = cacheMap.get(token);
            String userId = tmpValue.split("_")[0];
            Long loginTime = Long.parseLong(tmpValue.split("_")[1]);
            if (this.isTimeOut(loginTime, currentTime)) {// 如果当前缓存正在更新或者缓存超出时限，需重新加载
                log.error("已超时，需重新登陆");
                synchronized (this) {
                    this.removeCache(token);
                }
                retMap.put("userId", null);
            } else {
                retMap.put("userId", userId);
            }
        }
        return retMap;
    }

    private boolean isTimeOut(long loginTime, long currentTime) {
        return ((currentTime - loginTime) > 2 * 60 * 60 * 1000);// 超过时限 2 小时，超时单位毫秒ms
    }
}
