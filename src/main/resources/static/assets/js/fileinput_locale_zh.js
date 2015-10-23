/*!
 * FileInput <_LANG_> Translations
 *
 * This file must be loaded after 'fileinput.js'. Patterns in braces '{}', or
 * any HTML markup tags in the messages must not be converted or translated.
 *
 * @see http://github.com/kartik-v/bootstrap-fileinput
 *
 * NOTE: this file must be saved in UTF-8 encoding.
 */
(function ($) {
    "use strict";

    $.fn.fileinput.locales.zh = {
        fileSingle: '文件',
        filePlural: '文件',
        browseLabel: '浏览 &hellip;',
        removeLabel: '删除',
        removeTitle: '清除已选文件',
        cancelLabel: '取消',
        cancelTitle: '取消上传',
        uploadLabel: '上传',
        uploadTitle: '上传已选文件',
        msgSizeTooLarge: '文件 "{name}" (<b>{size} KB</b>) 超出了最大上传限制 <b>{maxSize} KB</b>. 请重新上传!',
        msgFilesTooLess: '必须选择至少 <b>{n}</b> {files} 上传. 请重新上传!',
        msgFilesTooMany: '已选择的上传文件数 <b>({n})</b> 超出了最大上传文件数 <b>{m}</b>. 请重新上传!',
        msgFileNotFound: '文件 "{name}" 未找到!',
        msgFileSecured: '因安全限制无法访问文件 "{name}".',
        msgFileNotReadable: '文件 "{name}" 不可读.',
        msgFilePreviewAborted: '放弃预览 "{name}".',
        msgFilePreviewError: '读取文件 "{name}" 错误.',
        msgInvalidFileType: '非法上传类型 "{name}". 仅 "{types}" 类型的文件可以上传.',
        msgInvalidFileExtension: '非法扩展名 "{name}". 仅 "{extensions}" 扩展名的文件可以上传.',
        msgValidationError: '文件上传错误',
        msgLoading: '正在上传文件{files}中第 {index} 个文件  &hellip;',
        msgProgress: '正在加载第 {index} 个文件 已完成 {files} - {name} - {percent}%.',
        msgSelected: '已选 {n} 个文件',
        msgFoldersNotAllowed: '仅可拖放文件! 跳过第 {n} 个文件夹.',
        dropZoneTitle: '在此拖放文件 &hellip;'
    };

    $.extend($.fn.fileinput.defaults, $.fn.fileinput.locales.zh);
})(window.jQuery);