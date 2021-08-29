//// 获取当前界面宽高
//var badyheight = window.screen.availHeight;
//var badywith = window.screen.width;
//
//// 设置当前中间高度
//$(".admin_center").height(badyheight - 110 - 145);
//
////设置 中间内容的宽度
//$(".admin_center_center").width(badywith - 220);
//$(".admin_center_center").css("background-color","#ffffff");

<<<<<<< HEAD
=======
layui.use(['form','layer','element','laypage'], function(){
    var form = layui.form;
    var layer = layui.layer;
    var element = layui.element;
    var laypage = layui.laypage;

    //统一重置
    $(".onReset").click(function () {
        window.location.reload();
    })

    //统一监听提交
    var submitbool = true;
    form.on('submit(formDemo)', function(data){
        if(submitbool){
            submitbool = false;
            var formurl = data.form.action;
            var fielddata = data.field;
            var operation = data.elem.dataset.operation;   //refresh 刷新 return 返回 jump 跳转
            var jumpurl = data.elem.dataset.jumpurl;       //jump 跳转url
            $.ajax({
                url: formurl,
                type: 'POST',
                contentType: 'application/json;charset=UTF-8',
                dataType:'json',
                data: JSON.stringify(fielddata),
                success: function(res){
                    if(res.code == 1){
                        layer.msg(res.msg,function () {
                            if(operation == "refresh"){
                                window.location.reload();
                            }else if(operation == "return"){
                                window.history(-1);
                            }else if(operation == "jump"){
                                window.location.href = jumpurl;
                            }
                            submitbool = true;
                        })
                    }else{
                        submitbool = true;
                        layer.msg(res.msg);
                    }
                },
                error: function(){
                    submitbool = true;
                    layer.msg("ajax请求失败");
                }
            })
        }
        return false;
    });

    //checkbox统一提交
    form.on('switch(checkboxcomm)', function(data){
        var url = this.dataset.url;
        var switchbool =  data.elem.checked;
        $.ajax({
            url: url,
            data: {switchbool:switchbool},
            success: function (res) {
                if(res.code == 1){
                    layer.msg(res.msg,function () {
                        window.location.reload();
                    })
                }else{
                    layer.msg(res.msg);
                }
            },
            error: function(){
                layer.msg("ajax请求失败");
            }
        });
    });

    //多选
    form.on('checkbox(selectAll)', function(data){
        if(data.elem.checked){
            $("input[class='selectli']").each(function(){
                $(this).prop('checked',true);
            });
        }else{
            $("input[class='selectli']").each(function(){
                $(this).prop('checked',false);
            });
        }
        form.render();
    });

    //单个删除
    $(".onedelete").click(function () {
        var url = this.dataset.url;
        layer.confirm('是否删除？', {
            btn: ['是','否'] //按钮
        }, function(){
            $.ajax({
                url: url,
                data: {},
                success: function (res) {
                    if(res.code == 1){
                        layer.msg(res.msg,function () {
                            window.location.reload();
                        })
                    }else{
                        layer.msg(res.msg);
                    }
                },
                error: function(){
                    layer.msg("ajax请求失败");
                }
            });
        }, function(){

        });
    })

    //多个删除
    $(".alldelete").click(function () {
        var idarr = "";
        $("input[class='selectli']").each(function(){
            if ($(this).is(':checked')){
                idarr += $(this).val()+",";
            }
        });
        if(idarr == ""){
            layer.msg("请选择数据");
            return;
        }
        idarr = idarr.slice(0,idarr.length-1);
        var url = this.dataset.url;
        layer.confirm('是否删除？', {
            btn: ['是','否'] //按钮
        }, function(){
            $.ajax({
                url: url,
                data: {id:idarr},
                success: function (res) {
                    if(res.code == 1){
                        layer.msg(res.msg,function () {
                            window.location.reload();
                        })
                    }else{
                        layer.msg(res.msg);
                    }
                },
                error: function(){
                    layer.msg("ajax请求失败");
                }
            });
        }, function(){

        });
    });


    function getQueryVariable(variable)
    {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i=0;i<vars.length;i++) {
            var pair = vars[i].split("=");
            if(pair[0] == variable){return pair[1];}
        }
        return "";
    }

    //分页
    var listcount = document.getElementById('commonid').getAttribute('data-listcount');
    //获取当前地址
    var url = window.location.href;
    //获取page
    var pagecurr = 0;
    var page = getQueryVariable('page');
    if(page == ""){
        pagecurr = 1;
    }
    if(page != ""){
        pagecurr = parseInt(page) + 1;
    }
    //执行一个laypage实例
    laypage.render({
        elem: 'pageview' //注意，这里的 test1 是 ID，不用加 # 号
        ,count: listcount //数据总数，从服务端得到
        ,limit:30
        ,curr: pagecurr//获取起始页
        ,hash: false//自定义hash值
        ,jump: function(obj, first){
            //obj包含了当前分页的所有参数，比如：
            //首次不执行
            if(!first){
                //do something
                var nextpage = (parseInt(obj.curr) - 1);
                if(page == ""){
                    var pageurl = url+"?page="+nextpage;
                }else{
                    var pageurl = url.replace('page='+page, "page="+nextpage);
                }
                window.location.href = pageurl;
            }
        }
    });

    //输入框失去焦点触发
    $(".blurinput").blur(function () {
        var url = this.dataset.url;
        var val =  $(this).val();
        $.ajax({
            url: url,
            data: {val:val},
            success: function (res) {
                if(res.code == 1){
                    layer.msg(res.msg,function () {
                        window.location.reload();
                    })
                }else{
                    layer.msg(res.msg);
                }
            },
            error: function(){
                layer.msg("ajax请求失败");
            }
        });
    });

    //退出
    $(".loginout").click(function () {
        var url = this.dataset.url;
        $.ajax({
            url: url,
            data: {},
            success: function (res) {
                if(res.code == 1){
                    layer.msg(res.msg,function () {
                        window.location.reload();
                    })
                }else{
                    layer.msg(res.msg);
                }
            },
            error: function(){
                layer.msg("ajax请求失败");
            }
        });
    })


});




>>>>>>> 6bc4f3c... 第一个版本完成
