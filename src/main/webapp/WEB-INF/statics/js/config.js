var lang_en_US = "en-US";
var lang_zh_CH = "zh-CH";
var lang_zh_TW = "zh-TW";

var _zh_TW_const = {
    aqUniqueIdIsNull: "請先進行提問",
    aqUniqueIdIsExist: "請先結束提問",
    paramsException: "參數異常，請進行填寫必填項",
    confirmEnd: "確認結束",
    descriptionIsNull: "描述為必填項",
    descriptionIsToLong: "描述太長，只能150個漢字或者300个字符，包含空格和換行",
    refreshInterface: "請刷新界面"
}

var _en_US_const = {
    aqUniqueIdIsNull: "Please ask questions first",
    aqUniqueIdIsExist: "Please end the question first",
    paramsException: "Params exception，Please fill in the required fields",
    confirmEnd: "Confirmation ends",
    descriptionIsNull: "Description is required",
    descriptionIsToLong: "The description is too long, only 150 characters or 300 characters, including spaces and line breaks",
    refreshInterface: "Please refresh the interface"
}

var _zh_CH_const = {
    aqUniqueIdIsNull: "请先进行提问",
    aqUniqueIdIsExist: "请先结束提问",
    paramsException: "参数异常，请进行填写必填项",
    confirmEnd: "确认结束",
    descriptionIsNull: "描述为必填项",
    descriptionIsToLong: "描述太长，只能150個汉字或者300个字符，包含空格和换行",
    refreshInterface: "请刷新界面"

}

function _zh_TW(_param) {
    return _zh_TW_const[_param];
}

function _en_US(_param) {
    return _en_US_const[_param];
}

function _zh_CH(_param) {
    return _zh_CH_const[_param];
}


function _languageSelect(_language, _param) {
    if (_language == lang_zh_TW) {
        return _zh_TW(_param);
    } else if (_language == lang_en_US) {
        return _en_US(_param);
    } else if (_language == lang_zh_CH) {
        return _zh_CH(_param);
    } else {
        return _languageSelect(_defaultLang, _param);
    }
}
