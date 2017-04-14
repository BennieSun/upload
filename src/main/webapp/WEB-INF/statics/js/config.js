
    var lang_en_US = "en-US";
    var lang_zh_CH = "zh-CH";
    var lang_zh_TW = "zh-TW";

    var _zh_TW_const = {
        aqUniqueIdIsNull: "請先進行提問",
        paramsException: "參數異常，請進行填寫必填項"
    }

    var _en_US_const = {
        aqUniqueIdIsNull: "Please ask questions first",
        paramsException: "params exception，Please fill in the required fields"
    }

    var _zh_CH_const = {
        aqUniqueIdIsNull: "Please ask questions first",
        paramsException: "参数异常，请进行填写必填项"
    }

    function _zh_TW(_param){
        return _zh_TW_const[_param];
    }

    function _en_US(_param){
        return _en_US_const[_param];
    }

    function _zh_CH(_param){
        return _zh_CH_const[_param];
    }


    function _languageSelect(_language, _param){
        if(_language == lang_zh_TW){
            return _zh_TW(_param);
        }else if(_language == lang_en_US){
            return _en_US(_param);
        }else if(_language == lang_zh_CH){
            return _zh_CH(_param);
        }else{
            return "error";
        }
    }
