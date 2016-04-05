/**
 * Created by Administrator on 16-4-5.
 */

function doUpload(){
    var formData = new FormData($( "#uploadForm" )[0]);
    $.ajax({
        url:'user/uploadFile',
        type:'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success:function(data){
            if(data.status == "OK"){
                $("#imgShow").attr("src",data.bean);
            }
        }
    })
}

