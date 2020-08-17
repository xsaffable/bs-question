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

        requestAsync(countGroupByTypeUrl, null, function (data) {
            // 问卷类别分布
            var qus_type_option = {
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b} : {c} ({d}%)'
                },
                legend: {
                    type: 'scroll',
                    orient: 'vertical',
                    right: 10,
                    top: 20,
                    bottom: 20,
                    // data: data.legendData,
                    //
                    // selected: data.selected
                },
                series: [
                    {
                        name: '类别',
                        type: 'pie',
                        radius: '55%',
                        center: ['40%', '50%'],
                        data: data.data,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            var qus_type = $('#qus_type').children('div');
            if(qus_type[0]) {
                var myChart = echarts.init(qus_type[0]);
                myChart.setOption(qus_type_option);
                window.addEventListener("resize", function() {
                    myChart.resize();
                });
            }
        });


        // 热点问卷排行
        var qus_hotspot_option = {
            dataset: {
                source: [
                    ['人次', '问卷']
                ]
            },
            grid: {containLabel: true},
            xAxis: {name: '人次'},
            yAxis: {type: 'category'},
            visualMap: {
                orient: 'horizontal',
                left: 'center',
                min: 1,
                max: 150,
                text: ['多', '少'],
                // Map the score column to color
                dimension: 0,
                inRange: {
                    color: ['#D7DA8B', '#E15457']
                }
            },
            series: [
                {
                    type: 'bar',
                    encode: {
                        // Map the "amount" column to X axis.
                        x: '人次',
                        // Map the "product" column to Y axis
                        y: '问卷'
                    }
                }
            ]
        };
        requestAsync(countUQGroupByTitleUrl, null, function (data) {
            for (var i in data.data) {
                var d = data.data[i];
                qus_hotspot_option['dataset']['source'].push([d['count'], d['title']]);
            }
            var qus_hotspot = $('#qus_hotspot').children('div');
            if(qus_hotspot[0]) {
                var myChart = echarts.init(qus_hotspot[0]);
                myChart.setOption(qus_hotspot_option);
                window.addEventListener("resize", function() {
                    myChart.resize();
                });
            }
        });


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
        // 问卷量
        requestAsync(questionnaireCountDaysUrl, null, function (data) {

            var qus_count_option = {
                xAxis: {
                    type: 'category',
                    data: getPre7BetweenDate()["dateArr"]
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: data.data,
                    type: 'line',
                    smooth: true
                }]
            };
            var qus_count = $('#qus_count').children('div');
            if(qus_count[0]) {
                var myChart = echarts.init(qus_count[0]);
                myChart.setOption(qus_count_option);
                window.addEventListener("resize", function() {
                    myChart.resize();
                });
            }
        });













        // // 近七天用户量
        // var user_count_option = {
        //     xAxis: {
        //         type: 'category',
        //         data: getPre7BetweenDate()["dateArr"]
        //     },
        //     yAxis: {
        //         type: 'value'
        //     },
        //     series: [{
        //         data: [820, 932, 901, 934, 1290, 1330, 1320],
        //         type: 'line',
        //         smooth: true
        //     }]
        // };
        // var user_count = $('#user_count').children('div');
        // if(user_count[0]) {
        //     var myChart = echarts.init(user_count[0]);
        //     myChart.setOption(user_count_option);
        //     window.addEventListener("resize", function() {
        //         myChart.resize();
        //     });
        // }
        //
        // // 用户喜爱度
        // var user_prefer_option = {
        //     tooltip: {},
        //     radar: {
        //         // shape: 'circle',
        //         name: {
        //             textStyle: {
        //                 color: '#fff',
        //                 backgroundColor: '#999',
        //                 borderRadius: 3,
        //                 padding: [3, 5]
        //             }
        //         },
        //         indicator: [
        //             { name: '销售（sales）', max: 6500},
        //             { name: '管理（Administration）', max: 16000},
        //             { name: '信息技术（Information Techology）', max: 30000},
        //             { name: '客服（Customer Support）', max: 38000},
        //             { name: '研发（Development）', max: 52000},
        //             { name: '市场（Marketing）', max: 25000}
        //         ]
        //     },
        //     series: [{
        //         type: 'radar',
        //         // areaStyle: {normal: {}},
        //         data: [
        //             {
        //                 value: [4300, 10000, 28000, 35000, 50000, 19000],
        //             }
        //         ]
        //     }]
        // };
        // var user_prefer = $('#user_prefer').children('div');
        // if(user_prefer[0]) {
        //     var myChart = echarts.init(user_prefer[0]);
        //     myChart.setOption(user_prefer_option);
        //     window.addEventListener("resize", function() {
        //         myChart.resize();
        //     });
        // }
        //
        // // 用户登录次数排行
        // var user_login_rank_option = {
        //     dataset: {
        //         source: [
        //             ['人次', '问卷'],
        //             [2, '最喜欢的电影'],
        //             [5, 'Milk Tea'],
        //             [6, 'Cheese Cocoa'],
        //             [10, 'Cheese Brownie'],
        //             [11, 'Matcha Cocoa'],
        //             [12, 'Tea'],
        //             [13, 'Orange Juice'],
        //             [50, 'Lemon Juice'],
        //             [80, 'Walnut Brownie'],
        //             [100, '最喜欢的运动']
        //         ]
        //     },
        //     grid: {containLabel: true},
        //     xAxis: {name: '人次'},
        //     yAxis: {type: 'category'},
        //     visualMap: {
        //         orient: 'horizontal',
        //         left: 'center',
        //         min: 1,
        //         max: 150,
        //         text: ['多', '少'],
        //         // Map the score column to color
        //         dimension: 0,
        //         inRange: {
        //             color: ['#D7DA8B', '#E15457']
        //         }
        //     },
        //     series: [
        //         {
        //             type: 'bar',
        //             encode: {
        //                 // Map the "amount" column to X axis.
        //                 x: '人次',
        //                 // Map the "product" column to Y axis
        //                 y: '问卷'
        //             }
        //         }
        //     ]
        // };
        // var user_login_rank = $('#user_login_rank').children('div');
        // if(user_login_rank[0]) {
        //     var myChart = echarts.init(user_login_rank[0]);
        //     myChart.setOption(user_login_rank_option);
        //     window.addEventListener("resize", function() {
        //         myChart.resize();
        //     });
        // }
        //
        // // 用户填写问卷次数排行
        // var user_qus_rank_option = {
        //     dataset: {
        //         source: [
        //             ['人次', '问卷'],
        //             [2, '最喜欢的电影'],
        //             [5, 'Milk Tea'],
        //             [6, 'Cheese Cocoa'],
        //             [10, 'Cheese Brownie'],
        //             [11, 'Matcha Cocoa'],
        //             [12, 'Tea'],
        //             [13, 'Orange Juice'],
        //             [50, 'Lemon Juice'],
        //             [80, 'Walnut Brownie'],
        //             [100, '最喜欢的运动']
        //         ]
        //     },
        //     grid: {containLabel: true},
        //     xAxis: {name: '人次'},
        //     yAxis: {type: 'category'},
        //     visualMap: {
        //         orient: 'horizontal',
        //         left: 'center',
        //         min: 1,
        //         max: 150,
        //         text: ['多', '少'],
        //         // Map the score column to color
        //         dimension: 0,
        //         inRange: {
        //             color: ['#D7DA8B', '#E15457']
        //         }
        //     },
        //     series: [
        //         {
        //             type: 'bar',
        //             encode: {
        //                 // Map the "amount" column to X axis.
        //                 x: '人次',
        //                 // Map the "product" column to Y axis
        //                 y: '问卷'
        //             }
        //         }
        //     ]
        // };
        // var user_qus_rank = $('#user_qus_rank').children('div');
        // if(user_qus_rank[0]) {
        //     var myChart = echarts.init(user_qus_rank[0]);
        //     myChart.setOption(user_qus_rank_option);
        //     window.addEventListener("resize", function() {
        //         myChart.resize();
        //     });
        // }
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        //
        // // cpu 使用率
        // var cpu_rate_option = {
        //     xAxis: {
        //         type: 'category',
        //         data: getPre12BetweenHour()["dateArr"]
        //     },
        //     yAxis: {
        //         type: 'value'
        //     },
        //     series: [{
        //         data: [820, 932, 901, 934, 1290, 1330, 1320],
        //         type: 'line',
        //         smooth: true
        //     }]
        // };
        // var cpu_rate = $('#cpu_rate').children('div');
        // if(cpu_rate[0]) {
        //     var myChart = echarts.init(cpu_rate[0]);
        //     myChart.setOption(cpu_rate_option);
        //     window.addEventListener("resize", function() {
        //         myChart.resize();
        //     });
        // }
        //
        // // 内存使用率
        // var memory_rate_option = {
        //     xAxis: {
        //         type: 'category',
        //         data: getPre12BetweenHour()["dateArr"]
        //     },
        //     yAxis: {
        //         type: 'value'
        //     },
        //     series: [{
        //         data: [820, 932, 901, 934, 1290, 1330, 1320],
        //         type: 'line',
        //         smooth: true
        //     }]
        // };
        // var memory_rate = $('#memory_rate').children('div');
        // if(memory_rate[0]) {
        //     var myChart = echarts.init(memory_rate[0]);
        //     myChart.setOption(memory_rate_option);
        //     window.addEventListener("resize", function() {
        //         myChart.resize();
        //     });
        // }

    });

    exports('analysis', {})

});