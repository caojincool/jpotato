/**
 * Created with IntelliJ IDEA.
 * User: dp
 * Date: 13-7-17
 * Time: 上午11:51
 * To change this template use File | Settings | File Templates.
 */
Ext.define('FileDiag', {
    extend: 'Ext.window.Window',
    singleton: true,                //单例模式的好处是在页面中直接用show方法显示就行了
    title: '上传文件',
    width: 300,
    height: 210,
    modal: true,                    //模态:可以在整个页面中动态显示的.
    closeAction: 'hide',            //关闭模式:隐藏
    hideMode: 'offsets',             //隐藏模式:偏移
    resizable: false,               //禁止调整大小


    //先定义好表单
    initComponent: function () {
        var me = this;

        me.progress = Ext.widget('progressbar', {
            text: '上传进度',
            flex: 1
        });

        me.spanid = Ext.id();

        //获取组件的附件
        me.wpfResourceAttachStore = {};
        me.pid='';

        me.items = [
            {
                region: "center",
                layout: 'fit',
                dockedItems: [
                    {
                        xtype: "toolbar",
                        dock: "bottom",
                        items: [
                            { xtype: "tbtext", text: "<span id='" + me.spanid + "'></span>", width: 70 },
                            me.progress
                        ]
                    }
                ]
            }
        ];

        me.on("afterrender", me.onAfterRender);
        me.callParent(arguments);
    },

    onAfterRender: function () {
        var me = this;

        me.swfu = new SWFUpload({
            upload_url: uploadUrl,
            file_size_limit: "10 MB",
            file_types_description: "附件",
            file_upload_limit: 100,
            file_queue_limit: 0,
            file_post_name: "Filedata",
            swfupload_preload_handler: me.UploadPreLoad,
            swfupload_load_failed_handler: me.UploadLoadFailed,
            swfupload_loaded_handler: me.UploadLoaded,
            file_queued_handler: me.fileQueued,
            file_queue_error_handler: me.fileQueueError,
            file_dialog_complete_handler: me.fileDialogComplete,
            upload_start_handler: me.uploadStart,
            upload_progress_handler: me.uploadProgress,
            upload_error_handler: me.uploadError,
            upload_success_handler: me.uploadSuccess,
            upload_complete_handler: me.uploadComplete,
            queue_complete_handler: me.queueComplete,
            // Button settings
            button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
            button_image_url: '',
            button_placeholder_id: me.spanid,
            button_width: 60,
            button_height: 20,
            button_text: '选择文件',
            button_text_style: '',
            button_text_top_padding: 0,
            button_text_left_padding: 0,
            // Flash Settings
            flash_url: "/resource/swfupload/swfupload.swf", // Relative to this file
            // flash9_url: "Scripts/swfupload/swfupload_FP9.swf", // Relative to this file
            custom_settings: { scope: me },
            // Debug Settings
            debug: false
        });
    },

    UploadPreLoad: function () {
    },

    UploadLoadFailed: function () {
    },

    UploadLoaded: function () {

    },

    fileQueued: function () {
    },

    fileQueueError: function (file, errorCode, message) {
        try {
            var dlg = Ext.Msg.alert;
            if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
                dlg("选择的文件太多。\n一次最多上传100个文件，而你选择了" + message + "个文件。");
                return;
            }
            switch (errorCode) {
                case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                    dlg("文件超过了10M.");
                    this.debug("错误代码: File too big, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                    break;
                case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                    dlg("不允许上传0字节文件。");
                    this.debug("Error Code: Zero byte file, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                    break;
                case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                    dlg("非法的文件类型。");
                    this.debug("Error Code: Invalid File Type, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                    break;
                default:
                    if (file !== null) {
                        dlg("未知错误。");
                    }
                    this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                    break;
            }
        } catch (ex) {
            this.debug(ex);
        }
    },

    fileDialogComplete: function (numFilesSelected, numFilesQueued) {
        try {
            if (numFilesQueued > 0) {
                var me = this.customSettings.scope;
                //开始上传
                this.startUpload();
            }
        } catch (ex) {
            this.debug(ex);
        }
    },

    uploadStart: function (file) {
        try {
            var me = this.customSettings.scope;
            me.progress.updateProgress(0);
            me.progress.updateText("正在上传文件" + file.name + "...");
        }
        catch (ex) {
        }

        return true;
    },

    uploadProgress: function (file, bytesLoaded, bytesTotal) {
        try {
            var percent = bytesLoaded / bytesTotal;
            var me = this.customSettings.scope;
            me.progress.updateProgress(percent);
            me.progress.updateText("正在上传文件" + file.name + "...");
        } catch (ex) {
            this.debug(ex);
        }
    },

    uploadError: function (file, errorCode, message) {
        try {
            var me = this.customSettings.scope;
            me.progress.updateText("正在上传文件" + file.name + "...");
            switch (errorCode) {
                case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
                    me.progress.updateText("上传错误：" + message);
                    this.debug("Error Code: HTTP Error, File name: " + file.name + ", Message: " + message);
                    break;
                case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
                    me.progress.updateText("上传失败。");
                    this.debug("Error Code: Upload Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                    break;
                case SWFUpload.UPLOAD_ERROR.IO_ERROR:
                    me.progress.updateText("Server (IO) 错误");
                    this.debug("Error Code: IO Error, File name: " + file.name + ", Message: " + message);
                    break;
                case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
                    me.progress.updateText("安全错误");
                    this.debug("Error Code: Security Error, File name: " + file.name + ", Message: " + message);
                    break;
                case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
                    me.progress.updateText("文件大小超出限制。");
                    this.debug("Error Code: Upload Limit Exceeded, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                    break;
                case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
                    me.progress.updateText("验证失败。");
                    this.debug("Error Code: File Validation Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                    break;
                case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
                    break;
                case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
                    me.progress.updateText("停止");
                    break;
                default:
                    me.progress.updateText("未知错误：" + errorCode);
                    this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                    break;
            }
        } catch (ex) {
            this.debug(ex);
        }
    },

    uploadSuccess: function (file, serverData) {
        try {
            var me = this.customSettings.scope;
            me.progress.updateProgress(1);
            me.progress.updateText(serverData);

        } catch (ex) {
            this.debug(ex);
        }
    },

    uploadComplete: function (file) {
        try {
            if (this.getStats().files_queued > 0) {
                this.startUpload();
            } else {
                var me = this.customSettings.scope;
                me.progress.updateProgress(1);
                me.progress.updateText("所有文件已上传。");
                me.wpfResourceAttachStore.load();
            }
        } catch (ex) {
            this.debug(ex);
        }
    },

    queueComplete: function (numFilesUploaded) {
        var me = this.customSettings.scope;
        me.progress.updateProgress(1);
        me.progress.updateText("已上传" + numFilesUploaded + "个文件");
        if (numFilesUploaded > 0) {
            me.filestore.load();
        }
    }
});