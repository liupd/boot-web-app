;(function($){
/**
 * jqGrid English Translation
 * Tony Tomov tony@trirand.com
 * http://trirand.com/blog/ 
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
**/
$.jgrid = $.jgrid || {};
$.extend($.jgrid,{
	defaults : {
		recordtext: "当前第 {0} - {1} / {2}",
		emptyrecords: "没有数据记录",
		loadtext: "加载中...",
		pgtext : "当前第{0} / {1}"
	},
	search : {
		caption: "搜索...",
		Find: "查找",
		Reset: "重置",
		odata : [{oper:'eq', text:'等于\u3000\u3000'},{oper:'ne', text: '不等\u3000\u3000'}, { oper:'lt', text:'小于\u3000\u3000'},{ oper:'le', text: '小于等于'},{ oper:'gt', text:'大于\u3000\u3000'},{ oper:'ge', text:'大于等于'},
			{oper:'bw', text:'开始于'},{ oper:'bn', text:'不开始于'},{ oper:'in', text:'属于\u3000\u3000'},{ oper:'ni', text:'不属于'},{ oper:'ew', text:'结束于'},{ oper:'en', text:'不结束于'},{ oper:'cn', text:'包含\u3000\u3000'},{ oper:'nc', text:'不包含'},{ oper:'nu', text:'空值于\u3000\u3000'},{ oper:'nn', text:'非空值'}],
		groupOps: [	{ op: "AND", text: "所有" },	{ op: "OR",  text: "任一" }	]
	},
	edit : {
		addCaption: "添加数据",
		editCaption: "编辑数据",
		bSubmit: "提交",
		bCancel: "取消",
		bClose: "关闭",
		saveData: "数据内容已经改变！是否保存数据？",
		bYes : "是",
		bNo : "否",
		bExit : "取消",
		msg: {
			required:"字段不能为空",
			number:"请输入一个合法的数字",
			minValue:"输入值必须小于或等于",
			maxValue:"输入值必须大于或等于",
			email: "不是一个有效的e-mail地址",
			integer: "请输入一个整数",
			date: "请输入一个有效的日期",
			url: "请输入一个合法的ULR地址，必须是以 ('http://' 或 'https://')开始",
			nodefined : "一个不明确的输入",
			novalue : " 必须有一个返回值",
			customarray : "自定义的函数，必须返回一个数组",
			customfcheck : "自定义的检查函数没有通过，请检查"
			
		}
	},
	view : {
		caption: "查看记录",
		bClose: "关闭"
	},
	del : {
		caption: "删除",
		msg: "是否删除已经选中的记录？",
		bSubmit: "确认删除",
		bCancel: "取消"
	},
	nav : {
		edittext: "",
		edittitle: "编辑选中的行",
		addtext:"",
		addtitle: "添加一行数据",
		deltext: "",
		deltitle: "删除选中的行",
		searchtext: "",
		searchtitle: "查找记录",
		refreshtext: "",
		refreshtitle: "重新加载数据",
		alertcap: "告警",
		alerttext: "请选择一行数据",
		viewtext: "",
		viewtitle: "查看一条数据"
	},
	col : {
		caption: "选中列",
		bSubmit: "确认",
		bCancel: "取消"
	},
	errors : {
		errcap : "错误",
		nourl : "不能从url获取set数据",
		norecords: "没有可处理的记录",
		model : "列名的个数和列模型的个数不相等!"
	},
	formatter : {
		integer : {thousandsSeparator: ",", defaultValue: '0'},
		number : {decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, defaultValue: '0.00'},
		currency : {decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "", suffix:"", defaultValue: '0.00'},
		date : {
			dayNames:   [
				"周日", "周一", "周二", "周三", "周四", "周五", "周六",
				"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"
			],
			monthNames: [
				"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月",
				"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"
			],
			AmPm : ["上午","下午","上午","下午"],
			S: function (j) {return j < 11 || j > 13 ? ['st', 'nd', 'rd', 'th'][Math.min((j - 1) % 10, 3)] : 'th';},
			srcformat: 'Y-m-d',
			newformat: 'n/j/Y',
			parseRe : /[Tt\\\/:_;.,\t\s-]/,
			masks : {
				// see http://php.net/manual/en/function.date.php for PHP format used in jqGrid
				// and see http://docs.jquery.com/UI/Datepicker/formatDate
				// and https://github.com/jquery/globalize#dates for alternative formats used frequently
				// one can find on https://github.com/jquery/globalize/tree/master/lib/cultures many
				// information about date, time, numbers and currency formats used in different countries
				// one should just convert the information in PHP format
				ISO8601Long:"Y-m-d H:i:s",
				ISO8601Short:"Y-m-d",
				// short date:
				//    n - Numeric representation of a month, without leading zeros
				//    j - Day of the month without leading zeros
				//    Y - A full numeric representation of a year, 4 digits
				// example: 3/1/2012 which means 1 March 2012
				ShortDate: "n/j/Y", // in jQuery UI Datepicker: "M/d/yyyy"
				// long date:
				//    l - A full textual representation of the day of the week
				//    F - A full textual representation of a month
				//    d - Day of the month, 2 digits with leading zeros
				//    Y - A full numeric representation of a year, 4 digits
				LongDate: "l, F d, Y", // in jQuery UI Datepicker: "dddd, MMMM dd, yyyy"
				// long date with long time:
				//    l - A full textual representation of the day of the week
				//    F - A full textual representation of a month
				//    d - Day of the month, 2 digits with leading zeros
				//    Y - A full numeric representation of a year, 4 digits
				//    g - 12-hour format of an hour without leading zeros
				//    i - Minutes with leading zeros
				//    s - Seconds, with leading zeros
				//    A - Uppercase Ante meridiem and Post meridiem (AM or PM)
				FullDateTime: "l, F d, Y g:i:s A", // in jQuery UI Datepicker: "dddd, MMMM dd, yyyy h:mm:ss tt"
				// month day:
				//    F - A full textual representation of a month
				//    d - Day of the month, 2 digits with leading zeros
				MonthDay: "F d", // in jQuery UI Datepicker: "MMMM dd"
				// short time (without seconds)
				//    g - 12-hour format of an hour without leading zeros
				//    i - Minutes with leading zeros
				//    A - Uppercase Ante meridiem and Post meridiem (AM or PM)
				ShortTime: "g:i A", // in jQuery UI Datepicker: "h:mm tt"
				// long time (with seconds)
				//    g - 12-hour format of an hour without leading zeros
				//    i - Minutes with leading zeros
				//    s - Seconds, with leading zeros
				//    A - Uppercase Ante meridiem and Post meridiem (AM or PM)
				LongTime: "g:i:s A", // in jQuery UI Datepicker: "h:mm:ss tt"
				SortableDateTime: "Y-m-d\\TH:i:s",
				UniversalSortableDateTime: "Y-m-d H:i:sO",
				// month with year
				//    Y - A full numeric representation of a year, 4 digits
				//    F - A full textual representation of a month
				YearMonth: "F, Y" // in jQuery UI Datepicker: "MMMM, yyyy"
			},
			reformatAfterEdit : false
		},
		baseLinkUrl: '',
		showAction: '',
		target: '',
		checkbox : {disabled:true},
		idName : 'id'
	}
});
})(jQuery);
