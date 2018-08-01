<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<style>
.form_cont{margin-bottom:10px;}
.lf_font{width:150px;}
</style>
<div class="fuchen" style="width:600px">
		<div class=" fc_top" style="width:600px">
	    	<b class="fl fc_top_font">${empty id?'添加':'修改'}微期权信息</b>
        <div class="fl"></div>
	    </div>
	   <form id="wqqForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">名称：</div>
            <div class="fl input"><input id="wqqname" name="name" placeholder="必填" value="${contents.name}" type="text"/></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">标的物：</div>
            <div class="fl input"><input id="OOI" name="OOI" placeholder="必填" value="${contents.OOI}" type="text"/></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">涨跌幅度：</div>
            <div class="fl input"><input id="updownRegion" name="updownRegion" placeholder="" value="${contents.updownRegion}" type="text"/></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">开始日：</div>
            <div class="fl input"><input id="beginTime" name="beginTime" placeholder="必填" value="<fmt:formatDate value="${contents.beginTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}', dateFmt:'yyyy-MM-dd'})" type="text"/><i class="riqi"></i></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">结束日：</div>
            <div class="fl input"><input id="endTime" name="endTime" placeholder="必填" value="<fmt:formatDate value="${contents.endTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}', dateFmt:'yyyy-MM-dd'})" type="text"/><i class="riqi"></i></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">开始日收盘价：</div>
            <div class="fl input"><input id="beginPrice" name="beginPrice" placeholder="" value="${contents.beginPrice}" type="text"/></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">结束日收盘价：</div>
            <div class="fl input"><input id="endPrice" name="endPrice" placeholder="" value="${contents.endPrice}" type="text"/></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">申购开始时间：</div>
            <div class="fl input"><input id="buyBeginTime" name="buyBeginTime" placeholder="必填" value="${upDetail.buyBeginTime}" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'buyEndTime\')}', dateFmt:'yyyy.MM.dd'})" type="text"/><i class="riqi"></i></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">申购截止时间：</div>
            <div class="fl input"><input id="buyEndTime" name="buyEndTime" placeholder="必填" value="${upDetail.buyEndTime}" onclick="WdatePicker({minDate:'#F{$dp.$D(\'buyBeginTime\')}', dateFmt:'yyyy.MM.dd'})" type="text"/><i class="riqi"></i></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">封闭开始时间：</div>
            <div class="fl input"><input id="closeBeginTime" name="closeBeginTime" placeholder="必填" value="${upDetail.closeBeginTime}" onclick="WdatePicker({minDate:'#F{$dp.$D(\'buyEndTime\')}', maxDate:'#F{$dp.$D(\'closeEndTime\')}', dateFmt:'yyyy.MM.dd'})" type="text"/><i class="riqi"></i></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">封闭截止时间：</div>
            <div class="fl input"><input id="closeEndTime" name="closeEndTime" placeholder="必填" value="${upDetail.closeEndTime}" onclick="WdatePicker({minDate:'#F{$dp.$D(\'closeBeginTime\')}', dateFmt:'yyyy.MM.dd'})" type="text"/><i class="riqi"></i></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">结算时间：</div>
            <div class="fl input"><input id="clearTime" name="clearTime" placeholder="必填" value="${upDetail.clearTime}" onclick="WdatePicker({minDate:'#F{$dp.$D(\'closeEndTime\')}', dateFmt:'yyyy.MM.dd'})" type="text"/><i class="riqi"></i></div>
            <div class="clr"></div>
        </div>
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨详情档位数(档)：</div>
				            <div class="fl input"><input id="up_dw_num" name="up_dw_num" value="${upDetail.up_dw_num}" type="text" placeholder="必填" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌详情档位数(档)：</div>
				            <div class="fl input"><input id="down_dw_num" name="down_dw_num" value="${downDetail.down_dw_num}" type="text" placeholder="必填" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨最大收益倍数(倍)：</div>
				            <div class="fl input"><input id="up_max_bs" name="up_max_bs" value="${upDetail.up_max_bs}" type="text" placeholder="必填" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌最大收益倍数(倍)：</div>
				            <div class="fl input"><input id="down_max_bs" name="down_max_bs" value="${downDetail.down_max_bs}" type="text" placeholder="必填" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨详情最大涨幅(%)：</div>
				            <div class="fl input"><input id="up_max_zf" name="up_max_zf" value="${upDetail.up_max_zf}" type="text" placeholder="必填" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌详情最大涨幅(%)：</div>
				            <div class="fl input"><input id="down_max_zf" name="down_max_zf" value="${downDetail.down_max_zf}" type="text" placeholder="必填" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨详情最小涨幅(%)：</div>
				            <div class="fl input"><input id="up_min_zf" name="up_min_zf" value="${upDetail.up_min_zf}" type="text" placeholder="必填" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌详情最小涨幅(%)：</div>
				            <div class="fl input"><input id="down_min_zf" name="down_min_zf" value="${downDetail.down_min_zf}" type="text" placeholder="必填" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨档位1(%)：</div>
				            <div class="fl input"><input id="up_dw1" name="up_dw1" value="${upDetail.up_dw1}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌档位1(%)：</div>
				            <div class="fl input"><input id="down_dw1" name="down_dw1" value="${downDetail.down_dw1}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨档位2(%)：</div>
				            <div class="fl input"><input id="up_dw2" name="up_dw2" value="${upDetail.up_dw2}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌档位2(%)：</div>
				            <div class="fl input"><input id="down_dw2" name="down_dw2" value="${downDetail.down_dw2}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨档位3(%)：</div>
				            <div class="fl input"><input id="up_dw3" name="up_dw3" value="${upDetail.up_dw3}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌档位3(%)：</div>
				            <div class="fl input"><input id="down_dw3" name="down_dw3" value="${downDetail.down_dw3}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨档位4(%)：</div>
				            <div class="fl input"><input id="up_dw4" name="up_dw4" value="${upDetail.up_dw4}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌档位4(%)：</div>
				            <div class="fl input"><input id="down_dw4" name="down_dw4" value="${downDetail.down_dw4}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨档位5(%)：</div>
				            <div class="fl input"><input id="up_dw5" name="up_dw5" value="${upDetail.up_dw5}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌档位5(%)：</div>
				            <div class="fl input"><input id="down_dw5" name="down_dw5" value="${downDetail.down_dw5}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨档位6(%)：</div>
				            <div class="fl input"><input id="up_dw6" name="up_dw6" value="${upDetail.up_dw6}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌档位6(%)：</div>
				            <div class="fl input"><input id="down_dw6" name="down_dw6" value="${downDetail.down_dw6}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨档位7(%)：</div>
				            <div class="fl input"><input id="up_dw7" name="up_dw7" value="${upDetail.up_dw7}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌档位7(%)：</div>
				            <div class="fl input"><input id="down_dw7" name="down_dw7" value="${downDetail.down_dw7}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨档位8(%)：</div>
				            <div class="fl input"><input id="up_dw8" name="up_dw8" value="${upDetail.up_dw8}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌档位8(%)：</div>
				            <div class="fl input"><input id="down_dw8" name="down_dw8" value="${downDetail.down_dw8}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨档位9(%)：</div>
				            <div class="fl input"><input id="up_dw9" name="up_dw9" value="${upDetail.up_dw9}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌档位9(%)：</div>
				            <div class="fl input"><input id="down_dw9" name="down_dw9" value="${downDetail.down_dw9}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨档位10(%)：</div>
				            <div class="fl input"><input id="up_dw10" name="up_dw10" value="${upDetail.up_dw10}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌档位10(%)：</div>
				            <div class="fl input"><input id="down_dw10" name="down_dw10" value="${downDetail.down_dw10}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨档位1收益率(%)：</div>
				            <div class="fl input"><input id="up_dw1_val" name="up_dw1_val" value="${upDetail.up_dw1_val}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌档位1收益率(%)：</div>
				            <div class="fl input"><input id="down_dw1_val" name="down_dw1_val" value="${downDetail.down_dw1_val}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨档位2收益率(%)：</div>
				            <div class="fl input"><input id="up_dw2_val" name="up_dw2_val" value="${upDetail.up_dw2_val}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌档位2收益率(%)：</div>
				            <div class="fl input"><input id="down_dw2_val" name="down_dw2_val" value="${downDetail.down_dw2_val}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨档位3收益率(%)：</div>
				            <div class="fl input"><input id="up_dw3_val" name="up_dw3_val" value="${upDetail.up_dw3_val}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌档位3收益率(%)：</div>
				            <div class="fl input"><input id="down_dw3_val" name="down_dw3_val" value="${downDetail.down_dw3_val}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨档位4收益率(%)：</div>
				            <div class="fl input"><input id="up_dw4_val" name="up_dw4_val" value="${upDetail.up_dw4_val}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌档位4收益率(%)：</div>
				            <div class="fl input"><input id="down_dw4_val" name="down_dw4_val" value="${downDetail.down_dw4_val}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨档位5收益率(%)：</div>
				            <div class="fl input"><input id="up_dw5_val" name="up_dw5_val" value="${upDetail.up_dw5_val}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌档位5收益率(%)：</div>
				            <div class="fl input"><input id="down_dw5_val" name="down_dw5_val" value="${downDetail.down_dw5_val}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨档位6收益率(%)：</div>
				            <div class="fl input"><input id="up_dw6_val" name="up_dw6_val" value="${upDetail.up_dw6_val}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌档位6收益率(%)：</div>
				            <div class="fl input"><input id="down_dw6_val" name="down_dw6_val" value="${downDetail.down_dw6_val}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨档位7收益率(%)：</div>
				            <div class="fl input"><input id="up_dw7_val" name="up_dw7_val" value="${upDetail.up_dw7_val}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌档位7收益率(%)：</div>
				            <div class="fl input"><input id="down_dw7_val" name="down_dw7_val" value="${downDetail.down_dw7_val}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨档位8收益率(%)：</div>
				            <div class="fl input"><input id="up_dw8_val" name="up_dw8_val" value="${upDetail.up_dw8_val}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌档位8收益率(%)：</div>
				            <div class="fl input"><input id="down_dw8_val" name="down_dw8_val" value="${downDetail.down_dw8_val}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨档位9收益率(%)：</div>
				            <div class="fl input"><input id="up_dw9_val" name="up_dw9_val" value="${upDetail.up_dw9_val}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌档位9收益率(%)：</div>
				            <div class="fl input"><input id="down_dw9_val" name="down_dw9_val" value="${downDetail.down_dw9_val}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">涨档位10收益率(%)：</div>
				            <div class="fl input"><input id="up_dw10_val" name="up_dw10_val" value="${upDetail.up_dw10_val}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				       		<div class="lf_font fl">跌档位10收益率(%)：</div>
				            <div class="fl input"><input id="down_dw10_val" name="down_dw10_val" value="${downDetail.down_dw10_val}" type="text" placeholder="" style="width:100px;"></div>
				            <div class="clr"></div>
				       </div>
				    </td>
				  </tr>
			</tbody>
		</table>
        <div class=" but" style="margin-bottom:-20px;"><domi:privilege url="/admin_op_wqq_contents/saveWqqContents.htm"><a href="javascript:void(0);" onclick="saveContents();" class="sure fl">确认</a></domi:privilege><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
//给表单添加默认值
$(function(){
	if(${id==null}){
		$("#OOI").val("沪深300指数");
		$("#up_dw_num").val(9);
		$("#down_dw_num").val(9);
		$("#up_max_bs").val(1.7);
		$("#down_max_bs").val(1.7);
		$("#up_max_zf").val(6);
		$("#down_max_zf").val(6);
		$("#up_min_zf").val(3);
		$("#down_min_zf").val(3);
		
		$("#up_dw1").val(0);
		$("#down_dw1").val(0);
		$("#up_dw2").val(2.5);
		$("#down_dw2").val(2.5);
		$("#up_dw3").val(3);
		$("#down_dw3").val(3);
		$("#up_dw4").val(3.5);
		$("#down_dw4").val(3.5);
		$("#up_dw5").val(4);
		$("#down_dw5").val(4);
		$("#up_dw6").val(4.5);
		$("#down_dw6").val(4.5);
		$("#up_dw7").val(5);
		$("#down_dw7").val(5);
		$("#up_dw8").val(5.5);
		$("#down_dw8").val(5.5);
		$("#up_dw9").val(6);
		$("#down_dw9").val(6);
		
		
		$("#up_dw1_val").val(-100);
		$("#up_dw2_val").val(-100);
		$("#up_dw3_val").val(50);
		$("#up_dw4_val").val(100);
		$("#up_dw5_val").val(130);
		$("#up_dw6_val").val(150);
		$("#up_dw7_val").val(200);
		$("#up_dw8_val").val(270);
		$("#up_dw9_val").val(120);
		
		$("#down_dw1_val").val(-100);
		$("#down_dw2_val").val(-100);
		$("#down_dw3_val").val(50);
		$("#down_dw4_val").val(100);
		$("#down_dw5_val").val(120);
		$("#down_dw6_val").val(140);
		$("#down_dw7_val").val(170);
		$("#down_dw8_val").val(210);
		$("#down_dw9_val").val(120);
	}
})
function saveContents(){
	if(!$("#wqqname").val()){
		jAlert("微期权名称不能为空！","提示",function(){
			$("#wqqname").focus();
        });
		return false;
	}
	if(!$("#OOI").val()){
		jAlert("标的物不能为空！","提示",function(){
			$("#OOI").focus();
        });
		return false;
	}
	if(!$("#beginTime").val()){
		jAlert("开始日不能为空！","提示",function(){
			$("#beginTime").focus();
        });
		return false;
	}
	if(!$("#endTime").val()){
		jAlert("结束日不能为空！","提示",function(){
			$("#endTime").focus();
        });
		return false;
	}
	if(!$("#buyBeginTime").val()){
		jAlert("申购开始时间不能为空！","提示",function(){
			$("#buyBeginTime").focus();
        });
		return false;
	}
	if(!$("#buyEndTime").val()){
		jAlert("申购截止时间不能为空！","提示",function(){
			$("#buyEndTime").focus();
        });
		return false;
	}
	if(!$("#closeBeginTime").val()){
		jAlert("封闭开始时间不能为空！","提示",function(){
			$("#closeBeginTime").focus();
        });
		return false;
	}
	if(!$("#closeEndTime").val()){
		jAlert("封闭截止时间不能为空！","提示",function(){
			$("#closeEndTime").focus();
        });
		return false;
	}
	if(!$("#clearTime").val()){
		jAlert("结算时间不能为空！","提示",function(){
			$("#clearTime").focus();
        });
		return false;
	}
	if(!$("#up_dw_num").val()){
		jAlert("涨详情档位数不能为空！","提示",function(){
			$("#up_dw_num").focus();
        });
		return false;
	}
	if(!$("#down_dw_num").val()){
		jAlert("跌详情档位数不能为空！","提示",function(){
			$("#down_dw_num").focus();
        });
		return false;
	}
	if(!$("#up_max_bs").val()){
		jAlert("涨最大收益倍数不能为空！","提示",function(){
			$("#up_max_bs").focus();
        });
		return false;
	}
	if(!$("#down_max_bs").val()){
		jAlert("跌最大收益倍数不能为空！","提示",function(){
			$("#down_max_bs").focus();
        });
		return false;
	}
	if(!$("#up_max_zf").val()){
		jAlert("涨详情最大涨幅不能为空！","提示",function(){
			$("#up_max_zf").focus();
        });
		return false;
	}
	if(!$("#down_max_zf").val()){
		jAlert("跌详情最大涨幅不能为空！","提示",function(){
			$("#down_max_zf").focus();
        });
		return false;
	}
	if(!$("#up_min_zf").val()){
		jAlert("涨详情最小涨幅不能为空！","提示",function(){
			$("#up_min_zf").focus();
        });
		return false;
	}
	if(!$("#down_min_zf").val()){
		jAlert("跌详情最小涨幅不能为空！","提示",function(){
			$("#down_min_zf").focus();
        });
		return false;
	}
	var data=$("#wqqForm").serialize();
	$.post("${ctx}/admin_op_wqq_contents/saveWqqContents.htm?id=${id}",data,function(d){
		jAlert("保存成功！","提示",function(){
			location.href=location.href;
		});
	});
}
</script>
