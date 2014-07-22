jQuery.noConflict();

jQuery(document).ready(function(){
	
	/**
	 * This will remove username/password text in the login form fields
	**/
	jQuery('.username, .password').focusout(function(){
		if(jQuery(this).val() != '') {
			jQuery(this).css({backgroundPosition: "0 -32px"});	
		} else {
			jQuery(this).css({backgroundPosition: "0 0"});	
		}
	});
	
	jQuery('.username, .password').focusin(function(){
		if(jQuery(this).val() == '') {
			jQuery(this).css({backgroundPosition: "0 -32px"});	
		}
	});
	
	

	
	/**
	 * Login form validation
	**/
	jQuery('#loginform').submit(function(){
		var username = jQuery('.username').val();
		var password = jQuery('.password').val();
		if(username == '' && password == '') {
			jQuery('.loginNotify').slideDown('fast');	
			return false;
		} else {
			return true;
		}
	});
	
	

	
});