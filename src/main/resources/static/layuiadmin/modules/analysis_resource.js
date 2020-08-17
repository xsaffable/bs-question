/**
 * 数据分析模块
 */
layui.define(function(exports){


    //区块轮播切换
    layui.use(['admin', 'carousel'], function(){
        var $ = layui.$
            ,admin = layui.admin
            ,carousel = layui.carousel
            ,element = layui.element
            ,device = layui.device();

        //轮播切换
        $('.layadmin-carousel').each(function(){
            var othis = $(this);
            carousel.render({
                elem: this
                ,width: '100%'
                ,arrow: 'none'
                ,interval: othis.data('interval')
                ,autoplay: othis.data('autoplay') === true
                ,trigger: (device.ios || device.android) ? 'click' : 'hover'
                ,anim: othis.data('anim')
            });
        });

        element.render('progress');

    });

    // 渲染图形
    layui.use(['carousel'], function(){
        var $ = layui.$
            ,carousel = layui.carousel;


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
        function getPreHour(preHour) {
            var time = (new Date).getTime()-60*60*1000*preHour;
            var preHour = new Date(time);
            var day = preHour.getDate();
            var hour = preHour.getHours();

            return {"day": day, "hour": hour};
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
        function getPre12BetweenHour() {
            var dateArr = [];
            var dateArrNumStr = [];
            for (var i = 12; i >= 1; i--) {
                var date = getPreHour(i);
                dateArr.push(date.day+"日"+date.hour+"时");
                var day = date.day > 9 ? date.day : "0"+date.day;
                var hour = date.hour > 9 ? date.hour : "0"+date.hour;
                dateArrNumStr.push(day+hour);
            }
            return {"dateArr": dateArr, "dateArrNumStr": dateArrNumStr};
        }

        requestAsync(systemUseRateUrl, null, function (data) {
            var cpus = [];
            var mems = [];

            for (var i in data.data) {
                var d = data.data[i];
                if (d == null) {
                    cpus.push(0);
                    mems.push(0);
                } else {
                    cpus.push(d['cpuRate']);
                    mems.push(d['memoryRate']);
                }

            }

            // cpu 使用率
            var cpu_rate_option = {
                xAxis: {
                    type: 'category',
                    data: getPre12BetweenHour()["dateArr"]
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: cpus,
                    type: 'line',
                    smooth: true
                }]
            };
            var cpu_rate = $('#cpu_rate').children('div');
            if(cpu_rate[0]) {
                var myChart = echarts.init(cpu_rate[0]);
                myChart.setOption(cpu_rate_option);
                window.addEventListener("resize", function() {
                    myChart.resize();
                });
            }

            // 内存使用率
            var memory_rate_option = {
                xAxis: {
                    type: 'category',
                    data: getPre12BetweenHour()["dateArr"]
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: mems,
                    type: 'line',
                    smooth: true
                }]
            };
            var memory_rate = $('#memory_rate').children('div');
            if(memory_rate[0]) {
                var myChart = echarts.init(memory_rate[0]);
                myChart.setOption(memory_rate_option);
                window.addEventListener("resize", function() {
                    myChart.resize();
                });
            }

        });



    });

    exports('analysis_resource', {})

});