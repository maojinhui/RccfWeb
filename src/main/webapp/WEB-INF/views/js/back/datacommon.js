/**
 * 根据name获取checkbox的int值
 *
 * @param name
 */
function getCheckIntValues(name) {
    var chk_val = [];
    $("input[name='" + name + "']:checked").each(function () {
        chk_val.push(parseInt($(this).val()));
    });
    return JSON.stringify(chk_val);
}

/**
 * 根据name获取checkbox的Float值
 *
 * @param name
 */
function getCheckFloatValues(name) {
    var chk_val = [];
    $("input[name='" + name + "']:checked").each(function () {
        chk_val.push(parseFloat($(this).val()));
    });
    return JSON.stringify(chk_val);
}


/**
 * 根据name获取radio的value
 * @param name
 * @returns {*}
 */
function getRadioValue(name) {
    var radioVlaue = null;
    $("input[name='" + name + "']").each(function () {
        var chck = $(this).attr("checked");
        if (chck) {
            radioVlaue = $(this).val();
        }
    });
    return radioVlaue;
}