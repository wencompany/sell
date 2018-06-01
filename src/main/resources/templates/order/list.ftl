<html>
    <head>
        <meta charset="UTF-8">
        <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-hover">
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
                                <td>${order.createdTime}</td>
                                <td>详情</td>
                                <td>取消</td>
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
            </div>
        </div>

    </body>
</html>
<#--<#list orderDTOPage.content as order>-->
    <#--${order.orderId}<br>-->

<#--</#list>-->