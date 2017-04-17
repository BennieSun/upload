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

    /**
     * 返回字符的字节长度（汉字算2个字节）
     * @param {string}
     * @returns {number}
     */

    $.getByteLength = function (val) {
        var len = 0;
        for (var i = 0; i < val.length; i++) {
            if (val[i].match(/[^x00-xff]/ig) != null) //全角
                len += 2;
            else
                len += 1;
        };
        return len;
    }

})(jQuery);

