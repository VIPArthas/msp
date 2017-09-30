function scrollNotice() {
    var i = 0;
    var bimg = $(".notice li");
    var Wid = bimg.height();

    function WFB() {
        bimg.eq(i).show().animate({top: -Wid});
        i++;
        if (i == bimg.length)i = 0;
        bimg.eq(i).css("top", Wid).show().animate({top: '0px'})
    }

    setInterval(WFB, 2000)
}
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12,
        "H+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    var week = {
        "0": "/u65e5",
        "1": "/u4e00",
        "2": "/u4e8c",
        "3": "/u4e09",
        "4": "/u56db",
        "5": "/u4e94",
        "6": "/u516d"
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length))
    }
    if (/(E+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f" : "/u5468") : "") + week[this.getDay() + ""])
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)))
        }
    }
    return fmt
};
 
 
function addImg(obj, len) {
    var _this = obj, par = $(obj).closest(".imglist3");
    var can = document.getElementById("mycanvas"), ctx = can.getContext("2d"), imgC = $(_this).closest(".aimg");
    var tCanvas = document.createElement("canvas");
    function compress(a) {
        var b = a.src.length;
        var c = a.width;
        var d = a.height;
        var e;
        if ((e = c * d / 4000000) > 1) {
            e = Math.sqrt(e);
            c /= e;
            d /= e
        } else {
            e = 1
        }
        can.width = c;
        can.height = d;
        ctx.fillStyle = "#fff";
        ctx.fillRect(0, 0, c, d);
        var f;
        if ((f = c * d / 1000000) > 1) {
            f = ~~(Math.sqrt(f) + 1);
            var g = ~~(c / f);
            var h = ~~(d / f);
            tCanvas.width = g;
            tCanvas.height = h;
            var k = tCanvas.getContext("2d");
            for (var i = 0; i < f; i++) {
                for (var j = 0; j < f; j++) {
                    k.drawImage(a, i * g * e, j * h * e, g * e, h * e, 0, 0, g, h);
                    ctx.drawImage(tCanvas, i * g, j * h, g, h)
                }
            }
        } else {
            ctx.drawImage(a, 0, 0, c, d)
        }
        var l = can.toDataURL("image/jpeg", 0.5);
        tCanvas.width = tCanvas.height = can.width = can.height = 0;
        var m = new Image();
        m.src = l;
        imgC.before('<div class="img"><span class="del"></span></div>');
        imgC.prev().height(imgC.width());
        imgC.height(imgC.width()).find("i").css("lineHeight", imgC.width() + "px");
        imgC.prev().prepend(m);
        par.find(".aimg").hide()
    }

    if (_this.files && _this.files[0]) {
        if (window.FileReader) {
            var reader = new FileReader();
            reader.onload = function (a) {
                var b = new Image();
                b.onload = function () {
                    compress(b)
                };
                b.src = this.result
            };
            reader.readAsDataURL(_this.files[0])
        } else {
            alert("您的浏览器不支持图片预览");
            return false
        }
    } else {
        alert("请升级微信版本")
    }
    function handler() {
        var aimg = $(this).closest(".imglist3");
        if (aimg.find(".img").length <= len) {
            aimg.find(".aimg").show();
           // aimg.find(".aimg").height(aimg.find(".aimg").width())
        }
        $(this).parent().remove();
    }

    par.on("click", ".del", handler)
}
function imglistInit(i) {
    $(".imglist3 .img").height("100%");
    if ($(".imglist3 .img").length >= i) {
        $(".imglist3").find(".aimg").hide()
    }
}
$(function () {
    var len = 9;
    imglistInit(len);
    var par = $(".imglist3");

    function handler() {
        var aimg = $(this).closest(".imglist3");
        if (aimg.find(".img").length <= len) {
            aimg.find(".aimg").show();
           // aimg.find(".aimg").height(aimg.find(".aimg").width())
        }
        $(this).parent().remove();
    }

    par.on("click", ".del", handler)
});
var format;
function convertdataURIToBlob_j(base64) {
    format = 'image/jpeg';
    var code;
    if (base64.split(',')[0].indexOf('base64') >= 0) {
        code = atob(base64.split(',')[1]);
        format = base64.split(',')[0].split(':')[1].split(';')[0]
    } else {
        code = unescape(base64.split(',')[1])
    }
    var aBuffer = new window.ArrayBuffer(code.length);
    var uBuffer = new window.Uint8Array(aBuffer);
    for (var i = 0; i < code.length; i++) {
        uBuffer[i] = code.charCodeAt(i)
    }
    var Builder = window.WebKitBlobBuilder || window.MozBlobBuilder;
    if (Builder) {
        var builder = new Builder;
        builder.append(uBuffer);
        return builder.getBlob(format)
    } else {
        return new window.Blob([uBuffer], {type: format})
    }
}
function showLoading() {
    if ($("body").find(".loading").length == 0) {
        $("body").append('<div class="loading" >' +
            '<div class="alert_green" style="text-align: center">' +
            '<img src="../img/loading_1.gif" width="40%" style="border-radius: 5px">' +
            '</div>' +
            '</div>');

    } else {
        $(".loading").show()
    }
}
function hideLoading() {
    $(".loading").hide()
}
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}
//获取cookie值
function GetCookieValue(name) {
    var cookieValue = null;
    if (document.cookie && document.cookie != '') {
        var cookies = document.cookie.split(';');
        for (var i = 0; i < cookies.length; i++) {
            var cookie = $.trim(cookies[i]);
            if (cookie.substring(0, name.length + 1) == (name + '=')) {
                cookieValue = cookie.substring(name.length + 1).replace(/"/g, '');
                break;
            }
        }
    }
    return cookieValue;
}
function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null)
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}
function setCookie(name, value, time) {
    var exp = new Date();
    exp.setTime(time * 1);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}