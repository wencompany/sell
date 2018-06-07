<html>
    <#include "../common/header.ftl">
    <body>
        <div id="wrapper" class="toggled">

            <#include "../common/nav.ftl">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table  table-condensed table-hover">
                            <thead>
                            <tr>
                                <th>订单Id</th>
                                <th>姓名</th>
                                <th>手机号</th>
                                <th>地址</th>
                                <th>金额</th>
                                <th>订单状态</th>
                                <th>支付状态</th>
                                <th>创建时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                        <#list orderDTOPage.content as order>
                        <tr>
                            <td>${order.orderId}</td>
                            <td>${order.buyerName}</td>
                            <td>${order.buyerPhone}</td>
                            <td>${order.buyerAddress}</td>
                            <td>${order.orderAmount}</td>
                            <td>${order.getOrderStatusEnum().msg}</td>
                            <td>${order.getPayStatusEnum().msg}</td>
                            <td>${(order.createdTime)!""}</td>
                            <td>
                                <a href="/sell/seller/order/detail?orderId=${order.orderId}" >详情</a>
                            </td>
                            <td>
                                    <#if order.getOrderStatusEnum().msg == '新下单' >
                                        <a href="/sell/seller/order/cancel?orderId=${order.orderId}">取消</a>
                                    </#if>
                            </td>
                        </tr>
                        </#list>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-12 column">
                        <ul class="pagination">
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else >
                            <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                        </#if>
                        <#list 1..orderDTOPage.getTotalPages() as index>

                            <#if index == currentPage>
                                <li class="disabled"><a href="#" >${index}</a></li>
                            <#else >
                                <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>

                        </#list>
                        <#if currentPage gte orderDTOPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else >
                            <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                        </#if>
                        </ul>
                    </div>

                    <#--弹窗提示-->
                    <div class="col-md-12 column">
                        <div class="modal fade" id="showMsg" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                        <h4 class="modal-title" id="myModalLabel">
                                            新订单
                                        </h4>
                                    </div>
                                    <div class="modal-body">
                                        你有新的订单了
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" onclick="javascript:document.getElementById('notice').pause();" class="btn btn-default">关闭</button>
                                        <button type="button" onclick="location.reload()" class="btn btn-primary">保存</button>
                                    </div>
                                </div>

                            </div>

                </div>
            </div>
        </div>
        <#--播放音乐-->
        <audio id="notice" loop="loop">
            <source src="/sell/mp3/song.mp3" type="audio/mpeg"></source>
        </audio>

         <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script>
            var websocket = null;
            if ('WebSocket' in window) {
                websocket = new WebSocket('ws://wmd.mynatapp.cc/sell/webSocket');
            } else {
                alert("该浏览器不支持WebSocket")
            }

            websocket.onopen = function (event) {
                console.log("建立连接")
            }

            websocket.onclose = function (event) {
                console.log("关闭连接")
            }

            websocket.onmessage = function (evnt) {
                console.log('收到消息', evnt.data);
                //播放音乐，弹窗提示
                // $('#showMsg').showModal();
                // document.getElementById('notice').onplay;
                $('#showMsg').modal('show');

                document.getElementById('notice').play();
            }

            websocket.onerror = function (event) {
                alert("web通信发生错误");
            }

            websocket.onbeforeunload = function () {
                websocket.close();
            }
        </script>
    </body>
</html>
<#--<#list orderDTOPage.content as order>-->
    <#--${order.orderId}<br>-->

<#--</#list>-->