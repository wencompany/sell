<html>
    <#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">

            <#include "../common/nav.ftl">
    <div class="container-fluid">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <form role="form" method="post" action="/sell/seller/product/save">
                    <div class="form-group">
                        <label >商品名称</label>
                        <input type="text" class="form-control" name="productName" value="${(productInfo.productName)!''}" placeholder="商品名称"/>
                    </div>
                    <div class="form-group">
                        <label >价格</label>
                        <input type="text" class="form-control" name="productPrice" value="${(productInfo.productPrice)!''}" placeholder="价格"/>
                    </div>
                    <div class="form-group">
                        <label >库存</label>
                        <input type="number" class="form-control" name="productStock" value="${(productInfo.productStock)!''}" placeholder="库存"/>
                    </div>
                    <div class="form-group">
                        <label >描述</label>
                        <input type="text" class="form-control" name="productDescription" value="${(productInfo.productDescription)!''}" placeholder="描述"/>
                    </div>
                    <div class="form-group">
                        <label >图片</label>
                        <img src="${(productInfo.productIcon)!''}" height="100" width="100" alt="">
                        <input type="text" class="form-control" name="productIcon" value="${(productInfo.productIcon)!''}" placeholder="图片地址"/>
                    </div>
                    <div class="form-group">
                        <label >类别</label>
                        <select name="categoryType">
                            <#list categoryList as category>
                                <option value="${category.categoryType}"
                                    <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryId>selected</#if>>
                                    ${category.categoryName}
                                </option>
                            </#list>
                        </select>
                    </div>
                    <input type="hidden" name="productId" value="${(productInfo.productId)!''}" />
                    <button type="submit" class="btn btn-default">提交</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>