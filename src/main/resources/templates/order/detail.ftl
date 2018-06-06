<html>
 <#include "../common/header.ftl">

    <body>
        <div id="wrapper" class="toggled">
            <#include "../common/nav.ftl">

            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-2 column">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>订单编号</th>
                                <th>总额</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.orderAmount}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>商品ID</th>
                                <th>名称</th>
                                <th>价格</th>
                                <th>数量</th>
                                <th>总额</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list orderDTO.orderDetailList as detail>
                            <tr>
                                <td>${detail.productId}</td>
                                <td>${detail.productName}</td>
                                <td>${detail.productPrice}</td>
                                <td>${detail.productQuantity}</td>
                                <td>${detail.productPrice * detail.productQuantity}</td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-12 column">
                    <#if orderDTO.getOrderStatusEnum().msg == '新下单' >
                        <a href="/sell/seller/order/finishi?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-primary">完结订单</a>
                        <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-warning">取消订单</a>
                    </#if>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>