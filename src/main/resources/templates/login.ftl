<html>
    <#include "common/header.ftl">

    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <form role="form" method="get" action="/sell/seller/login">
                    <div class="form-group">
                        <label for="exampleInputEmail1">用户名</label><input type="text" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">密码</label><input type="password" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">openid</label><input name="openid" type="password" class="form-control" />
                    </div>

                    <button type="submit" class="btn btn-default">登录</button>
                </form>
            </div>
        </div>
    </div>
</html>