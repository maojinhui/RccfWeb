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
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
};

function formateName(name) {
    if(null == name || typeof (name) == undefined){
        return "未填写";
    }else{
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
    if (reg.test(phone)){
        return true;
    }else{
        return false;
    }
}


$.toast.prototype.defaults.duration = 500;
