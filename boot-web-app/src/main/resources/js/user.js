/**
 * Created by Administrator on 16-3-31.
 */
var grid_table="#data-table";
var grid_pager="#grid-pager";

function addRow(){
    $("#FormError").remove();
    fullscreenLoading();
    var postData={
        name:$("#name").val(),
        code:$("#code").val(),
        password:$("#password").val(),
        email:$("#email").val()
    };
    postData=JSON.stringify(postData);
    $.ajax({
        type: "POST",
        url: '/user/addOrUpdateUser',
        dataType: 'json',
        contentType: "application/json",
        data:postData,
        success: function (data) {
            if (data.status == "OK") {
                removefullscreenLoading();
                $("#cData").click();
                $(grid_table).trigger("reloadGrid");
                alert("保存成功！");
            }
        },
        error: function () {
            removefullscreenLoading();
            console.log("保存失败！")
        }
    });
}


function editRow(){
    $("#FormError").remove();
    var id =$(grid_table).getGridParam("selrow");
    if(null==id||""==id||"null"==id){
        alert("请选择一行信息");
        return ;
    }
    fullscreenLoading();
    var postData={
        id:id,
        name:$("#name").val(),
        code:$("#code").val(),
        password:$("#password").val(),
        email:$("#email").val()
    };
    postData=JSON.stringify(postData);
    $.ajax({
        type: "POST",
        url: '/user/addOrUpdateUser',
        dataType: 'json',
        contentType: "application/json",
        data:postData,
        success: function (data) {
            if (data.status == "OK") {
                $("#cData").click();
                $(grid_table).trigger("reloadGrid");
                removefullscreenLoading();
                alert("保存成功！");
            }
        },
        error: function () {
            removefullscreenLoading();
            console.log("保存失败！")
        }
    });
}



function deleteRow(){
    $("#DelError").remove();
    var id =$(grid_table).getGridParam("selrow");
    if(null==id||""==id||"null"==id){
        alert("请选择一行信息");
        return ;
    }
    fullscreenLoading();
    $.ajax({
        type: "POST",
        url: '/user/'+id,
        dataType: 'json',
        contentType: "application/json",
        success: function (data) {
            if (data.status == "OK") {
                removefullscreenLoading();
                $("#eData").click();
                $(grid_table).trigger("reloadGrid");
                alert("删除成功！");
            }
        },
        error: function () {
            removefullscreenLoading();
            console.log("删除失败！")
        }
    });
}

jQuery(document).ready(function(){
    jQuery(grid_table).jqGrid({
        datatype: "json", //将这里改为使用JSON数据
        url:'user/findUsersByPage', //这是Action的请求地址
        mtype:'POST',
        height:250  ,
        colNames:[' ','ID','账号','名称','邮箱','密码','创建时间'],
        colModel:[
            {name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
                formatter:'actions',
                formatoptions:{
                    keys:true,
                    delOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback},
                    editformbutton:true,
                    editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}}
                },
                {name: 'id',index:'id', width:60, hidden:true,editrules:{edithidden:true}},
                {name:'code',index:'code',width:60,editable: true},
                {name: 'name',index:'name',  width:60,editable: true},
                {name:'email',index:'email',width:60,editable: true},
                {name:'password',index:'password',width:60,editable: true},
                //{name:'email',index:'email',hidden:false,editable: true,edittype: "select", editoptions: {dataUrl: 'metaData/findPhases', type: "post"},editrules:{edithidden:true}},
                {name: 'createTime',index:'createTime',  width:60}
            ],
            rowNum: 10,
            rowList: [ 10, 20, 30],
            gridview: true,
            pager: grid_pager,
            altRows: true,
            caption: "人员列表",
            autowidth: true,
            viewrecords: true,
            loadComplete : function() {
                var table = this;
                setTimeout(function(){
                updatePagerIcons(table);
                enableTooltips(table);
                }, 0);
            }
        })
        jQuery(grid_table).jqGrid('navGrid',grid_pager,
            { 	//navbar options
                edit: true,add: true,del: true,search: false,refresh: true,view: true
                },
            {
                //edit record form
                //closeAfterEdit: true,
                recreateForm: true,
                beforeShowForm : function(e) {
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                style_edit_form(form);
                }
            },
            {
                //new record form
                closeAfterAdd: true,
                recreateForm: true,
                viewPagerButtons: false,
                beforeShowForm : function(e) {
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                style_new_form(form);
                }
            },
            {
                //delete record form
                recreateForm: true,
                beforeShowForm : function(e) {
                var form = $(e[0]);
                if(form.data('styled')) return false;

                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                style_delete_form(form);

                form.data('styled', true);
                }
            },
            {
                //view record form
                recreateForm: true,
                beforeShowForm: function(e){
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                }
            })
            function style_new_form(form) {
                //enable datepicker on "sdate" field and switches for "stock" field
                    form.find('input[name=sdate]').datepicker({format:'yyyy-mm-dd' , autoclose:true})
                .end().find('input[name=stock]')
                .addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');

                //update buttons classes
                var buttons = form.next().find('.EditButton .fm-button');
                buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
                buttons.eq(0).attr('onclick','addRow()').addClass('btn-primary').prepend('<i class="icon-ok"></i>');
                buttons.eq(1).prepend('<i class="icon-remove"></i>')

                buttons = form.next().find('.navButton a');
                buttons.find('.ui-icon').remove();
                buttons.eq(0).append('<i class="icon-chevron-left"></i>');
                buttons.eq(1).append('<i class="icon-chevron-right"></i>');
            }
            function style_edit_form(form) {
                    //enable datepicker on "sdate" field and switches for "stock" field
                    form.find('input[name=sdate]').datepicker({format:'yyyy-mm-dd' , autoclose:true})
                .end().find('input[name=stock]')
                .addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');

                //update buttons classes
                var buttons = form.next().find('.EditButton .fm-button');
                buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
                buttons.eq(0).attr('onclick','editRow()').addClass('btn-primary').prepend('<i class="icon-ok"></i>');
                buttons.eq(1).prepend('<i class="icon-remove"></i>')

                buttons = form.next().find('.navButton a');
                buttons.find('.ui-icon').remove();
                buttons.eq(0).append('<i class="icon-chevron-left"></i>');
                buttons.eq(1).append('<i class="icon-chevron-right"></i>');
            }

        function style_delete_form(form) {
            var buttons = form.next().find('.EditButton .fm-button');
            buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
            buttons.eq(0).attr('onclick','deleteRow()').addClass('btn-danger').prepend('<i class="icon-trash"></i>');
            buttons.eq(1).prepend('<i class="icon-remove"></i>')
        }

        function beforeDeleteCallback(e) {
            var form = $(e[0]);
            if(form.data('styled')) return false;
            form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
            style_delete_form(form);
            form.data('styled', true);
        }

        function beforeEditCallback(e) {
            var form = $(e[0]);
            form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
            style_edit_form(form);
        }

        function updatePagerIcons(table) {
            var replacement =
            {
            'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
            'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
            'ui-icon-seek-next' : 'icon-angle-right bigger-140',
            'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
            };
        $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
            var icon = $(this);
            var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

            if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
            })
        }
        function enableTooltips(table) {
            $('.navtable .ui-pg-button').tooltip({container:'body'});
        $(table).find('.ui-pg-div').tooltip({container:'body'});
        }
    });