function JoinConfig(id,array,callback){
	this.$array=array;
	this.$id=id;
	this.$callback=callback;
}
JoinConfig.prototype={
	getValue:function(){//获取json字符串
		return $.toJSON(this.$array);
	},
	
	init:function(){// 初始化
		for(var i=0;i<this.$array.length;i++){
			this.appendHtml(this.$array[i],i);
		}
	},
	
	validate:function(){//验证 各栏位是否一合法
		for(var i=0;i<this.$array.length;i++){
			if($.trim(this.$array[i].name)==''){
				alert("请填写栏位名称");
				return false;
			}
			if(this.$array[i].type=='radio'||this.$array[i].type=='checkbox'||this.$array[i].type=='select'){
				if(this.$array[i].items.length==0){
					alert("请为栏位添加选项");
					return false;
				}
				for(var j=0;j<this.$array[i].items.length;j++){
					if($.trim(this.$array[i].items[j])==''){
						alert("请输入选项值");
						return false;
					}
				}
			}
		}
		return true;
	},
	
	createOption:function(type){//创建栏位
		var option={};
		option.type=type;
		option.require=false;
		option.name='';
		option.placeholder='';
		option.items=new Array();
		this.$array.push(option);
		this.appendHtml(option,this.$array.length-1);
	},
	
	removeOption:function(o){// 删除栏位
		var i=this.getOptionIndex(o);
		this.$array.splice(i,1);
		$(o).parents('.bitian').first().remove();
		this.refreshIndex();
	},
	
	length:function(){// 栏位个数
		return this.$array.length;
	},
	
	moveOption:function(o,up){
		// 移动栏位,根据o获取栏位位置，根据位置进行移动并一定两个html，将他们的值data-index也换掉
		var parent=$(o).parents('.bitian').first();
		var i=this.getOptionIndex(o);
		if(up&&i>0){
			var newArray=new Array();
			for(var index=0;index<this.$array.length;index++){
				if(index==i-1){
					newArray.push(this.$array[i]);
					newArray.push(this.$array[i-1]);
				}else if(index==i){
					continue;
				}else{
					newArray.push(this.$array[index]);
				}
			}
			this.$array=newArray;
			var before=parent.prev();
			if(before.size()>0)
				before.insertAfter(parent);
		}
		if(!up&&i<this.$array.length-1){
			var newArray=new Array();
			for(var index=0;index<this.$array.length;index++){
				if(index==i){
					newArray.push(this.$array[i+1]);
					newArray.push(this.$array[i]);
				}else if(index==i+1){
					continue;
				}else{
					newArray.push(this.$array[index]);
				}
			}
			this.$array=newArray;
			var after=parent.next();
			if(after.size()>0)
				after.insertBefore(parent);
		}
		this.refreshIndex();
	},
	
	createItem:function(o){
		// 增加子选项 根据o获取当前位置,根据位置添加栏位子选项
		var i=this.getOptionIndex(o);
		this.$array[i].items.push('');
		var itemhtml=new Array();
		itemhtml.push('<span data-index="'+(this.$array[i].items.length-1)+'"><a href="javascript:;" onclick="'+this.$callback+'.removeItem(this);" class="ss"><i><img src="../images/00.png"></i></a><input onblur="'+this.$callback+'.setItemName(this);" type="text" class="shuruk">');
		itemhtml.push('<label style="color:#F00; line-height:30px;">*</label></span>');
		$(o).prev().append(itemhtml.join(''));
	},
	
	setName:function(o){
		//获取位置之后设置option的name
		var i=this.getOptionIndex(o);
		this.$array[i].name=$(o).val();
	},
	
	setPlaceholder:function(o){
		//获取位置之后设置option的placeholder
		var i=this.getOptionIndex(o);
		this.$array[i].placeholder=$(o).val();
	},
	
	setRequire:function(o){
		//获取位置之后设置option的require
		var i=this.getOptionIndex(o);
		if($(o).prop('checked')){
			this.$array[i].require=true;
		}else{
			this.$array[i].require=false;
		}
	},
	
	removeItem:function(o){
		// 删除子选项,根据o活去当前位置,根据位置获取栏位和当前选项
		var i=this.getOptionIndex(o);
		var j=this.getItemIndex(o);
		this.$array[i].items.splice(j,1);
		$(o).parents('span').first().remove();
		this.refreshItemIndex(i);
	},
	
	setItemName:function(o){
		//获取位置之后设置item的值
		var i=this.getOptionIndex(o);
		var j=this.getItemIndex(o);
		this.$array[i].items[j]=$(o).val();
	},
	
	getOptionIndex:function(o){
		//获取栏位的位置
		return parseInt($(o).parents('.bitian').first().attr('data-index'));
	},
	
	getItemIndex:function(o){
		//获取选项的位置
		return parseInt($(o).parents('span').first().attr('data-index'));
	},
	
	refreshIndex:function(){
		//刷新位置
		$('#'+this.$id).find('.bitian').each(function(i,o){
			$(o).attr("data-index",i);
		});
	},
	
	refreshItemIndex:function(i){
		//刷新选项位置
		$('#'+this.$id).find('.bitian').eq(i).find('.formItems').first().children().each(function(i,o){
			$(o).attr("data-index",i);
		});
	},
	
	appendHtml:function(option,i){
		if(option.type=='radio'||option.type=='checkbox'||option.type=='select'){
			var hasItemsHtml=new Array();
			hasItemsHtml.push('<div style="width:595px;margin-top:10px;" class="bitian" data-index="'+i+'">');
			hasItemsHtml.push('<div class="left bitian0"><input onclick="'+this.$callback+'.setRequire(this)" '+(option.require?'checked="checked"':'')+' type="checkbox" value="">必填</div>');
			hasItemsHtml.push('<div class="left kehuxinxi kehuxinxi0"><div class="left bmrmc">'+this.getText(option.type)+'</div><div class="left xm xm0"><input onblur="'+this.$callback+'.setName(this);" type="text" value="'+option.name+'"></div><div class="clear"></div></div><div style="color:#F00; float:left; line-height:30px;">*</div>');
			hasItemsHtml.push('<div class="left kehuxinxi kehuxinxi0">');
			hasItemsHtml.push('<div class="left bmrmc">提示</div><div class="left xm xm0"><input onblur="'+this.$callback+'.setPlaceholder(this);" type="text" value="'+option.placeholder+'"></div><div class="clear"></div></div>');
			hasItemsHtml.push('<a href="javascript:;" onclick="'+this.$callback+'.moveOption(this,true);" class="ss"><i><img src="../images/glyphicons-halflings_03.gif" width="9" height="12"></i></a>');
			hasItemsHtml.push('<a href="javascript:;" onclick="'+this.$callback+'.moveOption(this,false);" class="ss"><i><img src="../images/glyphicons-halflings_05.png" width="9" height="12"></i></a>');
			hasItemsHtml.push('<a href="javascript:;" onclick="'+this.$callback+'.removeOption(this);" class="ss"><i><img src="../images/00.png"></i></a>');
			hasItemsHtml.push('<div class=" clear"></div>');
			hasItemsHtml.push('<div>');
			hasItemsHtml.push('<div class="left bitian0">选项列表：</div>');
			hasItemsHtml.push('<div class="left formItems" style="width:440px;">');
			for(var index=0;index<option.items.length;index++){
				hasItemsHtml.push('<span data-index="'+index+'"><a href="javascript:;" onclick="'+this.$callback+'.removeItem(this);" class="ss"><i><img src="../images/00.png"></i></a><input onblur="'+this.$callback+'.setItemName(this);" value="'+option.items[index]+'" type="text" class="shuruk">');
				hasItemsHtml.push('<label style="color:#F00; line-height:30px;">*</label></span>');
			}
			hasItemsHtml.push('</div>');
			hasItemsHtml.push('<a href="javascript:;" onclick="'+this.$callback+'.createItem(this);" class="ss"><i><img src="../images/11.png"></i></a>');
			hasItemsHtml.push('<div class=" clear"></div>');
            hasItemsHtml.push('</div>');
			hasItemsHtml.push('</div>');
			$('#'+this.$id).append(hasItemsHtml.join(''));
		}else{
			var noItemsHtml=new Array();
			noItemsHtml.push('<div style="width:595px;margin-top:10px;" class="bitian" data-index="'+i+'">');
			noItemsHtml.push('<div class="left bitian0"><input onclick="'+this.$callback+'.setRequire(this)" '+(option.require?'checked="checked"':'')+' type="checkbox" value="">必填</div>');
			noItemsHtml.push('<div class="left kehuxinxi kehuxinxi0"><div class="left bmrmc">'+this.getText(option.type)+'</div><div class="left xm xm0"><input onblur="'+this.$callback+'.setName(this);" type="text" value="'+option.name+'"></div><div class="clear"></div></div><div style="color:#F00; float:left; line-height:30px;">*</div>');
			noItemsHtml.push('<div class="left kehuxinxi kehuxinxi0">');
			noItemsHtml.push('<div class="left bmrmc">提示</div><div class="left xm xm0"><input onblur="'+this.$callback+'.setPlaceholder(this);" type="text" value="'+option.placeholder+'"></div><div class="clear"></div></div>');
			noItemsHtml.push('<a href="javascript:;" onclick="'+this.$callback+'.moveOption(this,true);" class="ss"><i><img src="../images/glyphicons-halflings_03.gif" width="9" height="12"></i></a>');
			noItemsHtml.push('<a href="javascript:;" onclick="'+this.$callback+'.moveOption(this,false);" class="ss"><i><img src="../images/glyphicons-halflings_05.png" width="9" height="12"></i></a>');
			noItemsHtml.push('<a href="javascript:;" onclick="'+this.$callback+'.removeOption(this);" class="ss"><i><img src="../images/00.png"></i></a>');
			noItemsHtml.push('<div class=" clear"></div>');
			noItemsHtml.push('</div>');
			$('#'+this.$id).append(noItemsHtml.join(''));
		}
	},
	
	getText:function(type){
		if(type=='text')
			return "单行文本框";
		if(type=='number')
			return "多行文本框";
		if(type=='date')
			return "日期选择框";
		if(type=='email')
			return "邮箱输入框";
		if(type=='textarea')
			return "多行文本框";
		if(type=='radio')
			return "单选按钮框";
		if(type=='checkbox')
			return "多选按钮框";
		if(type=='select')
			return "下拉选择框";
	},
	
	addCompany:function(){
		var option={};
		option.type="text";
		option.require=false;
		option.name='公司';
		option.placeholder='';
		option.items=new Array();
		this.$array.push(option);
		this.appendHtml(option,this.$array.length-1);
	},
	
	addDepartment:function(){
		var option={};
		option.type="text";
		option.require=false;
		option.name='部门';
		option.placeholder='';
		option.items=new Array();
		this.$array.push(option);
		this.appendHtml(option,this.$array.length-1);
	},
	
	addPosition:function(){
		var option={};
		option.type="text";
		option.require=false;
		option.name='职位';
		option.placeholder='';
		option.items=new Array();
		this.$array.push(option);
		this.appendHtml(option,this.$array.length-1);
	},
	
	addInterest:function(){
		var option={};
		option.type="checkbox";
		option.require=false;
		option.name='兴趣爱好';
		option.placeholder='';
		option.items=['文艺','音乐','运动','数码','购物','读书','旅游','时尚','其他'];
		this.$array.push(option);
		this.appendHtml(option,this.$array.length-1);
	},
	
	addBlood:function(){
		var option={};
		option.type="radio";
		option.require=false;
		option.name='血型';
		option.placeholder='';
		option.items=['A型','B型','O型','AB型'];
		this.$array.push(option);
		this.appendHtml(option,this.$array.length-1);
	},
	
	addMarriage:function(){
		var option={};
		option.type="radio";
		option.require=false;
		option.name='婚姻状况';
		option.placeholder='';
		option.items=['未婚','已婚','保密'];
		this.$array.push(option);
		this.appendHtml(option,this.$array.length-1);
	},
	
	addSex:function(){
		var option={};
		option.type="radio";
		option.require=false;
		option.name='性别';
		option.placeholder='';
		option.items=['男','女'];
		this.$array.push(option);
		this.appendHtml(option,this.$array.length-1);
	},
	
	addAge:function(){
		var option={};
		option.type="radio";
		option.require=false;
		option.name='年龄';
		option.placeholder='';
		option.items=['15岁一下','16~20岁','21~25岁','26~30岁','31~40岁','40岁以上'];
		this.$array.push(option);
		this.appendHtml(option,this.$array.length-1);
	},
	
	addSchooling:function(){
		var option={};
		option.type="radio";
		option.require=false;
		option.name='学历';
		option.placeholder='';
		option.items=['小学','中学','大专','本科','研究生及以上'];
		this.$array.push(option);
		this.appendHtml(option,this.$array.length-1);
	},
	
	addAddress:function(){
		var option={};
		option.type="select";
		option.require=false;
		option.name='住址';
		option.placeholder='';
		option.items=['北京','上海','广州','深圳'];
		this.$array.push(option);
		this.appendHtml(option,this.$array.length-1);
	},
	
	addEarn:function(){
		var option={};
		option.type="radio";
		option.require=false;
		option.name='月收入';
		option.placeholder='';
		option.items=['3000以下','3000~5000','5000~10000','10000~20000','20000以上'];
		this.$array.push(option);
		this.appendHtml(option,this.$array.length-1);
	},
	
	addOther:function(){
		var option={};
		option.type="textarea";
		option.require=false;
		option.name='其他';
		option.placeholder='';
		option.items=[];
		this.$array.push(option);
		this.appendHtml(option,this.$array.length-1);
	}
}