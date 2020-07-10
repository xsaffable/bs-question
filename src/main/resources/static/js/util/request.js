
// url 前缀
var urlPrefix = "";

// 请求个数，当全部请求完毕，关闭加载动画
var reqNum = 1;

// 加载动画图层的 index
var indexList = [];

/**
 * 异步请求工具方法
 * @param {string} url 请求的url --------------------- 必填
 * @param {Object} jsonData 请求的数据 --------------- 必填
 * @param {function} sucFunc 请求成功的回调函数 ------- 必填
 * @param {string} reqType  请求的类型,默认为 post
 * @param {function} errFunc 请求失败的回调函数
 * @param {function} compFunc 请求完成的回调函数
 * @param {function} beforeFunc 请求之前的回调函数
 */
function requestAsync(url, jsonData, sucFunc, reqType, errFunc, compFunc, beforeFunc) {
	url = urlPrefix + url;

	// 默认为 post 请求
	if ((reqType == undefined) || (reqType == null) || (reqType == "")) {
		reqType = "POST";
	}

	// 设置响应失败默认回调函数
	if ((errFunc == undefined) || (errFunc == null) || (errFunc == "")) {
		errFunc = function (XMLHttpRequest, textStatus, errorThrown) {
			layer.msg("系统偷懒啦，请稍后再试", { icon: 5 });
		};
	}

	// 设置默认回调函数
	if ((compFunc == undefined) || (compFunc == null) || (compFunc == "")) {
		compFunc = function(XMLHttpRequest, textStatus) {
			reqNum--;
			if (reqNum == 0) {
				for (var i in indexList) {
					layer.close(indexList[i]);
				}
			}
		};
	}

	if ((beforeFunc == undefined) || (beforeFunc == null) || (beforeFunc == "")) {
		beforeFunc = function () {
			var index = layer.load(3, {shade: [0.1, '#393D49'], time: 20*1000});
			indexList.push(index);
		};
	}


	// 发起异步请求
	$.ajax({
		url: url,
		type: reqType,
		dataType: "json",
		contentType: "application/json",
		async: true,
		data: jsonData,
		success: function (data) {
			if (data.code === 0) {
				sucFunc(data);
			} else {
				layer.msg(data.msg || "系统偷懒啦，请稍后再试", { icon: 5 });
			}
		},
		complete: compFunc,
		error: errFunc,
		beforeSend: beforeFunc
	});

}
