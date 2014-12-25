/**
 * Created by jfchen on 12/24/14.
 */

// 栈结构
function Stack()   //Creating Stack Object
{
    // Create an empty array of cards.
    this.cards = new Array();  //cards array inside stack object
    this.push  = pushData;      //Call pushdata function on push operation
    this.pop   = popData;        //Call popdata function on pop operation
    this.top = topData;
    this.printStack = showStackData; //Call showStackData function on printstack operation
}

function topData(){
    if(this.cards.length>0)
        return this.cards[this.cards.length-1];
    return null;
}
function pushData(data) {
    this.cards.push(data);
}

function popData(data) {
    return this.cards.pop();
}

function showStackData() {
    return this.cards;
}

//================================================

var g_tabStack= new Stack();
//关闭标签
$(".closeTheTab").click(function () {

    //there are multiple elements which has .closeTab icon so close the tab whose close icon is clicked
    var liId = $(this).parent().attr('id');
    //$(this).parent().parent().remove(); //remove li of tab
    //$('#myTab a:last').tab('show'); // Select first tab

    $(this).parent().parent().attr('class', 'hide');; //remove respective tab content

    var curTab = g_tabStack.pop();
    var lastTab = g_tabStack.pop();

    if(lastTab==liId){
        return;
    }

    var ele = $('#'+lastTab);
    showTab(lastTab);
});

function showTab(eleId){
    var ele = $("#"+eleId);
    ele.click();
}

function hideTab(eleId){
    var ele = $('#'+eleId);
    ele.parent().attr('class', 'hide');
}

function pushTabToStack(eleId){
    var topId=g_tabStack.top();
    if(eleId!=topId)
        g_tabStack.push(eleId);
}

function showdiv(id) {
    document.getElementById(id).style.display = "block";
}
function hidediv(id) {
    document.getElementById(id).style.display = "none";
}




//=================表格相关===============================


/**
 * 从表单对象数据中将匹配到的表单数据赋值为空
 * @param {} formData 表单对象数据
 * @param {} matcherData 查找匹配的数据
 */
function removeFormDefaultVal(formData, matcherData){

    $.each(matcherData, function(property, value){
        if(value==formData[property]){
            formData[property] = '';
        }
    });

}

function commonInitPagination(total,page,pageSize,param,url,columns,table,callback,divPage){
    if(!divPage) return;


    divPage.unbind();
    divPage.bootpag( {
        total : total,
        page : page,
        maxVisible : pageSize
    }).on('page', function(event, num) {
        internalQueryForListAndPageByParamMap(num,pageSize,param,url,columns,table,callback,divPage);
    });
}



/**
 * 生成表格的Thead元素
 * @param {} dataList 表格展示的数据集合
 * @param {} titleNames 标题数组对象，用于指定显示的顺序
 */
function commonGetThead(columns){
    var thead = $('<thead>');
    var tit = $('<tr>');
    $.each(columns, function(index, column){
        var th = $('<th>');
        th.html(column['title']);
        tit.append(th);
    });
    thead.append(tit);

    return thead;
}

/**
 * 生成表格的TBODY元素
 * @param {} dataList 表格展示的数据集合
 * @param {} titleNames 标题数组对象，用于指定显示的顺序
 */
function commonGetTbody(dataList, columns){
    var tbody = $('<tbody>');
    $.each(dataList, function(i, data){
        var tr = $('<tr>');
        $.each(columns, function(index, column){
            var tdValue = "";
            var td = $('<td>');

            if(jQuery.isFunction(column['render'])){
                column['render'](data, data[column['name']], td);
            } else {
                tdValue = data[column['name']];
                if(column['name']=='specialty') {
                    var div = $('<div style="display: inline-block;width:300px"></div>');
                    div.html(tdValue);
                    td.append(div);
                }else
                    td.html(tdValue);
            }
            tr.append(td);
        });
        tbody.append(tr);
    });

    return tbody;
}


function internalQueryForListAndPageByParamMap(page, pageSize,param,url,columns,table,callback,divPage){

    g_cur_page = page;
    g_cur_pagesize = pageSize;

    var paramMap = JSON.stringify(param);



    table.prepend(showAlert('spininfo','',"正在加载数据"));

    var postdata = {'param':paramMap};
    if(divPage!=null){
        postdata = {'param':paramMap,'page':page,'pageSize':pageSize};

    }


    $.ajax({
        dataType : 'json',
        type : 'POST',
        url : url,
        data : postdata,
        success : function(data) {
            var body = data.body;
            table.html('');

            if(body!=null){


                var tbody = commonGetTbody(body, columns);
                var thead = commonGetThead(columns);

                table.append(thead);
                table.append(tbody);

                if(divPage!=null){
                    // 初始化分页
                    commonInitPagination(data.Total, data.page,pageSize,param,url,columns,table,callback,divPage);
                }
            }
            callback.success(data);

        },
        error : function() {

            callback.error();
        }
    });
}


function commonQueryForListAndPage(page, pageSize,conditionForm,url,columns,table,callback,divPage){

    var param = conditionForm.serializeFormStandard();

    var matcherData = {
        name:'部门名称',
        tel:'电话号码'
    };
    removeFormDefaultVal(param, matcherData);
    internalQueryForListAndPageByParamMap(page,pageSize,param,url,columns,table,callback,divPage);
}


function commonQueryForSimpleList(param,url,columns,table,callback){
    internalQueryForListAndPageByParamMap(1,20,param,url,columns,table,callback,null);
}

/**
 * 将一个表单的数据返回成JSON对象
 * 依赖jQuery类库
 * @return {}
 */
$.fn.serializeFormStandard = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

function showAlert(type, title, info, target){
    var alertClass = '';
    var spin='';
    switch(type){

        case 'success' : alertClass = 'alert-success';
            break;
        case 'error' : alertClass = 'alert-error';
            break;
        case 'info' : alertClass = 'alert-info';
            break;
        case 'spininfo' : alertClass = 'alert-info spin';
            spin='<span><img src="./res/image/loading.gif"/>  </span>';
            break;
        case 'warn' : alertClass = 'alert-block';
            break;

    }
    var html  = '<div class="alert ' + alertClass + '">';
    html +=	'	<button id="mymodel_alert_close" type="button" class="close" data-dismiss="alert">&times;</button>';
    if(title){
        html += '	<h4>' + title + '</h4>';
    }
    html += spin+info;
    html += '</div>';

    if(type=='success' || type=='error' || type=='info'){
        window.setTimeout(function(){
            $('#mymodel_alert_close').click();
        }, 5000);
    }

    return html;

}

