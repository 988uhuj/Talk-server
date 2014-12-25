/**
 * Created by jfchen on 12/24/14.
 */


//================================================


/**
 * 页面初始化完成后执行
 */
$(document).ready(function(){
    pushTabToStack('user-list-tab-li');
    queryUserList(1,20);
});

/**
 *处理标签页点击事件
 */
$('#nav-tab a').click(function (e) {
    e.preventDefault();

    var $target = $(e.target);
    if( $target.is("button") ) {
        return;
    }

    var ele = $(this);
    $(this).parent().removeClass('hide');
    ele.tab('show');

    pushTabToStack(e.target.id);

    if(e.target.id=='user-list-tab-li'){
        //queryUserList(1,20);
    }
});

function openNewTab(){
    showTab("user-new-tab-li");
}


//================================================

function saveNewUser(){
    postSaveNewUser("user-new-form")
}

function postSaveNewUser(formId){
    var map={};
    map = $.xhelper.formSerializeToArray($("#"+formId))
    map['action']="create";
    $.xhelper.request("./user/index.do",map,

        //onSuccess
        function(action,code,body,msg){
            if(code == 200){
                alert("提交成功");
            } else if(code == 404){
                alert("该账号已被注册");
            }
        },
        //onLoad
        function(){

        },
        //onLog
        null);
}


/**
 *
 * @param page  分页序号
 * @param pageSize  每页显示条数
 */
function queryUserList(page, pageSize){

    /**
     * 表格列定义
     * @type {Array}
     */
    var columns = [
        {
            name:'account',
            title:'账号'
        },
        {
            name:'name',
            title:'昵称'
        },
        {
            name:'password',
            title:'密码'
        }];

    /**
     * ajax 请求回调函数
     * @type {{success: Function, error: Function}}
     */
    var ajaxCallback={
        success:function(data){
        },
        error:function(){
        }
    };

    var div_table;
    var div_page;
    var form;

    div_table=  $('#user-table');
    div_page =  $('#user-page');
    form=$('#form-user-query');

    /**
     * 通用查询结果生成列表函数
     */
    commonQueryForListAndPage(
        page,
        pageSize,
        form,
        './user/index.do',
        columns,
        div_table,
        ajaxCallback,
        div_page
    );
}
