/**

 @Name：layuiAdmin 用户管理 管理员管理 角色管理
 @Author：star1029
 @Site：http://www.layui.com/admin/
 @License：LPPL

 */


layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;

  //用户管理
  table.render({
    elem: '#LAY-user-manage'
    ,url: "/user/list"
    ,method: 'post'
    ,contentType: 'application/json'
    ,parseData: function(res){ //res 即为原始返回的数据

      if (res.data && res.data.length === 0) {
          return {"code": 201, "msg": "无数据"};
      }
      return {
        "code": 0, //解析接口状态
        "msg": res.msg, //解析提示文本
        "count": res.count, //解析数据长度
        "data": res.code === 0 ? res.data : [] //解析数据列表
      };
    }
    ,cols: [[
      {type: 'checkbox', fixed: 'left'}
      ,{field: 'id', width: 100, title: 'ID', sort: true}
      ,{field: 'username', title: '用户名'}
      ,{field: 'nickname', title: '昵称'}
      ,{field: 'phoneNumber', title: '手机号'}
      ,{field: 'email', title: '邮箱'}
      ,{field: 'sex', title: '性别', templet: function (d) {
          if (d.sex === 0) {
            return "女";
          } else if (d.sex === 1) {
            return "男";
          } else {
            return "未填写";
          }
        }}
      ,{field: 'createtime', title: '加入时间', sort: true}
      ,{title: '操作', width: 150, align:'center', fixed: 'right', toolbar: '#table-useradmin-webuser'}
    ]]
    ,page: true
    ,limit: 10
    ,text: '对不起，加载出现异常！'
  });

  //监听工具条
  table.on('tool(LAY-user-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
      layer.confirm('确定删除?', function(index){
        var param = [{"id": obj.data.id}];
        reqNum = 1;
        requestAsync("/user/delete", JSON.stringify(param), function (data) {
          parent.layer.msg(data.msg || "删除成功", { icon: 6, time: 500 });
        })
        table.reload('LAY-user-manage'); //数据刷新
        layer.close(index);
      });
    } else if(obj.event === 'edit'){
      var tr = $(obj.tr);

      layer.open({
        type: 2
        ,title: '编辑用户'
        ,content: '/user_manage/user_form'
        ,maxmin: true
        ,area: ['500px', '450px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var iframeWindow = window['layui-layer-iframe'+ index]
          ,submitID = 'LAY-user-front-submit'
          ,submit = layero.find('iframe').contents().find('#'+ submitID);

          //监听提交
          iframeWindow.layui.form.on('submit('+ submitID +')', function(d){
            var field = d.field; //获取提交的字段
            reqNum = 1;
            requestAsync("/user/update", JSON.stringify(field), function (data) {
              parent.layer.msg(data.msg || "更新成功", { icon: 6, time: 500 });
              //提交 Ajax 成功后，静态更新表格中的数据
              table.reload('LAY-user-manage'); //数据刷新
              layer.close(index); //关闭弹层
            });

          });

          submit.trigger('click');
        }
        ,success: function(layero, index){
          var body = layer.getChildFrame('body', index);
          if (data.username !== null) {
            body.find("input[name='username']").val(data.username);
            // 禁止修改用户名
            body.find("input[name='username']").attr("disabled", true);
          }
          if (data.nickname !== null) {
            body.find("input[name='nickname']").val(data.nickname);
          }
          if (data.password !== null) {
            body.find("input[name='password']").val(data.password);
            // 禁止修改密码
            body.find("input[name='password']").attr("disabled", true);
          }
          if (data.phoneNumber !== null) {
            body.find("input[name='phoneNumber']").val(data.phoneNumber);
          }
          if (data.email !== null) {
            body.find("#email").val(data.email);
          }
          if (data.sex !== null) {
            body.find("input[name=sex][value='"+data.sex+"']").prop("checked","true");
          }
        }
      });
    }
  });

  //管理员管理
  table.render({
    elem: '#LAY-user-back-manage'
    ,url: "/user/admin/list"
    ,method: 'post'
    ,contentType: 'application/json'
    ,parseData: function(res){ //res 即为原始返回的数据
      if (res.data && res.data.length === 0) {
        return {"code": 201, "msg": "无数据"};
      }
      return {
        "code": 0, //解析接口状态
        "msg": res.msg, //解析提示文本
        "count": res.count, //解析数据长度
        "data": res.code === 0 ? res.data : [] //解析数据列表
      };
    }
    ,cols: [[
      {type: 'checkbox', fixed: 'left'}
      ,{field: 'id', width: 100, title: 'ID', sort: true}
      ,{field: 'username', title: '用户名'}
      ,{field: 'nickname', title: '昵称'}
      ,{field: 'phoneNumber', title: '手机号'}
      ,{field: 'email', title: '邮箱'}
      ,{field: 'sex', title: '性别', templet: function (d) {
          if (d.sex === 0) {
            return "女";
          } else if (d.sex === 1) {
            return "男";
          } else {
            return "未填写";
          }
        }}
      ,{field: 'createtime', title: '加入时间', sort: true}
      ,{title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-useradmin-admin'}
    ]]
    ,page: true
    ,limit: 30
    ,text: '对不起，加载出现异常！'
  });

  //监听工具条
  table.on('tool(LAY-user-back-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
      layer.confirm('确定删除此管理员？', function(index){
        var param = [{"id": data.id}];
        reqNum = 1;
        requestAsync("/user/delete", JSON.stringify(param), function (data) {
          parent.layer.msg(data.msg || "删除成功", { icon: 6, time: 500 });
          table.reload('LAY-user-back-manage'); //数据刷新
          layer.close(index);
        })

      });
    }else if(obj.event === 'edit'){
      var tr = $(obj.tr);

      layer.open({
        type: 2
        ,title: '编辑管理员'
        ,content: '/admin_manage/user_form'
        ,area: ['420px', '490px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var iframeWindow = window['layui-layer-iframe'+ index]
          ,submitID = 'LAY-user-back-submit'
          ,submit = layero.find('iframe').contents().find('#'+ submitID);

          //监听提交
          iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
            var field = data.field; //获取提交的字段
            reqNum = 1;
            requestAsync("/user/update", JSON.stringify(field), function (data) {
              parent.layer.msg(data.msg || "更新成功", { icon: 6, time: 500 });
              //提交 Ajax 成功后，静态更新表格中的数据
              table.reload('LAY-user-back-manage'); //数据刷新
              layer.close(index); //关闭弹层
            });
          });

          submit.trigger('click');
        }
        ,success: function(layero, index){
          var iframeWindow = window['layui-layer-iframe'+ index]
          var body = layer.getChildFrame('body', index);
          if (data.username !== null) {
            body.find("input[name='username']").val(data.username);
            // 禁止修改用户名
            body.find("input[name='username']").attr("disabled", true);
          }
          if (data.nickname !== null) {
            body.find("input[name='nickname']").val(data.nickname);
          }
          if (data.password !== null) {
            body.find("input[name='password']").val(data.password);
            // 禁止修改密码
            body.find("input[name='password']").attr("disabled", true);
          }
          if (data.phoneNumber !== null) {
            body.find("input[name='phoneNumber']").val(data.phoneNumber);
          }
          if (data.email !== null) {
            body.find("#email").val(data.email);
          }
          if (data.role !== null) {
            body.find("#roleId").val(data.role);
          }
        }
      })
    }
  });

  exports('useradmin', {})
});