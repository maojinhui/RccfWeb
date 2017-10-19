<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/10/19
  Time: 下午7:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>员工信息</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/admin.css"/>
    <%--<link rel="stylesheet" type="text/css" href="/css/style.css"/>--%>

    <style type="text/css" rel="stylesheet">
        html, body {
            overflow: auto;
        }

        .am-g .am-input-group-label {
            width: 8em;
        }

        .am-nav-tabs > li.am-active > a, .am-nav-tabs > li.am-active > a:focus, .am-nav-tabs > li.am-active > a:hover {
            color: #eeeeee;
            background-color: #33393c;
            border: 1px solid #eeeeee;
            border-bottom-color: transparent;
            cursor: default;
        }

        .am-tabs-nav li {
            position: relative;
            z-index: 1;
        }

        .am-tabs-nav .am-icon-close {
            top: 0;
            right: 10px;
            color: #888;
            cursor: pointer;
            z-index: 100;
        }

        .am-tabs-nav .am-icon-close:hover {
            color: #333;
        }

        .am-tabs-nav .am-icon-close ~ a {
            padding-right: 25px !important;
        }

        .info-img {
            display: none;
            height: 200px;
            border: solid 1px #eeeeee;
            color: #ea6e0c;
        }

        .info-img img {
            width: 100%;
            height: 200px;
        }

    </style>
</head>
<body>
<div class="admin-content-body am-margin-top-lg">


    <div class="am-tabs" data-am-tabs="{noSwipe: 1}" id="doc-tab-demo-1">
        <ul class="am-tabs-nav am-nav am-nav-tabs">
            <li class="am-active"><a href="javascript: void(0)">岗位信息</a></li>
            <li><a href="javascript: void(0)">劳动合同信息</a></li>
            <li><a href="javascript: void(0)">基础信息</a></li>
            <li><a href="javascript: void(0)">联系方式</a></li>

            <li><a href="javascript: void(0)">证件审核</a></li>
        </ul>


        <div class="am-tabs-bd">
            <div class="am-tab-panel am-active">
                <!--岗位信息-->
                <div class="am-margin-bottom-lg">
                    <div class="am-g ">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered ">
                            <div class="am-input-group am-input-group-sm">
                        <span class="am-input-group-label">
                         部&emsp;&emsp;门:
                         </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                        <span class="am-input-group-label">
                         总&emsp;&emsp;监:
                         </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                        <span class="am-input-group-label">
                         &emsp;副总监:
                         </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                        <span class="am-input-group-label">
                         岗&emsp;&emsp;位:
                         </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                            <span class="am-input-group-label">
                             姓&emsp;&emsp;名:
                             </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     入职时间:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     转正日期:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                        <span class="am-input-group-label">
                         编&emsp;&emsp;号:
                         </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                        <span class="am-input-group-label">
                         &emsp;门禁号:
                         </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                        <span class="am-input-group-label">
                         &emsp;曾用名:
                         </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                        <span class="am-input-group-label">
                         &emsp;英文名:
                         </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     性&emsp;&emsp;别:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     状&emsp;&emsp;态:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     离职日期:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     离职原因:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="am-tab-panel">
                <!--劳动合同信息-->
                <div class="am-margin-bottom-lg">
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     用工形式:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     合同编号:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     合同发放:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     合同类型:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     合同期限:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>

                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     <small>签定合同日期:</small>
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     <small>合同生效日期:</small>
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>

                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     <small>合同终止日期:</small>
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     <small>合同剩余天数:</small>
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     <small>换签劳动合同:</small>
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     <small>合同续签情况:</small>
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="am-tab-panel">
                <!--基础信息-->
                <div class="am-margin-bottom-lg">
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     户籍所在地:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>

                    <!--民族	血型	婚否	政治面貌	年龄	出生年月	身份证号	参   加   工作时间	子女姓名	子女生日-->
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     民&emsp;&emsp;族:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     血&emsp;&emsp;型:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     婚&emsp;&emsp;否:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     政治面貌:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     年&emsp;&emsp;龄:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     出生年月:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     身份证号:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     <small>参加工作时间:</small>
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <div class="am-tab-panel">
                <!--联系方式-->
                <div class="am-margin-bottom-lg">
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     联系电话:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     紧急联系人:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     <small>紧急联系电话:</small>
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     与本人关系:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     身份证地址:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     家庭住址:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     邮&emsp;&emsp;编:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     &emsp;现住址:
                     </span>
                                <input class="am-form-field" type="text">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="am-tab-panel">
                <!--证件审核-->
                <div class="am-margin-bottom-lg">

                    <div class="am-g am-margin-bottom-0">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm am-form-file">
                                <span class="am-input-group-label">
                                 身份证正面:
                                 </span>
                                <button type="button" class="am-btn am-btn-default am-btn-sm am-vertical-align-bottom">
                                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                                </button>
                                <input id="IDCard_face" class="am-form-field " accept="image/*" type="file" multiple>
                            </div>
                        </div>
                    </div>
                    <div class="am-g am-margin-bottom">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="info-img">
                                <img id="img_IDCard_face" src="" alt="身份证正面"/>
                            </div>
                        </div>
                    </div>


                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm am-form-file">
                                <span class="am-input-group-label">
                                 身份证背面:
                                 </span>
                                <button type="button" class="am-btn am-btn-default am-btn-sm am-vertical-align-bottom">
                                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                                </button>
                                <input id="IDCard_back" class="am-form-field " accept="image/*" type="file" multiple>
                            </div>
                        </div>
                    </div>
                    <div class="am-g am-margin-bottom">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="info-img">
                                <img id="img_IDCard_back" src="" alt="身份证背面"/>
                            </div>
                        </div>
                    </div>

                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm am-form-file">
                                <span class="am-input-group-label">
                                 &emsp;学历证:
                                 </span>
                                <button type="button" class="am-btn am-btn-default am-btn-sm am-vertical-align-bottom">
                                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                                </button>
                                <input id="diploma" class="am-form-field " accept="image/*" type="file" multiple>
                            </div>
                        </div>
                    </div>
                    <div class="am-g am-margin-bottom">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="info-img">
                                <img id="img_diploma" src="" alt="学历证"/>
                            </div>
                        </div>
                    </div>


                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm am-form-file">
                                <span class="am-input-group-label">
                                 &emsp;职称证:
                                 </span>
                                <button type="button" class="am-btn am-btn-default am-btn-sm am-vertical-align-bottom">
                                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                                </button>
                                <input id="certificate" class="am-form-field " accept="image/*" type="file" multiple>
                            </div>
                        </div>
                    </div>
                    <div class="am-g am-margin-bottom">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="info-img">
                                <img id="img_certificate" src="" alt="职称证"/>
                            </div>
                        </div>
                    </div>

                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm am-form-file">
                                <span class="am-input-group-label">
                                 照&emsp;&emsp;片:
                                 </span>
                                <button type="button" class="am-btn am-btn-default am-btn-sm am-vertical-align-bottom">
                                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                                </button>
                                <input id="photo" class="am-form-field " accept="image/*" type="file" multiple>
                            </div>
                        </div>
                    </div>
                    <div class="am-g am-margin-bottom">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="info-img">
                                <img id="img_photo" src="" alt="照片"/>
                            </div>
                        </div>
                    </div>


                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm am-form-file">
                                <span class="am-input-group-label">
                                 离职证明:
                                 </span>
                                <button type="button" class="am-btn am-btn-default am-btn-sm am-vertical-align-bottom">
                                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                                </button>
                                <input id="leave_proof" class="am-form-field " accept="image/*" type="file" multiple>
                            </div>
                        </div>
                    </div>
                    <div class="am-g am-margin-bottom">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="info-img">
                                <img id="img_leave_proof" src="" alt="离职证明"/>
                            </div>
                        </div>
                    </div>


                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm am-form-file">
                                <span class="am-input-group-label">
                                 <small>个人信用报告:</small>
                                 </span>
                                <button type="button" class="am-btn am-btn-default am-btn-sm am-vertical-align-bottom">
                                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                                </button>
                                <input id="personal_credit" class="am-form-field " accept="image/*" type="file"
                                       multiple>
                            </div>
                        </div>
                    </div>
                    <div class="am-g am-margin-bottom">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="info-img">
                                <img id="img_personal_credit" src="" alt="个人信用报告"/>
                            </div>
                        </div>
                    </div>


                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm am-form-file">
                                <span class="am-input-group-label">
                                 <small>无犯罪证明:</small>
                                 </span>
                                <button type="button" class="am-btn am-btn-default am-btn-sm am-vertical-align-bottom">
                                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                                </button>
                                <input id="no_crime" class="am-form-field " accept="image/*" type="file" multiple>
                            </div>
                        </div>
                    </div>
                    <div class="am-g am-margin-bottom">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="info-img">
                                <img id="img_no_crime" src="" alt="无犯罪证明"/>
                            </div>
                        </div>
                    </div>


                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm am-form-file">
                                <span class="am-input-group-label">
                                 <small>体检报告:</small>
                                 </span>
                                <button type="button" class="am-btn am-btn-default am-btn-sm am-vertical-align-bottom">
                                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                                </button>
                                <input id="check" class="am-form-field " accept="image/*" type="file" multiple>
                            </div>
                        </div>
                    </div>
                    <div class="am-g am-margin-bottom">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="info-img">
                                <img id="img_check" src="" alt="体检报告"/>
                            </div>
                        </div>
                    </div>

                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-8 am-u-lg-6 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     备&emsp;&emsp;注:
                     </span>
                                <textarea rows="3" cols="50">
                                </textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br/>

    <div class="am-g">
        <div class="am-u-sm-4 am-u-sm-centered">
            <button type="button" class="am-btn am-btn-primary am-margin-left-lg am-u-sm-3">确认保存</button>
            <button type="button" class="am-btn am-btn-default am-margin-left-lg am-u-sm-3 am-u-end">取消编辑</button>
        </div>

    </div>

</div>

<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="/js/amaze/amazeui.min.js"></script>
<script>

    $(function () {

        function uploadFile(form, dvPreview, e) {
            $.ajax({
                url: '/upload/document',
                type: 'POST',
                dataType: 'json',
                data: form,
                contentType: false,
                processData: false,
                success: function (result) {
                    if (result.code) {
                        dvPreview.attr("src", e.target.result);
                        dvPreview.parent('.info-img').css('display', 'block');
                    } else {
                        alert(result.errormsg);
                    }
                },
                error: function (result) {

                }
            });
        }

        function changeFn(dvPreview, node, eid, type) {

            if (typeof (FileReader) != "undefined") {

//                var dvPreview = $("#img_IDCard_back");

                dvPreview.html("");

                var regex = /(.jpg|.jpeg|.gif|.png|.bmp)$/;

                $(node[0].files).each(function () {
                    var file = $(this);
                    if (regex.test(file[0].name.toLowerCase())) {

                        var reader = new FileReader();
                        reader.onload = function (e) {
                            var form = new FormData();
                            form.append('eid', eid);
                            form.append('type', type);
                            form.append('file', file[0]);
                            uploadFile(form, dvPreview, e);

                        };
                        reader.readAsDataURL(file[0]);

                    } else {
                        alert(file[0].name + " is not a valid image file.");
                        dvPreview.html("");
                        return false;
                    }
                });
            } else {
                alert("This browser does not support HTML5 FileReader.");
            }
        }


        $("#IDCard_face").change(function () {
            var dvPreview = $("#img_IDCard_face");
            changeFn(dvPreview, $(this), 1, 'idcard_positive');
        });

        $("#IDCard_back").change(function () {
            var dvPreview = $("#img_IDCard_back");
            changeFn(dvPreview, $(this), 1, 'idcard_negative');
        });

        $('#diploma').change(function () {
            var dvPreview = $("#img_diploma");
            changeFn(dvPreview, $(this), 1, 'education');
        });

        $('#certificate').change(function () {
            var dvPreview = $("#img_certificate");
            changeFn(dvPreview, $(this), 1, 'job_title');
        });

        $('#photo').change(function () {
            var dvPreview = $("#img_photo");
            changeFn(dvPreview, $(this), 1, 'picture');
        });

        $('#leave_proof').change(function () {
            var dvPreview = $("#img_leave_proof");
            changeFn(dvPreview, $(this), 1, 'leavingproof');
        });

        $('#personal_credit').change(function () {
            var dvPreview = $("#img_personal_credit");
            changeFn(dvPreview, $(this), 1, 'cridit_report');
        });

        $('#no_crime').change(function () {
            var dvPreview = $("#img_no_crime");
            changeFn(dvPreview, $(this), 1, 'nocrime_report');
        });

        $('#check').change(function () {
            var dvPreview = $("#img_check");
            changeFn(dvPreview, $(this), 1, 'examination');
        });


        var tabCounter = 0;
        var $tab = $('#doc-tab-demo-1');
        var $nav = $tab.find('.am-tabs-nav');
        var $bd = $tab.find('.am-tabs-bd');

        function addTab() {
            var nav = '<li><span class="am-icon-close"></span>' +
                '<a href="javascript: void(0)">标签 ' + tabCounter + '</a></li>';
            var content = '<div class="am-tab-panel">动态插入的标签内容' + tabCounter + '</div>';

            $nav.append(nav);
            $bd.append(content);
            tabCounter++;
            $tab.tabs('refresh');
        }

        // 动态添加标签页
        $('.js-append-tab').on('click', function () {
            addTab();
        });

        // 移除标签页
        $nav.on('click', '.am-icon-close', function () {
            var $item = $(this).closest('li');
            var index = $nav.children('li').index($item);

            $item.remove();
            $bd.find('.am-tab-panel').eq(index).remove();

            $tab.tabs('open', index > 0 ? index - 1 : index + 1);
            $tab.tabs('refresh');
        });
    });
</script>
</body>
</html>
