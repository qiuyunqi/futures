<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class=" fucheng fucheng0" style="width:500px">
	<div class=" fucheng_title"><span>升级方案</span><div class="clr"></div></div>
            	<div style=" text-align:center; padding:50px 0 20px; ">
        	是否确认要升级该方案？
    </div>
                
    <div style="text-align:center; margin:30px 0 0px 20px; padding-bottom:30px;">
        <a href="javascript:void(0);" onclick="upProgramInfo();" class=" queren">确认</a>
        <a href="javascript:void(0);" onclick="$.fancybox.close();" class=" quxiao">取消</a>
    </div>
                
    </div>
<script>
	function upProgramInfo(){
		location.href="${ctx}/index_info/index.htm?ppId=${id}#peizi";
	}
</script>