

/**
 * 格式化数字,把数字转化为每三位一个逗号的字符串
 * @param num
 * @returns {string|*}
 */
function formatNum(num) {
    if(!/^(\+|-)?(\d+)(\.\d+)?$/.test(num)) {
        layer.msg("系统偷懒啦,请稍后再试", { icon: 5 })
        return num;
    }
    var a = RegExp.$1,
        b = RegExp.$2,
        c = RegExp.$3;
    var re = new RegExp().compile("(\\d)(\\d{3})(,|$)");
    while(re.test(b)) {
        b = b.replace(re, "$1,$2$3");
    }
    return a + "" + b + "" + c;
}

/**
 * 转化数字 1000->1千 10000-1万
 * @param num
 * @returns {string|*}
 */
function parseNum(num) {
    if(!/^(\+|-)?(\d+)(\.\d+)?$/.test(num)) {
        layer.msg("系统偷懒啦,请稍后再试", { icon: 5 })
        return num;
    }
    if (num / 10000 >= 1) {
        return (num / 10000).toFixed(2) + '万';
    } else if (num / 1000 >= 1) {
        return (num / 1000).toFixed(2) + '千';
    }
    return num;
}

/**
 * 获取比例
 * @param num 部分的数据
 * @param all 全部数据
 * @returns {number} 0-100之间的数字
 */
function getRatio(num, all) {
    if (all == 0) {
        return 0;
    }
    return (num / all * 100).toFixed(2);
}

/**
 * 获取几天前的日期
 * @returns {{month: number, year: number, day: number}}
 */
function getPreDate(preDay) {
    var time = (new Date).getTime()-24*60*60*1000*preDay;
    var yesterday = new Date(time);

    var year = yesterday.getFullYear();
    var month = yesterday.getMonth() + 1;
    var day = yesterday.getDate();

    return {"year": year, "month": month, "day": day};
}

/**
 * 获取几月前的日期
 * @returns {{month: number, year: number}}
 */
function getPreMonth(preMonth) {
    var time = (new Date).getTime();
    var date = new Date(time);

    var year = date.getFullYear();
    var month = date.getMonth() + 1;

    var m = month - preMonth;
    var i = 0;
    while (m <= 0) {
        m = 12 - (preMonth - month - 12 * i);
        i++;
    }
    var y = year - i;

    return {"year": y, "month": m};
}

/**
 * 获取前7天的日期
 * @returns {{dateArr: [], dateArrNumStr: []}}
 */
function getPre7BetweenDate() {
    var dateArr = [];
    var dateArrNumStr = [];
    var dateYMDStr = [];
    for (var i = 7; i >= 1; i--) {
        var date = getPreDate(i);
        dateArr.push(date.year+"年"+date.month+"月"+date.day+"日");
        var month = date.month > 9 ? date.month : "0"+date.month
        var day = date.day > 9 ? date.day : "0"+date.day;
        dateArrNumStr.push(date.year+month+day);
        dateYMDStr.push(date.year+'-'+month+'-'+day);
    }
    return {"dateArr": dateArr, "dateArrNumStr": dateArrNumStr, "dateYMDStr": dateYMDStr};
}

/**
 * 获取前7月的日期
 * @returns {{dateArr: [], dateArrNumStr: []}}
 */
function getPre7BetweenMonth() {
    var dateArr = [];
    var dateArrNumStr = [];
    for (var i = 7; i >= 1; i--) {
        var date = getPreMonth(i);
        dateArr.push(date.year+"年"+date.month+"月");
        var month = date.month > 9 ? date.month : "0"+date.month
        dateArrNumStr.push(date.year+month);
    }
    return {"dateArr": dateArr, "dateArrNumStr": dateArrNumStr};
}

