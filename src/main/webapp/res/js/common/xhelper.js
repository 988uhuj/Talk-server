/**
 * Created with IntelliJ IDEA.
 * User: sureone
 * Date: 6/29/13
 * Time: 1:57 PM
 * To change this template use File | Settings | File Templates.
 */

(function($) {
    $.xhelper = {

        //FYI 根据实际应用情况设置相应的表单缺省数据过滤
        formFilter:{
            name:'姓名',
            name:'如：南开大学××学院/系/单位××采购项目'
        },

        test:function(text){
            alert("hello"+text);
        },

        /**
         * 从表单对象数据中将匹配到的表单数据赋值为空
         * @param {} formData 表单对象数据
         */
        filterFormDefaultValue:function(formData){
            $.each(this.formFilter, function(property, value){
                if(value==formData[property]){
                    formData[property] = '';
                }
            });

        },
        request:function(url,mapParam,onSuccess,onFail,onLog){
            var jsonParam = JSON.stringify(mapParam);
            $.ajax({
                dataType : 'json',
                type : 'POST',
                url : url,
                data:{param:jsonParam},
                success : function(data) {
                    if(onLog!=null)
                        onLog(data);

                    //closeSpinInfo($('#tabContainer'));
                    var body = data.body;
                    var code = data.status;
                    var msg = data.msg;
                    var action = data.action;
                    if(onSuccess !=null)
                        onSuccess(action,code,body,msg);
                },
                error : function() {
                    if(onFail != null)
                        onFail(action);

                }
            });
        },

        //序列化form成Array去除输入提示
        formSerializeToArray:function(form){

                var o = {};
                var a = form.serializeArray();
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

                this.filterFormDefaultValue(o);

                return o;
        }
    }


})(jQuery);