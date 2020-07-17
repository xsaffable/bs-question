

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

        element.render('progress');

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
                            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
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
                            data: [900, 850, 950, 1000, 1100, 1050, 1000, 1150, 1250, 1370, 1250, 1100]
                        },
                        {
                            name: '问卷新增量',
                            type: 'line',
                            yAxisIndex: 1,
                            data: [850, 850, 800, 950, 1000, 950, 950, 1150, 1100, 1240, 1000, 950]
                        },
                        {
                            name: '问卷填写人次',
                            type: 'line',
                            data: [870, 850, 850, 950, 1050, 1000, 980, 1150, 1000, 1300, 1150, 1000]
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
                    data:['直接访问', '邮件营销','联盟广告','视频广告','搜索引擎']
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
                    {
                        name:'直接访问',
                        type:'bar',
                        stack: '总量',
                        itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
                        data:[320, 302, 301, 334, 390, 330, 320]
                    },
                    {
                        name:'邮件营销',
                        type:'bar',
                        stack: '总量',
                        itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
                        data:[120, 132, 101, 134, 90, 230, 210]
                    },
                    {
                        name:'联盟广告',
                        type:'bar',
                        stack: '总量',
                        itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
                        data:[220, 182, 191, 234, 290, 330, 310]
                    },
                    {
                        name:'视频广告',
                        type:'bar',
                        stack: '总量',
                        itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
                        data:[150, 212, 201, 154, 190, 330, 410]
                    },
                    {
                        name:'搜索引擎',
                        type:'bar',
                        stack: '总量',
                        itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
                        data:[820, 832, 901, 934, 1290, 1330, 1320]
                    }
                ]
            }
        ],
        elemheapbar = $('#LAY-index-heapbar').children('div'),
        renderheapbar = function(index){
            echheapbar[index] = echarts.init(elemheapbar[index], layui.echartsTheme);
            echheapbar[index].setOption(heapbar[index]);
            window.onresize = echheapbar[index].resize;
        };
        if(!elemheapbar[0]) return;
        renderheapbar(0);

    });

    exports('console', {})

});