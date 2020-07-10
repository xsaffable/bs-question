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
        , url: layui.setter.base + 'json/useradmin/webuser.js' //模拟接口
        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', width: 100, title: 'ID', sort: true}
            , {field: 'username', title: '用户名'}
            , {field: 'nickname', title: '昵称'}
            , {field: 'phone', title: '手机'}
            , {field: 'email', title: '邮箱'}
            , {field: 'sex', width: 80, title: '性别'}
            , {field: 'logintime', title: '登录时间', sort: true}
        ]]
        , page: true
        , limit: 30
        , text: '对不起，加载出现异常！'
    });



    exports('monitor', {})
});