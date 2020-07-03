var storage = window.localStorage;

/**
 * 把数据存放到 localStorage
 * @param data 要保存的数据
 * @param expireTime 保存时长,单位: 秒
 * @param key 保存的 key ,用于获取数据
 */
function saveToStorage(data, expireTime, key) {
    storage.setItem(key, JSON.stringify(
        {data: data, time: new Date().getTime(), expireTime: expireTime}))
}

/**
 * 从 localStorage 获取数据
 * @param key
 */
function getFromStorage(key) {
    var dataObj = storage.getItem(key);
    if ((dataObj == null) || (dataObj === '') || (dataObj === undefined)) {
        // 如果没有查询到数据,说明没有存储
        return null;
    } else {
        var dataObjJson = JSON.parse(dataObj);
        if (new Date().getTime() - dataObjJson.time > dataObjJson.expireTime*1000) {
            // 缓存过期
            // 移除之前存储的数据
            storage.removeItem(key);
            return null;
        } else {
            return dataObjJson.data;
        }
    }
}