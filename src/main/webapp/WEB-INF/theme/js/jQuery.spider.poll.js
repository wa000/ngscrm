(function ($){
    var defaults = {
        title:null,//上方的标题
        titleColor:'#C3C3C3',
        titleFontSize:'15px',
        titleAlign:'center',
        width:'500px',//一共的宽度
        speed:1000,//显示效果的时间（毫秒）
        bgImg:'../theme/img/vote/bg.gif',//背景
        multiple:false,//是否多选
        showPoll:false,//是否显示投票
        data:'',//数据
        itelTotal:6,//一共支持的颜色数量
        itemStyle:{
            fontSize:'12px',//每条文字的大小
            bgColor:'#fffae2'//每条的背景颜色
        }
    };
    $.fn.poll = function (id,options){
        options = $.extend(defaults,options);
        var dataObj = eval("("+options.data+")");//数据JSON
        var o = this;
        var total=0;
        $.each(dataObj.root,function (i,n){//统计一共的投票数
            total+=parseInt(n.value);
        });
        $(this).append("<table id='"+id+"' border='0' style='font-size:"+options.itemStyle.fontSize+";' width='"+defaults.width+"'></table>");//设置TABLE的长度
        if(null != options.title) //是否显示标题
        $("table",this).append("<tr><td colspan=4 align='"+options.titleAlign+"' ><span style='color:"+options.titleColor+";font-size:"+options.titleFontSize+";'>"+options.title+"</span></td></tr>");
        
        var itemDiv;
        $.each(dataObj.root,function (i,n){
            var index=0;//当前个数，超过5个循环取
            var percentage = (n.value/total*100).toFixed(2);//取后两位百分比
            if(isNaN(percentage)){
                percentage=0;
            }
            var imgWidth = parseFloat(percentage);
            if(imgWidth>0)
            {
                if(i>(options.itelTotal-1))
                    index = i-(options.itelTotal-1);
                else
                    index = i;
                itemDiv="<div style='background-color:"+options.itemStyle.bgColor+";font-size:"+options.itemStyle.fontSize+"'><div divWidth='"+imgWidth+"' style='width:0%;background-image:url("+options.bgImg+");' class='poll_plan"+index+"' ><div class='plan_e' style='background-image:url("+options.bgImg+");'><div class='plan_c'  style='background-image:url("+options.bgImg+");'></div></div></div></div>";
            }
            else
            {
                itemDiv='';
            }
            $("table",o).append("<tr><td width='30%' align='right' >"+n.name+":</td><td width='60%' bgcolor='"+options.itemStyle.bgColor+"' >"+itemDiv+"</td><td width='10%' nowrap >"+n.value+"("+percentage+"%)</td></tr>");
            
            if(options.showPoll){//是否显示投票
                if(options.multiple){//是否是多选
                    $("tr:last").append("<td><input type='checkbox' name='poll_"+id+"' value='"+n.id+"'/></td>");
                }else {
                    $("tr:last").append("<td><input type='radio' name='poll_"+id+"' value='"+n.id+"'/></td>");
                }
            }
        });
        $("div",o).each(function(i,n){
            if($(n).attr('divWidth'))
            {
                $(n).animate( { width: $(n).attr('divWidth')+'%'}, options.speed );
                $(n).removeAttr("divWidth");
            }
        });
        return this;
    };
    $.fn.getChecked=function (){
        var checked_val;
        return $(":checked",this);
    };
})(jQuery);