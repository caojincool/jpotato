
jQuery(function() {
    //首页轮播图1
    jQuery("#avatarUpload").uploadify({
        'auto'				: false,
        'multi'				: false,
        'uploadLimit'		: 1,
        'formData'			: {'uid':'18'},
        'buttonText'		: '请选择图片',
        'height'			: 20,
        'width'				: 120,
        'removeCompleted'	: false,
        'swf'				: 'jquery/uploadify/uploadify.swf',
        'uploader'			: '/help/document/'+pid+'/uploadImage;jsessionid='+sessionid,
        'fileTypeExts'		: '*.gif; *.jpg; *.jpeg; *.png;',
        'fileSizeLimit'		: '4024KB',
        'onUploadSuccess' : function(file, data, response) {
            var msg = jQuery.parseJSON(data);
            if( msg.success ){
                jQuery("#img").val( msg.message );
                msg.message=msg.message.replace(/\\/g,"/");
                jQuery("#target").attr("src",msg.message);
                jQuery(".preview").attr("src",msg.message);
                if(jcrop_api!=null){
                    jcrop_api.setImage(msg.message )
                }
                jQuery('#target').Jcrop({
                        minSize: [80,80],
                        setSelect: [0,0,200,200],
                        onChange: updatePreview,
                        onSelect: updatePreview,
                        onSelect: updateCoords,
                        aspectRatio: 1
                    },
                    function(){
                        // Use the API to get the real image size
                        var bounds = this.getBounds();
                        boundx = bounds[0];
                        boundy = bounds[1];
                        // Store the API in the jcrop_api variable
                        jcrop_api = this;
                    });
                jQuery("#preViews").show();
                jQuery("#scrView").show();
                jQuery(".imgchoose").show(1000);
                jQuery("#avatar_submit").show(1000);
            } else {
                alert(msg.message);
            }
        },
        'onClearQueue' : function(queueItemCount) {
            alert( jQuery('#img1') );
        },
        'onCancel' : function(file) {
            alert('The file ' + file.name + ' was cancelled.');
        }
    });

    //头像裁剪
    var jcrop_api=null, boundx, boundy;

    function updateCoords(c)
    {
        jQuery('#x').val(c.x);
        jQuery('#y').val(c.y);
        jQuery('#w').val(c.w);
        jQuery('#h').val(c.h);
    };
    function checkCoords()
    {
        if (parseInt(jQuery('#w').val()))
            return true;
        //alert('请选择图片上合适的区域');
        return false;
    };
    function updatePreview(c){
        if (parseInt(c.w) > 0){
            var rx = 80 / c.w;
            var ry = 80 / c.h;
            jQuery('#preview').css({
                width: Math.round(rx * boundx) + 'px',
                height: Math.round(ry * boundy) + 'px',
                marginLeft: '-' + Math.round(rx * c.x) + 'px',
                marginTop: '-' + Math.round(ry * c.y) + 'px'
            });
        }
        {
            var rx = 150 / c.w;
            var ry = 150 / c.h;
            jQuery('#preview2').css({
                width: Math.round(rx * boundx) + 'px',
                height: Math.round(ry * boundy) + 'px',
                marginLeft: '-' + Math.round(rx * c.x) + 'px',
                marginTop: '-' + Math.round(ry * c.y) + 'px'
            });
        }
    };
    /**
     *
     * @param preViemImageFlag 图片保存是否成功
     * @param detailsFlag 详细更新是否成功
     */

    $("#submitBtn").click(function(){
        saveImage();
    });

    $("#next").click(function(){
        flag=1;
        saveImage();
    });
    $("#finish").click(function(){
        flag=2;
        saveImage();
    });
    function saveImage(){
        var img = $("#img").val();
        var x = $("#x").val();
        var y = $("#y").val();
        var w = $("#w").val();
        var h = $("#h").val();
        var preViemImageFlag=false;//预览图提交成功与否标识
        var detailsFlag=false;//详情提交成功与否标识
        if(checkCoords()){
            //执行保存切割预览图信息
            $.ajax({
                type: "POST",
                url: "/help/document/"+pid+"/cutPreviewImage",
                data: {"srcPath":img,"x":x,"y":y,"width":w,"height":h},
                dataType: "json",
                success: function(msg){
                    // var msg = jQuery.parseJSON(data);
                    if( msg.success ){
                        preViemImageFlag=true;
                        jumpPage(preViemImageFlag,detailsFlag);
                    } else {
                        jQuery( "#message").html(msg.message);
                        jQuery( "#dialog-message" ).dialog({
                            modal: true
                        });

                    }
                }
            });
        }else{
            preViemImageFlag=true;
            jumpPage(preViemImageFlag,detailsFlag);
        }
        //执行保存组件详情信息
        $.ajax({
            type: "POST",
            url: "/help/document/"+pid+"/updateDetails",
            data: {"details":editor.getContent()},
            success: function(msg){
                if( msg.success ){
                    detailsFlag=true;
                    jumpPage(preViemImageFlag,detailsFlag);
                } else {
                    jQuery( "#message").html(msg.message);
                    jQuery( "#dialog-message" ).dialog({
                        modal: true
                    });
                }
            }
        });
    }
});