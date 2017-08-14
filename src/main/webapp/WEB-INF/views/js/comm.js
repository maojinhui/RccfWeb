function formatDateTime(inputTime) {
    var date = new Date(inputTime);
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
    return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
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

function isInclude(name){
    var js= /js$/i.test(name);
    var es=document.getElementsByTagName(js?'script':'link');
    for(var i=0;i<es.length;i++)
        if(es[i][js?'src':'href'].indexOf(name)!=-1)return true;
    return false;
}

if(isInclude("jquery-weui.min.js")){
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




