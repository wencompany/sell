<html>
    <#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">

            <#include "../common/nav.ftl">
    <div class="container-fluid">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <form role="form" method="post" action="/sell/seller/category/save">
                    <div class="form-group">
                        <label >类目名称</label>
                        <input type="text" class="form-control" name="categoryName" value="${(category.categoryName)!''}" placeholder="类目名称"/>
                    </div>
                    <div class="form-group">
                        <label >类目编号</label>
                        <input type="number" class="form-control" name="categoryType" value="${(category.categoryType)!''}" placeholder="类目编号"/>
                    </div>

                    <input type="hidden" name="categoryId" value="${(category.getCategoryId())!''}" />
                    <button type="submit" class="btn btn-default">提交</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>