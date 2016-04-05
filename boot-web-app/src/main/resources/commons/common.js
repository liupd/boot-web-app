
function fullscreenLoading(text) {
    if(text==undefined){
        text = '正在执行...'
    }
    var Loading = '<div id="fullLoading" style="z-index:99999  !important;  "><i class="icon-spinner icon-spin  bigger-125"></i>' + text + '</div><div  style="z-index:99998 !important;" class="ui-widget-overlay"></div>';
    $("body").append(Loading);
}

function removefullscreenLoading() {
    $("#fullLoading,.ui-widget-overlay").remove();
}



