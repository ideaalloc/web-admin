<#-- @ftlvariable name="contextPath" type="java.lang.String" -->

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="robots" content="Disallow">
    <meta name="author" content="lvchao, billcc.lv@hotmail.com">
    <title>后台管理系统</title>
    <link rel="shortcut icon" type="image/x-icon" href="/assets/img/favicon.ico">
    <link rel="stylesheet" href="/libs/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/libs/bower_components/bootstrap/dist/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/libs/bower_components/bootstrap-submenu/dist/css/bootstrap-submenu.min.css">
    <link rel="stylesheet" href="/libs/bower_components/datatables/media/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="/assets/css/dataTables.bootstrap.css">
    <link rel="stylesheet" href="/libs/bower_components/bootstrap3-dialog/dist/css/bootstrap-dialog.min.css">
    <link rel="stylesheet" href="/assets/css/formValidation.min.css">
    <link rel="stylesheet" href="/libs/bower_components/blueimp-gallery/css/blueimp-gallery.min.css">
    <link rel="stylesheet" href="/assets/css/bootstrap-image-gallery.min.css">
    <link rel="stylesheet" href="/libs/bower_components/bootstrap-fileinput/css/fileinput.min.css">
    <link rel="stylesheet" href="/libs/wijmo/styles/wijmo.min.css">
    <link rel="stylesheet" href="/libs/bower_components/image-picker/image-picker/image-picker.css">
    <link rel="stylesheet" href="/libs/bower_components/blueimp-gallery/css/blueimp-gallery.min.css">
    <link rel="stylesheet" href="/assets/css/bootstrap-image-gallery.min.css">
    <link rel="stylesheet" href="/assets/css/jquery.fileupload.css">
    <link rel="stylesheet" href="/libs/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css">
</head>
<body>

<script src="/libs/bower_components/jquery/dist/jquery.min.js"></script>
<script src="/libs/bower_components/jquery-cookie/jquery.cookie.js"></script>
<script src="/libs/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="/assets/js/dataTables.bootstrap.js"></script>
<script src="/libs/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="/libs/bower_components/bootstrap-submenu/dist/js/bootstrap-submenu.min.js"></script>
<script src="/libs/bower_components/bootstrap3-dialog/dist/js/bootstrap-dialog.min.js"></script>
<script src="/assets/js/jquery.bootstrap.wizard.min.js"></script>
<script src="/assets/js/formValidation.js"></script>
<script src="/assets/js/bootstrap.js"></script>
<script src="/libs/bower_components/angular/angular.min.js"></script>
<script src="/libs/bower_components/angular-route/angular-route.min.js"></script>
<script src="/libs/bower_components/blueimp-gallery/js/jquery.blueimp-gallery.min.js"></script>
<script src="/assets/js/bootstrap-image-gallery.min.js"></script>
<script src="/libs/wijmo/controls/wijmo.min.js"></script>
<script src="/libs/wijmo/controls/wijmo.chart.min.js"></script>
<script src="/libs/wijmo/interop/angular/wijmo.angular.min.js"></script>
<script src="/libs/bower_components/bootstrap-fileinput/js/fileinput.min.js"></script>
<script src="/assets/js/fileinput_locale_zh.js"></script>
<script src="/libs/bower_components/image-picker/image-picker/image-picker.min.js"></script>
<script src="/libs/bower_components/blueimp-gallery/js/jquery.blueimp-gallery.min.js"></script>
<script src="/assets/js/bootstrap-image-gallery.min.js"></script>
<script src="/assets/js/jquery.ui.widget.js"></script>
<script src="/assets/js/jquery.iframe-transport.js"></script>
<script src="/assets/js/jquery.fileupload.js"></script>
<script src="/libs/bower_components/moment/min/moment.min.js"></script>
<script src="/libs/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
<script src="/libs/bower_components/react/react.min.js"></script>
<script src="/libs/bower_components/react/JSXTransformer.js"></script>

<script src="/apps/app.js"></script>
<script src="/apps/build/kickStarters.js"></script>
<script>
    $(function () {
        $("#logout").click(function (e) {
            e.preventDefault();

            var data = {"${_csrf.parameterName}": "${_csrf.token}"};

            $.ajax({
                type: "POST",
                url: "/logout",
                data: JSON.stringify(data),
                cache: false,
                success: function (response) {
                    window.location.replace("/");
                },
                error: function (request, status, error) {
                    BootstrapDialog.alert({
                        title: '警告',
                        message: '注销失败',
                        type: BootstrapDialog.TYPE_WARNING,
                        buttonLabel: '确定'
                    });
                }
            });
        });
    });
</script>
</body>
</html>