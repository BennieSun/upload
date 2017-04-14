(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURI(r[2]); return null;
    }

    $.getUrlparams = function () {
        var loc = location.href;
        var _length = loc.length;//地址的总长度
        var _startLength = loc.indexOf("?");//取得=号的位置
        var data = loc.substr(_startLength+1, _length-_startLength);
        if (data != null)
        return data;
    }

})(jQuery);

