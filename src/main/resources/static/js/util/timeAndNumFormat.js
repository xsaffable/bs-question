

/**
 * 处理数据中的 "null"
 * @param dArr 待处理数据
 */
function deal_null(dArr) {
    for (var i in dArr) {
        var d = dArr[i];
        for (var k in d) {
            if (d[k] == "null") {
                d[k] = "【数据格式有误！】";
            }
        }
    }
    return dArr;
}

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
 * 获取比例
 * @param num 部分的数据
 * @param all 全部数据
 * @returns {number} 0-100之间的数字
 */
function getRatio(num, all) {
    if (all == 0) {
        return 0;
    }
    return (num / all).toFixed(2) * 100;
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
    for (var i = 7; i >= 1; i--) {
        var date = getPreDate(i);
        dateArr.push(date.year+"年"+date.month+"月"+date.day+"日");
        var month = date.month > 9 ? date.month : "0"+date.month
        var day = date.day > 9 ? date.day : "0"+date.day;
        dateArrNumStr.push(date.year+month+day);
    }
    return {"dateArr": dateArr, "dateArrNumStr": dateArrNumStr};
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

/**
 * 转化时间，把毫秒转化为时间字符串
 * @param dateLong
 * @returns {string}
 */
function getFormatDate(dateLong) {
    var date = new Date(dateLong);
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    month = month > 9 ? month : "0"+month;
    day = day > 9 ? day : "0"+day;

    return year + month + day;

}

/**
 * 转化时间，把毫秒转化为时间字符串
 * @param dateLong
 * @returns {string}
 */
function getFormatTime(dateLong) {
    try {
        var date = new Date(dateLong);
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        var hour = date.getHours();
        var minute = date.getMinutes();
        var second= date.getSeconds();
        month = month > 9 ? month : "0"+month;
        day = day > 9 ? day : "0"+day;
        hour = hour > 9 ? hour : "0"+hour;
        minute = minute > 9 ? minute : "0"+minute;
        second = second > 9 ? second : "0"+second;

        return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
    } catch (err) {
        console.log(err)
        return "【数据格式有误！】";
    }

}