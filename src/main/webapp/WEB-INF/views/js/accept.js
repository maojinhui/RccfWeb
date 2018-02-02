
function getAcceptStateInfo(state,customerName,employee,houqi){
    if(state===1){
        return employee+'提交客户'+customerName+'的受理单';
    }
    else if(state===2){
        return employee+'的客户'+customerName+'的受理单已被'+houqi+"驳回";
    }
    else if(state===3){
        return employee+'的客户'+customerName+'的受理单已被'+houqi+"提交至受理中心";
    }
    else if(state===4){
        return employee+'的客户'+customerName+'的受理单被受理中心驳回';
    }
    else if(state===5){
        return employee+'的客户'+customerName+'的受理单被受理中心受理';
    }

}


function acceptInfo(obj) {
    var accept_id = obj.dataset.acceptId ;
    location.href = '/gzh/accept/info?accept_id='+accept_id;
}
