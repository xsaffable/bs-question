/**
 * 系统监控
 */
layui.define(['table', 'form'], function (exports) {
    var $ = layui.$
        , table = layui.table
        , form = layui.form;

    // 登录监控
    table.render({
        elem: '#LAY-user-manage'
        , url: '/monitor/userlogin_listpage' //模拟接口
        , method: 'post'
        , contentType: 'application/json'
        , cols: [[{field: 'id', width: 100, title: 'ID', sort: true}
            , {field: 'username', title: '用户名'}
            , {field: 'nickname', title: '昵称'}
            , {field: 'phoneNumber', title: '手机'}
            , {field: 'email', title: '邮箱'}
            , {field: 'sex', width: 80, title: '性别', templet: function (d) {
                    if (d['sex'] == '1') {
                        return '男';
                    } else if (d['sex'] == '0') {
                        return '女';
                    } else {
                        return '';
                    }
                }}
            , {field: 'createtime', title: '登录时间', sort: true}
        ]]
        , page: true
        , limit: 10
        , limits: [10, 20, 30]
        , text: '对不起，加载出现异常！'
    });



    exports('monitor', {})
});