

jQuery(document).ready(function(){
    jQuery('.notification .close').click(function(){
        jQuery(this).parent().fadeOut();
    });
    /**
	 * Sidebar accordion
	**/
	jQuery('#accordion h3').click(function() {
		if(jQuery(this).hasClass('open')) {
			jQuery(this).removeClass('open');
			jQuery(this).next().slideUp('fast');
		} else {
			jQuery(this).addClass('open');
			jQuery(this).next().slideDown('fast');
		} return false;
	});
    jQuery('#accordion h4').click(function() {
        if(jQuery(this).hasClass('open')) {
            jQuery(this).removeClass('open');
            jQuery(this).next().slideUp('fast');
        } else {
            jQuery(this).addClass('open');
            jQuery(this).next().slideDown('fast');
        } return false;
    });
    jQuery('#accordion2 h3').click(function() {
        if(jQuery(this).hasClass('open')) {
            jQuery(this).removeClass('open');
            jQuery(this).next().slideUp('fast');
        } else {
            jQuery(this).addClass('open');
            jQuery(this).next().slideDown('fast');
        } return false;
    });
	

	
	/** Make footer always at the bottom**/
	if(jQuery('body').height() > jQuery(window).height()) {
		jQuery('.footer').removeClass('footer_float');
	}
	
	
	


	
});
/**
 * 关闭浏览器无提示
 */
function closeWin(){
    window.opener=null;
    window.open('','_self');
    window.close();
}
/**
 * 组件查询
 * @param number
 */
function sub(number){
    if(number)
        document.frmQuery.page.value=number;
    else
        document.frmQuery.page.value=1;

    var form=document.getElementById('query');
    form.submit();
}
/**
 * 预览 可能要废弃掉
 * @param cate
 * @param pid
 */
function show(cate,pid){
    if (cate == 'TABELGP5' || cate == 'WPFSKIN') {
        var url = 'ilemsun://command/resource=' +pid.toUpperCase() + '&token=&sfdsd=slskdj&action=open';
        window.open(url);
    } else if (cate == 'WEBSKIN') {
        var url = clientUrl +"/"+ pid.toUpperCase();
        window.open(url);
    } else if(cate == 'IMAGE'){
        var url = "/component/image/content/get?pid="+ pid.toUpperCase();
        window.open(url);
    }else {
        jQuery( "#message").html("此组件预览功能正在开发阶段...\r\n请关注开发论坛");
        jQuery( "#dialog-message" ).dialog({
            modal: true,
            buttons: {
                "确定": function() {
                    jQuery( this ).dialog( "close" );
                }
            }
        });
    }

}