package com.les.common;

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

    private volatile static MapCacheManager mapCacheObject;// 缓存实例对象

    private static Map<String, String> cacheMap = new ConcurrentHashMap<>();// 缓存容器

    private MapCacheManager() {
        this.loadCache();// 加载缓存
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
     * 初始化缓存
     */
    private void loadCache() {
        /********** 数据处理，将数据放入cacheMap缓存中 **begin ******/
//        cacheMap.put("key1", "value1");
//        cacheMap.put("key2", "value2");
//        cacheMap.put("key3", "value3");
//        cacheMap.put("key4", "value4");
//        cacheMap.put("key5", "value5");
        /********** 数据处理，将数据放入cacheMap缓存中 ***end *******/
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
     *
     * @return
     */
    public Map<String, String> getMapCache() {
        return this.cacheMap;
    }
}
