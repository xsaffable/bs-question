/**
 * 基础信息管理
 */
layui.define(['table', 'form'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , form = layui.form;

    //分类管理
    table.render({
        elem: '#LAY-app-content-tags'
        , url: layui.setter.base + 'json/content/tags.js' //模拟接口
        , cols: [[
            {type: 'numbers', fixed: 'left'}
            , {field: 'id', width: 100, title: 'ID', sort: true}
            , {field: 'tags', title: '分类名', minWidth: 100}
            , {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#layuiadmin-app-cont-tagsbar'}
        ]]
        , text: '对不起，加载出现异常！'
    });

    //监听工具条
    table.on('tool(LAY-app-content-tags)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确定删除此分类？', function (index) {
                obj.del();
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            var tr = $(obj.tr);
            layer.open({
                type: 2
                , title: '编辑分类'
                , content: '/basic_manage/qtype/form?id=' + data.id
                , area: ['450px', '200px']
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    //获取iframe元素的值
                    var othis = layero.find('iframe').contents().find("#layuiadmin-app-form-tags")
                        , tags = othis.find('input[name="tags"]').val();

                    if (!tags.replace(/\s/g, '')) return;

                    obj.update({
                        tags: tags
                    });
                    layer.close(index);
                }
                , success: function (layero, index) {
                    //给iframe元素赋值
                    var othis = layero.find('iframe').contents().find("#layuiadmin-app-form-tags").click();
                    othis.find('input[name="tags"]').val(data.tags);
                }
            });
        }
    });

    //文章管理
    table.render({
        elem: '#LAY-app-content-list'
        , url: layui.setter.base + 'json/content/list.json' //模拟接口
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', width: 100, title: '问卷ID', sort: true}
            , {field: 'label', title: '问卷分类', minWidth: 100}
            , {field: 'title', title: '问卷标题'}
            , {field: 'author', title: '作者'}
            , {field: 'uploadtime', title: '创建时间', sort: true}
            , {field: 'status', title: '发布状态', templet: '#buttonTpl', minWidth: 80, align: 'center'}
            , {title: '操作', minWidth: 150, align: 'center', fixed: 'right', toolbar: '#table-content-list'}
        ]]
        , page: true
        , limit: 10
        , limits: [10, 15, 20, 25, 30]
        , text: '对不起，加载出现异常！'
    });

    //监听工具条
    table.on('tool(LAY-app-content-list)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确定删除此问卷？', function (index) {
                obj.del();
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            var index = layer.open({
                type: 2
                , title: '编辑问卷'
                , content: '/basic_manage/q/edit?id=' + data.id
                , maxmin: true
                , area: ['100%', '100%']
                , btn: ['确定', '取消']
                , success: function (layero) {
                    var myIframe = window[layero.find('iframe')[0]['name']];
                    data =
                        {"title":"a",
                            "type":"1",
                            "status":1,
                            "questions":[{"title":"题目测试",
                                "queType": 'radio',
                                "ans":[{"ansTitle":"A", "ansVal":"答案测试A"},
                                    {"ansTitle":"B", "ansVal":"答案测试B"}]},
                                {"title":"题目测试2",
                                    "queType": 'checkbox',
                                    "ans":[{"ansTitle":"A", "ansVal":"答案测试2A"},
                                        {"ansTitle":"B", "ansVal":"答案测试2B"}]}]};
                    myIframe.init(data);
                }
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");

                    // //监听提交
                    // iframeWindow.layui.form.on('submit(layuiadmin-app-form-submit)', function (data) {
                    //     var field = data.field; //获取提交的字段
                    //
                    //     //提交 Ajax 成功后，静态更新表格中的数据
                    //     //$.ajax({});
                    //     obj.update({
                    //         label: field.label
                    //         , title: field.title
                    //         , author: field.author
                    //         , status: field.status
                    //     }); //数据更新
                    //
                    //     form.render();
                    //     layer.close(index); //关闭弹层
                    // });

                    submit.trigger('click');
                }
            });
            layer.full(index);
        } else if (obj.event === 'detail') {
            var index = layer.open({
                type: 2
                , title: '查看问卷'
                , content: '/basic_manage/q/detail?id=' + data.id
                , maxmin: true
                , area: ['100%', '100%']
                , btn: ['确定', '取消']
                , success: function (layero) {
                    var myIframe = window[layero.find('iframe')[0]['name']];
                    data =
                        {"title":"a",
                            "type":"1",
                            "status":1,
                            "questions":[{"title":"题目测试",
                                "queType": 'radio',
                                "ans":[{"ansTitle":"A", "ansVal":"答案测试A"},
                                    {"ansTitle":"B", "ansVal":"答案测试B"}]},
                                {"title":"题目测试3",
                                    "queType": 'radio',
                                    "ans":[{"ansTitle":"A", "ansVal":"答案测试3A"},
                                        {"ansTitle":"B", "ansVal":"答案测试3B"}]},
                                {"title":"题目测试2",
                                    "queType": 'checkbox',
                                    "ans":[{"ansTitle":"A", "ansVal":"答案测试2A"},
                                        {"ansTitle":"B", "ansVal":"答案测试2B"}]}]};
                    myIframe.init(data);
                }
                , yes: function (index, layero) {
                    var iframeWindow = window['layui-layer-iframe' + index]
                        , submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");

                    submit.trigger('click');
                }
            });
            layer.full(index);
        }
    });

    // 系统公告管理
    table.render({
        elem: '#LAY-app-forum-list'
        ,url: layui.setter.base + 'json/forum/list.js' //模拟接口
        ,cols: [[
            {type: 'checkbox', fixed: 'left'}
            ,{field: 'id', width: 100, title: 'ID', sort: true}
            ,{field: 'poster', title: '发布人'}
            ,{field: 'content', title: '公告内容'}
            ,{field: 'posttime', title: '发布时间', sort: true}
            ,{field: 'top', title: '置顶', templet: '#buttonTpl', minWidth: 80, align: 'center'}
            ,{title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-forum-list'}
        ]]
        ,page: true
        ,limit: 10
        ,limits: [10, 15, 20, 25, 30]
        ,text: '对不起，加载出现异常！'
    });

    //监听工具条
    table.on('tool(LAY-app-forum-list)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm('确定删除此条帖子？', function(index){
                obj.del();
                layer.close(index);
            });
        } else if(obj.event === 'edit'){
            var tr = $(obj.tr);

            layer.open({
                type: 2
                ,title: '编辑公告'
                ,content: '/basic_manage/notice/edit'
                ,area: ['550px', '300px']
                ,btn: ['确定', '取消']
                ,resize: false
                ,yes: function(index, layero){
                    var iframeWindow = window['layui-layer-iframe'+ index]
                        ,submitID = 'LAY-app-forum-submit'
                        ,submit = layero.find('iframe').contents().find('#'+ submitID);

                    //监听提交
                    iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                        var field = data.field; //获取提交的字段

                        //提交 Ajax 成功后，静态更新表格中的数据
                        //$.ajax({});
                        table.reload('LAY-app-forum-list'); //数据刷新
                        layer.close(index); //关闭弹层
                    });

                    submit.trigger('click');
                }
                ,success: function(layero, index){

                }
            });
        }
    });

    exports('basic', {})
});