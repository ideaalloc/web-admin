<#-- @ftlvariable name="host" type="java.lang.String" -->
<#-- @ftlvariable name="contextPath" type="java.lang.String" -->

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="Disallow">
    <meta name="author" content="lvchao, billcc.lv@hotmail.com">
    <title>后台管理系统</title>

    <link rel="shortcut icon" type="image/x-icon" href="/assets/img/favicon.ico">

    <link rel="stylesheet" href="/libs/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/formValidation.min.css">
    <link rel="stylesheet" href="/assets/css/login.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="/assets/js/html5shiv.min.js"></script>
    <script src="/assets/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <h2>请登录</h2>

    <div class="row">
        <div class="wrap col-sm-offset-2 col-sm-8 col-md-offset-3 col-md-6">
            <form id="loginForm" class="form-horizontal" action="/login" method="post">
                <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">

                <div class="form-group">
                    <div class="col-xs-offset-1 col-xs-10">
                        <div class="input-group">
                            <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
                            <input type="text" id="username" name="username" class="form-control" placeholder="请输入账户">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-offset-1 col-xs-10">
                        <div class="input-group">
                            <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
                            <input type="password" id="password" name="password" class="form-control"
                                   placeholder="请输入密码">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-offset-1 col-xs-10 col-sm-7">
                        <div class="input-group">
                            <div class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></div>
                            <input type="text" class="form-control" id="captchaInput" name="captchaInput"
                                   placeholder="请输入验证码">
                        </div>
                    </div>
                    <div class="col-xs-offset-1 col-sm-offset-0 col-sm-3">
                        <img id="captcha" width="64" height="34" src="/captcha" alt="验证码"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-offset-1 col-xs-10">
                        <button type="submit" class="btn btn-primary btn-lg btn-block">登 录</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div id="errorContainer">
    <#if error.isPresent()>
        <div class="row">
            <div class="col-sm-offset-2 col-sm-8 col-md-offset-3 col-md-6">
                <div class="alert alert-danger" role="alert">账户或密码错误, 请重新输入</div>
            </div>
        </div>
    </#if>
    </div>

</div>

<script src="/libs/bower_components/jquery/dist/jquery.min.js"></script>
<script src="/libs/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="/assets/js/formValidation.js"></script>
<script src="/assets/js/bootstrap.js"></script>

<script>
    var generateCaptcha = function () {
        $('#captcha').attr('src', '/captcha?' + new Date().getTime());
    };

    var validateCaptcha = function () {
        var data = {};
        data.challenge = $.trim($('#captchaInput').val());
        if (data.challenge == "") {
            return false;
        }

        return 'true' === $.ajax({
                    type: "POST",
                    url: "/captcha/validate",
                    cache: false,
                    async: false,
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8",
                    dataType: "json"
                }).responseText;
    };

    $(function () {
        $(document).on("click", ":submit", function (e) {
            if (!validate()) {
                return false;
            }
        });

        $('#captcha').click(function () {
            generateCaptcha();
        });

        $('#loginForm')
                .formValidation({
                    framework: 'bootstrap',
                    icon: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        captchaInput: {
                            validators: {
                                callback: {
                                    message: '验证码输入错误',
                                    callback: function (value, validator, $field) {
                                        return validateCaptcha();
                                    }
                                }
                            }
                        },
                        username: {
                            validators: {
                                notEmpty: {
                                    message: '须输入账户'
                                }
                            }
                        },
                        password: {
                            validators: {
                                notEmpty: {
                                    message: '须输入密码'
                                }
                            }
                        }
                    }
                })
                .on('err.form.fv', function (e) {
                    generateCaptcha();
                });

        var validate = function () {
            var fv = $("#loginForm").data("formValidation");
            fv.validateContainer($("#loginForm"));
            var isValid = fv.isValidContainer($("#loginForm"));
            if (isValid === false || isValid === null) {
                return false;
            }
            return true;
        }
    });
</script>
<script src="/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
