// JavaScript Document
(function($) {
    $.extend($.fn.validatebox.defaults.rules, {
        CHS: {
            validator: function(value, param) {
                return /^[\u0391-\uFFE5]+$/.test(value);
            },
            message: '请输入汉字'
        },
        english: { // 验证英语
            validator: function(value) {
                return /^[A-Za-z]+$/i.test(value);
            },
            message: '请输入英文'
        },
        ip: { // 验证IP地址
            validator: function(value) {
                return /\d+\.\d+\.\d+\.\d+/.test(value);
            },
            message: 'IP地址格式不正确'
        },
        ZIP: {
            validator: function(value, param) {
                return /^[0-9]\d{5}$/.test(value);
            },
            message: '邮政编码不存在'
        },
        QQ: {
            validator: function(value, param) {
                return /^[1-9]\d{4,10}$/.test(value);
            },
            message: 'QQ号码不正确'
        },
        mobile: {
            validator: function(value, param) {
                return /^(?:13\d|15\d|17\d|18\d)-?\d{5}(\d{3}|\*{3})$/.test(value);
            },
            message: '手机号码不正确'
        },
        tel: {
            validator: function(value, param) {
                return /^(\d{3}-|\d{4}-)?(\d{8}|\d{7})?(-\d{1,6})?$/.test(value);
            },
            message: '电话号码不正确'
        },
        mobileAndTel: {
            validator: function(value, param) {
                return /(^([0\+]\d{2,3})\d{3,4}\-\d{3,8}$)|(^([0\+]\d{2,3})\d{3,4}\d{3,8}$)|(^([0\+]\d{2,3}){0,1}13\d{9}$)|(^\d{3,4}\d{3,8}$)|(^\d{3,4}\-\d{3,8}$)/.test(value);
            },
            message: '请正确输入电话号码'
        },
        number: {
            validator: function(value, param) {
                return /^[0-9]+.?[0-9]*$/.test(value);
            },
            message: '请输入数字'
        },
        money: {
            validator: function(value, param) {
                return (/^(([1-9]\d*)|\d)(\.\d{1,3})?$/).test(value);
            },
            message: '请输入正确的金额'

        },
        double: {
        	validator: function(value, param) {
        		return (/^(([1-9]\d*)|\d)(\.\d{1,6})?$/).test(value);
        	},
        	message: '请输入正确的比例,最多保留五位小数'
        		
        },
        mone: {
            validator: function(value, param) {
                return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
            },
            message: '请输入整数或小数'

        },
        integer: {
            validator: function(value, param) {
                return /^[+]?[1-9]\d*$/.test(value);
            },
            message: '请输入最小为1的整数'
        },
        integ: {
            validator: function(value, param) {
                return /^[+]?[0-9]\d*$/.test(value);
            },
            message: '请输入整数'
        },
        range: {
            validator: function(value, param) {
                if (/^[1-9]\d*$/.test(value)) {
                    return value >= param[0] && value <= param[1]
                } else {
                    return false;
                }
            },
            message: '输入的数字在{0}到{1}之间'
        },
        minLength: {
            validator: function(value, param) {
                return value.length >= param[0]
            },
            message: '至少输入{0}个字'
        },
        maxLength: {
            validator: function(value, param) {
                return value.length <= param[0]
            },
            message: '最多{0}个字'
        },
        //select即选择框的验证
        selectValid: {
            validator: function(value, param) {
                if (value == param[0]) {
                    return false;
                } else {
                    return true;
                }
            },
            message: '请选择'
        },
        idCode: {
            validator: function(value, param) {
                return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);
            },
            message: '请输入正确的身份证号'
        },
        loginName: {
            validator: function(value, param) {
                return /^[\u0391-\uFFE5\w]+$/.test(value);
            },
            message: '登录名称只允许汉字、英文字母、数字及下划线。'
        },
        account: {
            validator: function(value, param) {
                return /^[A-Za-z0-9]+$/.test(value);
            },
            message: '登录名称只允许英文字母、数字。'
        },
        equalTo: {
            validator: function(value, param) {
                return value == $(param[0]).val();
            },
            message: '两次输入的字符不一至'
        },
        englishOrNum: { // 只能输入英文和数字
            validator: function(value) {
                return /^[a-zA-Z0-9_ ]{1,}$/.test(value);
            },
            message: '请输入英文、数字、下划线或者空格'
        },
        xiaoshu: {
            validator: function(value) {
                return /^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/.test(value);
            },
            message: '最多保留两位小数！'
        },
        ddPrice: {
            validator: function(value, param) {
                if (/^[1-9]\d*$/.test(value)) {
                    return value >= param[0] && value <= param[1];
                } else {
                    return false;
                }
            },
            message: '请输入1到100之间正整数'
        },
        jretailUpperLimit: {
            validator: function(value, param) {
                if (/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)) {
                    return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);
                } else {
                    return false;
                }
            },
            message: '请输入0到100之间的最多俩位小数的数字'
        },
        yearLimit: {
            validator: function(value, param) {
                if (/^[0-9]+([.]{1}[0-9]{0,1})?$/.test(value)) {
                    return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);
                } else {
                    return false;
                }
            },
            message: '请输入0到100之间的最多一位小数的数字'
        },
        name: { // 验证姓名，可以是中文 
            validator: function(value) {
                return /^[\Α-\￥]{2,}$/i.test(value);
            },
            message: '请输入姓名'
        },
        rateCheck: {
            validator: function(value, param) {
                if (/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)) {
                    return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);
                } else {
                    return false;
                }
            },
            message: '请输入0到1000之间的最多俩位小数的数字'
        },
        dateValid: {
            validator: function(value, param) { //参数value为当前文本框的值，也就是endDate 
                startTime = $(param[0]).datetimebox('getValue'); //获取起始时间的值  
                var start = $.fn.datebox.defaults.parser(startTime);
                var end = $.fn.datebox.defaults.parser(value);
                varify = end > start;
                return varify;
            },
            message: '竣工时间要大于开工时间!'
        }
    });
    //输入框内容不再在列表选项内时清空
    $.extend($.fn.combobox.methods, {
        match: function(jq) {
            $(jq).combobox({
                onChange: function(newValue, oldValue) {
                    if (newValue == null || newValue == undefined || (newValue != null && newValue != undefined && newValue.length == 0)) {
                        $(jq).combobox('setValue', '');
                    }
                },
                onHidePanel: function() {
                    var q = $.trim($(jq).combobox('getText'));
                    var opt = $(jq).combobox('options');
                    var data = $(jq).combobox('getData');
                    for (var i = 0; i < data.length; i++) {
                        var flag = true;
                        if (q == $.trim(eval('data[i].' + opt.textField))) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        $(jq).combobox('setValue', '');
                    }
                }
            });
        },
        selectedIndex: function(jq, index) {
            if (!index) {
                index = 0;
            }
            $(jq).combobox({
                onLoadSuccess: function() {
                    var opt = $(jq).combobox('options');
                    var data = $(jq).combobox('getData');

                    for (var i = 0; i < data.length; i++) {
                        if (i == index) {
                            $(jq).combobox('setValue', eval('data[index].' + opt.valueField));
                            break;
                        }
                    }
                }
            });
        }
    });
    //树形输入框内容不再在列表选项内时清空
    $.extend($.fn.combotree.methods, {
        match: function(jqTree) {
            $(jqTree).combotree({
                onChange: function(newValue, oldValue) {
                    if (newValue == null || newValue == undefined || (newValue != null && newValue != undefined && newValue.length == 0)) {
                        $(jqTree).combotree('setValue', '');
                    }
                },
                onHidePanel: function() {
                    var q = $.trim($(jqTree).combotree('getText'));
                    var arr = [];
                    $(".panel .tree-title").each(function() {
                        arr.push($.trim($(this).html()))
                    });
                    for (var i = 0; i < arr.length; i++) {
                        var flag = true;
                        if (q == arr[i]) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        $(jqTree).combotree('setValue', '');
                    }
                }
            });
        },
        selectedIndex: function(jqTree, index) {
            if (!index) {
                index = 0;
            }
            $(jqTree).combotree({
                onLoadSuccess: function(node, data) {
                    var root = data[index].text;
                    $(jqTree).combotree('setValue', root);
                }
            });
        }
    });
})(jQuery);

$.fn.datebox.defaults.formatter = function(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();
    return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d);
};

$.fn.datebox.defaults.parser = function(s) {
    if (s) {
        var a = s.split('-');
        var d = new Date(parseInt(a[0]), parseInt(a[1]) - 1, parseInt(a[2]));
        return d;
    } else {
        return new Date();
    }
};