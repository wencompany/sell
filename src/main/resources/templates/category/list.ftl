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
                        <th>类目Id</th>
                        <th>名称</th>
                        <th>编号</th>
                        <th>创建时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list categoryPage.content as category>
                        <tr>
                            <td>${category.categoryId}</td>
                            <td>${category.categoryName}</td>
                            <td>${category.categoryType}</td>
                            <td>${(category.createdTime)!""}</td>
                            <td>
                                <a href="/sell/seller/category/index?categoryId=${category.categoryId}" >修改</a>
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
                            <li><a href="/sell/seller/category/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                        </#if>
                        <#list 1..categoryPage.getTotalPages() as index>

                            <#if index == currentPage>
                                <li class="disabled"><a href="#" >${index}</a></li>
                            <#else >
                                <li><a href="/sell/seller/category/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>

                        </#list>
                        <#if currentPage gte categoryPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else >
                            <li><a href="/sell/seller/category/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                        </#if>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>