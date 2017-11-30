Date.prototype.format = function (format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
}


function isNull(variable1) {
    if (variable1 === null) {
        return true;
    }
    if (typeof (variable1) === 'undefined') {
        return true;
    }
    if (variable1 === "") {
        return true;
    }

    if (variable1 === "null") {
        return true;
    }

    return false;
}

function formatDateTime(inputTime) {
    if (typeof (inputTime) == 'undefined' || isNull(inputTime)) {
        return "";
    }
    var date = new Date();
    date.setTime(inputTime);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d;
};

function formateName(name) {
    if (null == name || typeof (name) == undefined) {
        return "未填写";
    } else {
        return name;
    }
}

var deskey = "rccf.*abc123.*abc123.*abc123.*";

function desEncrypt(text) {
    var keyHex = CryptoJS.enc.Utf8.parse(deskey);
    var encrypted = CryptoJS.DES.encrypt(text, keyHex, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    return encrypted.toString();
}


function ismobile(phone) {
    var reg = /^1[34578]\d{9}$/;
    if (reg.test(phone)) {
        return true;
    } else {
        return false;
    }
}

function isInclude(name) {
    var js = /js$/i.test(name);
    var es = document.getElementsByTagName(js ? 'script' : 'link');
    for (var i = 0; i < es.length; i++)
        if (es[i][js ? 'src' : 'href'].indexOf(name) != -1) return true;
    return false;
}

if (isInclude("jquery-weui.min.js")) {
    $.toast.prototype.defaults.duration = 500;
}

// 动态加载css文件
function loadStyles(url) {
    var link = document.createElement("link");
    link.type = "text/css";
    link.rel = "stylesheet";
    link.href = url;
    document.getElementsByTagName("head")[0].appendChild(link);
}


function getStringWithspace(str) {
    if (isNull(str)) {
        return "";
    }
    return str;
}


function getCustomerLevel(level) {
    var
}


function getString(str) {
    if (isNull(str)) {
        return '无';
    }
    return str;
}


function getdata(str) {
    if (isNull(str)) {
        return '无';
    }
    return str;
}

/**
 * 获取贷款类型
 * @param type
 * @returns {*}
 */
function getType(type) {
    if (type === 0) {
        return '信用贷';
    } else if (type === 1) {
        return '抵押';
    } else if (type === 2) {
        return '质押';
    }
    else if (type === 3) {
        return '权证';
    } else if (type === 4) {
        return '车贷';
    } else if (type === 5) {
        return '拼份';
    } else if (type === 6) {
        return '包装';
    } else if (type === 10) {
        return '融成贷';
    } else {
        return '未知';
    }
}

/**
 * 转换受理单状态
 * @param state
 * @returns {*}
 */
function getState(state) {
    if (state === 1) {
        return '受理';
    } else if (state === 2) {
        return '办结';
    } else if (state === 3) {
        return '拒单';
    } else if (state === 4) {
        return '撤单';
    } else {
        return '其他';
    }
}

function get_sum(arr) {
    var sum = 0;
    for (var i = 0; i < arr.length; i++) {
        sum += arr[i][6];
    }
    return sum;
}

function getSex(sex) {
    if (sex === 0) {
        return "未知";
    } else if (sex === 1) {
        return "男";
    } else if (sex === 2) {
        return "女";
    } else {
        return "未知";
    }
}

function getDate(time) {
    var date = new Date();
    if (isNull(time)) {
        return '';
    }
    date.setTime(time);
    return date.format('yyyy-MM-dd')
}

function getDateComplete(time) {
    var date = new Date();
    if (isNull(time)) {
        return '';
    }
    date.setTime(time);
    return date.format('yyyy-MM-dd hh:mm:ss');
}

function getRepaymentType(type) {
    if (isNull(type)) {
        return '未填写';
    } else if (type === 0) {
        return "未填写";
    }
    else if (type === 1) {
        return "等额本金";
    } else if (type === 2) {
        return "等额本息";
    } else if (type === 3) {
        return "停本付息";
    } else if (type === 4) {
        return "先息后本";
    } else {
        return "其他";
    }
}

/**
 * 借款用途
 */
function getUseType(type) {
    if (isNull(type)) {
        return '未填写';
    } else if (type === 1) {
        return "个人消费";
    } else if (type === 2) {
        return "企业经营";
    } else if (type === 3) {
        return "其他";
    } else {
        return "其他";
    }
}

/**
 * 客户级别
 */
function getUseRank(rank) {
    if (isNull(rank)) {
        return '未填写';
    } else if (rank === 0) {
        return "未填写";
    } else if (rank === 1) {
        return "A";
    } else if (rank === 2) {
        return "B";
    } else if (rank === 3) {
        return "C";
    } else {
        return "其他";
    }
}

/**
 * 获取审核状态
 * @param state
 * @returns {*}
 */
function getAuditStateStr(state){
        if(isNull(state)){
            return '未知';
        }
        if(state==1){
            return '已审核';
        }else if (state==2){
            return '审核不通过';
        }else if(state==3){
            return '审核中';
        }else{
            return '未知';
        }
}




