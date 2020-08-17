

layui.define(function(exports) {
    var admin = layui.admin;

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

        var quesIncRateUrl = '/index/questionnaire_inc_rate';
        var visitIncRateUrl = '/index/visit_inc_rate';
        var questionnaireIncRateUrl = '/index/questionnaire_edit_inc_rate';

        requestAsync(quesIncRateUrl, null, function (data) {
            $('#ques_inc_rate').attr('lay-percent', data.data+'%');
            requestAsync(visitIncRateUrl, null, function (data) {
                $('#visit_inc_rate').attr('lay-percent', data.data+'%');
                requestAsync(questionnaireIncRateUrl, null, function (data) {
                    $('#ques_edit_inc_rate').attr('lay-percent', data.data+'%');
                    element.render('progress');
                });
            });
        });


    });

    //访问量
    layui.use(['carousel', 'echarts'], function () {
        var $ = layui.$,
            carousel = layui.carousel,
            echarts = layui.echarts;

        var echartsApp = [], options = [
                {
                    tooltip: {
                        trigger: 'axis'
                    },
                    calculable: true,
                    legend: {
                        data: ['访问量', '问卷新增量', '问卷填写人次']
                    },

                    xAxis: [
                        {
                            type: 'category',
                            data: []
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: '访问量',
                            axisLabel: {
                                formatter: '{value} 万'
                            }
                        },
                        {
                            type: 'value',
                            name: '问卷新增量',
                            axisLabel: {
                                formatter: '{value} 万'
                            }
                        }
                    ],
                    series: [
                        {
                            name: '访问量',
                            type: 'line',
                            data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
                        },
                        {
                            name: '问卷新增量',
                            type: 'line',
                            yAxisIndex: 1,
                            data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
                        },
                        {
                            name: '问卷填写人次',
                            type: 'line',
                            data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
                        }
                    ]
                }
            ],
            elemDataView = $('#LAY-index-pagetwo').children('div'),
            renderDataView = function (index) {
                echartsApp[index] = echarts.init(elemDataView[index], layui.echartsTheme);
                echartsApp[index].setOption(options[index]);
                window.onresize = echartsApp[index].resize;
            };

        var visitCountMonthUrl = "/index/visit_count_month";
        var quesCountMonthUrl = "/index/questionnaire_count_month";
        // 每月问卷填写量
        var questionnaireEditCountMonthUrl = '/index/questionnaire_edit_count_month';

        var months = []
        var date = new Date();
        var month = date.getMonth();
        var i = month;
        do {
            months.push(getPreMonth(i--).month+'月');
        } while (i >= 0);
        options[0]['xAxis'][0].data = months;
        // 访问量
        requestAsync(visitCountMonthUrl, null, function (data) {
            options[0]['series'][0].data = data.data;
            requestAsync(quesCountMonthUrl, null, function (data) {
                options[0]['series'][1].data = data.data;
                requestAsync(questionnaireEditCountMonthUrl, null, function (data) {
                    options[0]['series'][2].data = data.data;
                    echartsApp[0].setOption(options[0]);
                })

            })
        });


        //没找到DOM，终止执行
        if (!elemDataView[0]) return;
        renderDataView(0);


        //堆积条形图
        var echheapbar = [], heapbar = [
            {
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                legend: {
                    data:[]
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'value'
                    }
                ],
                yAxis : [
                    {
                        type : 'category',
                        data : ['7月8日','7月9日','7月10日','7月11日','7月12日','7月13日','7月14日']
                    }
                ],
                series : [
                    // {
                    //     name:'',
                    //     type:'bar',
                    //     stack: '总量',
                    //     itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
                    //     data:[]
                    // },
                    // {
                    //     name:'',
                    //     type:'bar',
                    //     stack: '总量',
                    //     itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
                    //     data:[]
                    // },
                    // {
                    //     name:'',
                    //     type:'bar',
                    //     stack: '总量',
                    //     itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
                    //     data:[]
                    // },
                    // {
                    //     name:'',
                    //     type:'bar',
                    //     stack: '总量',
                    //     itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
                    //     data:[]
                    // }
                ]
            }
        ],
        elemheapbar = $('#LAY-index-heapbar').children('div'),
        renderheapbar = function(index){
            echheapbar[index] = echarts.init(elemheapbar[index], layui.echartsTheme);
            // echheapbar[index].setOption(heapbar[index]);
            window.onresize = echheapbar[index].resize;
        };
        if(!elemheapbar[0]) return;
        renderheapbar(0);

        var pre7BetweenDate = getPre7BetweenDate();
        var dateArr = pre7BetweenDate.dateArr;
        var dateYMDStr = pre7BetweenDate.dateYMDStr;
        var dates = [];
        for (var i in dateArr) {
            dates.push(dateArr[i].substring(5));
        }
        requestAsync('/index/all_qt', null, function (data) {
            heapbar[0]['legend']['data'] = data.data;
            heapbar[0]['yAxis'][0]['data'] = dates;
            var heads = [];
            requestAsync('/index/dtcounts', JSON.stringify({"startTime": dateYMDStr[0], "endTime": dateYMDStr[dateYMDStr.length-1]}), function (data2) {
                var types = data.data;
                for (var i in types) {
                    var type = types[i];
                    var r = data2.data[type];
                    if (r != undefined) {
                        heapbar[0]['series'].push({
                            name: type,
                            type: 'bar',
                            stack: '总量',
                            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
                            data: r
                        });
                        heads.push(type);
                    }

                }
                echheapbar[0].setOption(heapbar[0]);
            })
        });

    });

    exports('console', {})

});