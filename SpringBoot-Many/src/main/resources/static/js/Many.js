/**
 * 
 */
// 退出登录
function exitLogin() {
	window.location.href = "http://192.168.1.55:1111/exitLogin";
}
// 弹出层窗口 修改个人信息页面
function updateUser() {
	var id = document.getElementById("loginId").innerHTML;
	var nav = "editUI"
	layer.open({
		type : 2,
		title : '修改个人信息',
		shadeClose : true,
		shade : 0.8,
		area : [ '480px', '60%' ],
		content : nav
	// iframe的url
	});
}
// 弹出层窗口 增加用户网址页面
function addWeb() {
	layer.open({
		type : 2,
		title : '添加学习资料',
		shadeClose : true,
		shade : 0.8,
		area : [ '480px', '60%' ],
		content : "addWebUI" // iframe的url
	});
}
// 删除
function deleteModel(deleteUrl) {
	// var obj,check_val;
	obj = document.getElementsByName("checkbox");
	check_val = [];
	for (k in obj) {
		if (obj[k].checked)
			check_val.push(obj[k].value);
	}
	if (check_val == null || check_val == "") {
		layer.msg("请选择", {
			icon : 2
		});
		return;
	}
	// alert(check_val);
	var rootPath = "http://192.168.1.55:1111/";
	$.ajax({
		type : "POST",
		url : rootPath + deleteUrl,
		data : {
			// check_val是数组，后端需要的是String类型的，所以需要转型
			"ids" : check_val.toString()
		},
		dataType : "json",

		success : function(resultdata) {
			/**
			 * 因为删除图片与删除学习网址成功之后，跳转的页面不一样，所以是不同的逻辑处理
			 */
			// alert(resultdata.status)
			// 删除图片时触发的逻辑
			// 通过接收Ret类来获取返回结果
			if (resultdata.status == "S") {
				layer.msg(resultdata.data, {
					icon : 1
				});
				// 1s后刷新
				setTimeout(function() { // 使用 setTimeout（）方法设定定时1000毫秒
					location.replace(rootPath + "img/downloadfile");// 页面刷新
				}, 1000);
			} else if (resultdata.message) {
				window.location.href = "http://192.168.1.55:1111/errorHtml";

				// 通过接收map来获取返回结果
			} else if (resultdata.success) {

				layer.msg(resultdata.success, {
					icon : 1
				});
				setTimeout(function() { // 使用 setTimeout（）方法设定定时2000毫秒
					window.location.reload();// 刷新本页面
				}, 1000);
			} else {
				layer.msg(resultdata.error, {
					icon : 2
				});
				setTimeout(function() { // 使用 setTimeout（）方法设定定时2000毫秒
					window.location.reload();// 刷新本页面
				}, 1000);
			}
		},
		error : function(data, errorMsg) {

			layer.msg("系统繁忙,请稍后重试", {
				icon : 2
			});
		}
	});
}

// 关闭窗口
function closeModel() {
	var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
	parent.layer.close(index);
}
var rootPath = "http://192.168.1.55:1111";
// 修改信息后提交
function commit(formId, commitUrl, params) {
	// 表单验证
	var valid = $("#" + formId).data('formValidation');
	if (valid != undefined) {
		reValidate(formId);
		valid.validate();
		if (!valid.isValid()) {
			return;
		}
	}
	// 组装表单数据
	var data = $("#" + formId).serialize();
	if (params) {
		data = params;
	}
	$.ajax({
		type : "POST",
		url : rootPath + commitUrl,
		data : data,
		dataType : "json",
		success : function(resultdata) {
			// alert(resultdata.message)
			// 通过接收map来获取返回结果
			if (resultdata.success) {
				layer.msg(resultdata.success, {
					icon : 1
				});
				// 2s后刷新父窗口
				setTimeout(function() { // 使用 setTimeout（）方法设定定时2000毫秒
					parent.location.href = parent.location.href;// 页面刷新
					// 关闭窗口
					closeModel();
				}, 1000);
			} else {
				layer.msg(resultdata.error, {
					icon : 2
				});
			}
		},
		error : function(data, errorMsg) {
			layer.msg("系统繁忙,请稍后重试", {
				icon : 2
			});
		}
	});
}
// 分页显示图片
function goImg() {
	var pageNum = document.getElementById("pageNum").innerHTML;
	var goToPage = document.getElementById("goToPage").value;
	if (goToPage <= 0 || goToPage > total) {
		window.location.href = '/img/downloadfile?pageNum=' + pageNum;
	} else {
		window.location.href = '/img/downloadfile?pageNum=' + goToPage;
	}
}

// 分页显示学习网站内容
$(function() {
	$("#go").click(function go() {
		var pageNum = document.getElementById("pageNum").innerHTML;
		var goToPage = document.getElementById("goToPage").value;
		if (goToPage <= 0 || goToPage > total) {
			window.location.href = '/learn/learns?pageNum=' + pageNum;
		} else {
			window.location.href = '/learn/learns?pageNum=' + goToPage;
		}
	});
})
/**
 * 对上传的图片进行大小格式的判断
 * 
 * @param target
 * @returns
 */
function fileImgChange(target) {
	var fileSize = 0;
	if (!target.files) {
		var filePath = target.value;
		var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
		var file = fileSystem.GetFile(filePath);
		fileSize = file.Size;
	} else {
		fileSize = target.files[0].size;
	}
	var size = fileSize / 1024;
	if (size > 2000) {
		layer.alert('附件不能大于2M', {
			skin : 'layui-layer-molv' // 样式类名
			,
			closeBtn : 0
		});
		target.value = "";
		return
	}
	var name = target.value;
	var fileName = name.substring(name.lastIndexOf(".") + 1).toLowerCase();
	if (fileName != "jpg" && fileName != "jpeg" && fileName != "pdf"
			&& fileName != "png" && fileName != "dwg" && fileName != "gif") {
		layer.alert('请选择图片格式文件上传(jpg,png,gif,dwg,pdf等)', {
			skin : 'layui-layer-molv' // 样式类名
			,
			closeBtn : 0
		});
		target.value = "";
		return
	}
}
/**
 * 到处用户学习网址的信息到本地excel中
 * 
 * @param outExcelUrl
 * @returns
 */
function outExcel(outExcelUrl) {
	var pathName = "D:/";
	var rootPath = "http://192.168.1.55:1111/";
	$.ajax({
			type : "POST",
			url : rootPath + outExcelUrl,
			data : {
				// check_val是数组，后端需要的是String类型的，所以需要转型
				"pathName" : pathName
			},
			dataType : "json",
			success : function(resultdata) {
				// alert(resultdata.message)
				if (resultdata.status == "S") {
					// Ajax传输成功后，让页面下载learnData.xls 文件
					window.location.href = "http://192.168.1.55:1111/excel/learnData.xls";
					// 1s后刷新
					setTimeout(function() { // 使用 setTimeout（）方法设定定时1000毫秒
						// window.location.reload();// 页面刷新
						layer.msg(resultdata.data, {
							icon : 1
							});
						}, 1000);
				} else {
					layer.msg(resultdata.message, {
						icon : 2
					});
					// 1s后刷新
					setTimeout(function() {
						window.location.reload();// 页面刷新
					}, 1000);
				}
			},
			error : function(data, errorMsg) {
				layer.msg("系统繁忙,请稍后重试", {
					icon : 2
				});
			}
		});
}
