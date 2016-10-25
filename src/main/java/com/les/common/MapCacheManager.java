package com.les.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

    private volatile long updateTime = 0L;// 更新缓存时记录的时间

    private volatile boolean updateFlag = true;// 正在更新时的阀门，为false时表示当前没有更新缓存，为true时表示当前正在更新缓存

    private static Map<String, String> cacheMap = new ConcurrentHashMap<>();// 缓存容器

    private MapCacheManager() {
        this.loadCache();// 加载缓存
        updateTime = System.currentTimeMillis();// 缓存更新时间
    }

    /**
     * 采用单例模式获取缓存对象实例
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
     * 初始化缓存
     */
    private void loadCache() {
        this.updateFlag = true;// 正在更新
        this.updateFlag = false;// 更新已完成
    }

    /**
     * 更新缓存
     */
    public void updateCache(String key, String value) {
        Set<Map.Entry<String, String>> entrySet = cacheMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet){
            if(entry.getValue().equals(value)){
                cacheMap.remove(entry.getKey());
            }
        }
        cacheMap.put(key, value);
    }

    /**
     * 返回缓存对象
     * @return
     */
    public Map<String, String> getMapCache() {
        long currentTime = System.currentTimeMillis();

        if (this.updateFlag) {// 前缓存正在更新
            log.info("cache is Instance .....");
            return null;
        }
        if (this.isTimeOut(currentTime)) {// 如果当前缓存正在更新或者缓存超出时限，需重新加载
            synchronized (this) {
                this.ReLoadCache();
                this.updateTime = currentTime;
            }
        }
        return this.cacheMap;
    }
    private boolean isTimeOut(long currentTime) {
        if((currentTime - this.updateTime) > 3000000){
            log.error("已超时，需重新登陆");
        }
        return ((currentTime - this.updateTime) > 3000000);// 超过时限，超时
    }
    /**
     * 重新装载
     */
    private void ReLoadCache() {
        this.cacheMap.clear();
        this.loadCache();
    }
}
