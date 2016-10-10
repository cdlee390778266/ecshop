$(document).ready(function(){
	$('form').on("submit", function(event){
		console.log(b64_sha1($("#passwd").val()));
		$("#passwd").val(b64_sha1($("#passwd").val()));
	});

    $(document).ready(function() {
                $('.fixed-wrapper').stickUp();
        });
});