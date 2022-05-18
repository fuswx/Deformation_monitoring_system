let chartResizeInterval=null;

const projectName="/monitorSystem/";

let roadwayTotalTransform=[]
let updateTime=[]
let workingFacePress=[]
let stepPressures=[]
let crossHeadingPress=[]
let roadwayDepth=[]

let alertChinese=['瓦斯浓度','巷道变形','周期来压']
let alertCurrentValue=[]
let alertValue=[0.75,60,50]

let alertFlagPro=false
let alertFlagClose=false

let macStatusValue=[]
let macStatusValueChinese=['电量','风速','温度','瓦斯浓度']
const days=[0,1,2,3,4,5,6,7]
const days2=[0,1,2,3,4,5,6]

let labelMeasure=['%','t','°C','%']

//日期格式化
function timeStampString(time){
    var datetime = new Date(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return month+"月"+date+"日";
}

$(function (){
    //页面加载完成首先更新一次图表
    setChart();

    //直播尺寸初始化
    $("#liveVideo").width($("#realTime").width())
    $("#liveVideo").height($("#realTime").height())

    //设置定时器为5秒钟更新一次数据
    setInterval(()=>{
        setChart()
    },1000*5)

    $(".showBtnBox").width($(".rightContent").width()*0.95)

    $("#showBtnBoxClose").click(()=>{
        alertFlagClose=true
        alertFlagPro=false
        setTimeout(()=>{
            alertFlagClose=false
        },1000*60)
        $(".showBtnBox").transition('fade left')
    })

    $(".showBtn").hover(function (){
        $(".showBtnBox").transition('fade left')
    },function (){
        $(".showBtnBox").transition('fade left')
    })

    //初始化动画
    $(".firstLine,.secondLine").css('display','none')
    $(".firstLine,.secondLine").transition('scale',700)
})

function getDataByPost(){
    $.ajax({
        url: projectName+"device/getLastWorkingStatus.do",
        dataType: 'JSON',
        method: "post",
        contentType: "application/json",
        async: false,
        success:(res)=>{
            alertCurrentValue=[]
            macStatusValue=[]
            macStatusValue.push(res.buttery)
            macStatusValue.push(res.windSpeed)
            macStatusValue.push(res.temperature)
            macStatusValue.push(res.gasConcentration)
            alertCurrentValue.push(res.gasConcentration)
        }
    })
    $.ajax({
        url: projectName+"device/getByDaysVariableData.do",
        dataType: 'JSON',
        method: "post",
        contentType: "application/json",
        async: false,
        data: {
            days: 7
        },
        success:function (res) {
            roadwayTotalTransform=[]
            updateTime=[]
            workingFacePress=[]
            stepPressures=[]
            crossHeadingPress=[]
            roadwayDepth=[]
            for (const data of res) {
                roadwayTotalTransform.push(data.roadwayTotalTransform)
                updateTime.push(new Date(data.updateTime))
                workingFacePress.push(data.workingFacePress)
                stepPressures.push(data.stepPressures)
                crossHeadingPress.push(data.crossHeadingPress)
                roadwayDepth.push(data.roadwayDepth)
            }
            alertCurrentValue.push(res[roadwayTotalTransform.length-1].roadwayTotalTransform)
            alertCurrentValue.push(res[roadwayTotalTransform.length-1].stepPressure)
            $("#btnBoxTable > tbody").empty()
            alertFlagPro=false
            for (let i = 0; i < alertCurrentValue.length; i++) {
                if (alertCurrentValue[i]>=alertValue[i]){
                    $("#btnBoxTable > tbody").append("<tr>\n" +
                        "                    <td>"+alertChinese[i]+"</td>\n" +
                        "                    <td class=\"error\">"+alertCurrentValue[i]+" &nbsp;&nbsp;&nbsp;<i class=\"attention icon\"></i>异常！</td>\n" +
                        "                    <td>"+alertValue[i]+"</td>\n" +
                        "                </tr>")
                    alertFlagPro=true
                }else {
                    $("#btnBoxTable > tbody").append("<tr>\n" +
                        "                    <td>"+alertChinese[i]+"</td>\n" +
                        "                    <td>"+alertCurrentValue[i]+"</td>\n" +
                        "                    <td>"+alertValue[i]+"</td>\n" +
                        "                </tr>")
                }
            }
            if (alertFlagPro&&$(".showBtnBox").hasClass('hidden')&&!alertFlagClose){
                $(".showBtnBox").transition('fade left')
            }else if (!$(".showBtnBox").hasClass('hidden')&&!alertFlagPro) {
                $(".showBtnBox").transition('fade left')
            }

            //关于预测
            roadwayTotalTransform.push(res[roadwayTotalTransform.length-1].roadwayTotalTransformPre)
            roadwayDepth.push(roadwayDepth[roadwayDepth.length-1]+20)
            let preDate=new Date(res[updateTime.length-1].updateTime)
            preDate.setDate(preDate.getDate()+1)
            updateTime.push(preDate)
        }
    })
}

function setChart(){
    let transitionTime=300
    let myChart=echarts.init(document.querySelector("#lineTest"))
    let myChart2=echarts.init(document.querySelector("#lineTest2"))
    let macStatus=echarts.init(document.querySelector('#macStatus'))
    let toTime=timeStampString(new Date().getTime()+24*60*60*1000);
    let fromTime=timeStampString(new Date().getTime()-7*24*60*60*1000);

    getDataByPost()

    let myColor = ["rgb(1,120,255)", "#F57474", "#F8B448", "#7898f6"];

    macStatusValue[3]*=100

    let option1={
        visualMap:[
            {
                dimension: 0,
                show:false,
                splitNumber: 2,
                pieces:[],
                inRange: {
                    color: 'rgb(1,120,255)'
                },
                outOfRange:{
                    color :'rgb(1,120,255)'
                },
                seriesIndex: 0
            },
            {
                dimension: 0,
                show:false,
                splitNumber: 2,
                pieces:[],
                inRange: {
                    color: 'rgb(176,151,243)'
                },
                outOfRange:{
                    color: 'rgb(176,151,243)'
                },
                seriesIndex: 1
            },
        ],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                },
            },
            formatter: (params)=>{
                for (let i = 0; i < params.length; i++) {
                    let str=''
                    for (let j = 0; j < option1.series[0].data.length; j++) {
                        str="<div>"+timeStampString(updateTime[params[i].dataIndex]) +"</div>"
                            +"<div style='width: 10px;height: 10px;display: inline-block;background-color:rgb(176,151,243) '></div> 巷道埋深:"+roadwayDepth[params[i].dataIndex]+"m"+"<br/><div style='width: 10px;height: 10px;display: inline-block;background-color:rgb(1,120,255) '></div> 巷道累计变形量:"+roadwayTotalTransform[params[i].dataIndex]+"m";
                    }
                    return str
                }
            }
        },
        legend: {
            data: ['巷道累计变形量', '巷道埋深'],
            left:"5%",
            textStyle:{
                fontSize:'12'
            }
        },
        toolbox: {
            right:'5%',
            feature: {
                saveAsImage: {
                    title:'保存',
                }
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true, //grid大小是否包含左侧的label
            position: 'center'
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: days,
                axisLabel:{
                    interval: 0,
                    formatter: (value)=>{
                        return timeStampString(updateTime[value])
                    }
                },
                axisPointer:{
                    label:{
                        show: false
                    }
                },
                //x轴线颜色
                axisLine:{
                    lineStyle:{
                        color:'rgb(1,120,255)',
                        fontSize: 12,
                        fontWeight: 700
                    }
                },
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '巷道累计变形量',
                axisTick: {
                    show: false
                },
                axisLabel: {
                    textStyle: {
                        color:'#3f3f3f',
                        fontSize: 12
                    },

                }
            },
            {
                type: 'value',
                name: '巷道埋深',
                axisTick: {
                    show: false
                },
                axisLabel: {
                    textStyle: {
                        color:'#3f3f3f',
                        fontSize: 12
                    }
                }
            }
        ],
        series: [
            {
                name: '巷道累计变形量',
                type: 'line',
                // stack: 'Total',
                smooth:true,
                emphasis: {
                    focus: 'series'
                },
                yAxisIndex: 0,
                data: roadwayTotalTransform,
                //填充颜色设置
                areaStyle:{
                    color: new echarts.graphic.LinearGradient(
                        0,0,0,1,
                        [
                            {
                                offset:0,
                                color:'rgb(1,120,255)',//渐变色的起始颜色
                            },
                            {
                                offset: 0.95,
                                color: 'rgba(1,120,255,0.2)'
                            }
                        ],
                        false
                    ),
                    shadowColor:"rgba(0,0,0,0.1)"
                },
                itemStyle: {
                    color: 'rgb(1,120,255)'
                },
                animation:true,
                animationDuration: transitionTime,
                animationEasing: 'liner',
            },
            {
                name: '巷道埋深',
                type: 'line',
                // stack: 'Total',
                smooth:true,
                yAxisIndex: 1,
                emphasis: {
                    focus: 'series'
                },
                data: roadwayDepth,
                //填充颜色设置
                areaStyle:{
                    origin: 'start',
                    color: new echarts.graphic.LinearGradient(
                        0,0,0,1,
                        [
                            {
                                offset:0,
                                color:'rgb(176,151,243)',//渐变色的起始颜色
                            },
                            {
                                offset: 0.95,
                                color: 'rgba(176,151,243,0.2)'
                            }
                        ],
                        false
                    ),
                    shadowColor:"rgba(0,0,0,0.1)"
                },
                itemStyle: {
                    color: 'rgb(176,151,243)'
                },
                animation:true,
                animationDuration: transitionTime,
                animationEasing: 'liner',
            },

        ]
    }
    let option2={
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            },
            formatter: (params)=>{
                for (let i = 0; i < params.length; i++) {
                    let str=''
                    for (let j = 0; j < option1.series[0].data.length; j++) {
                        str="<div>"+timeStampString(updateTime[params[i].dataIndex]) +"</div>"
                            +"<div style='width: 10px;height: 10px;display: inline-block;background-color:rgb(1,120,255) '></div> 工作面矿压:"+workingFacePress[params[i].dataIndex]+"MPa"+"<br/><div style='width: 10px;height: 10px;display: inline-block;background-color:rgb(176,151,243) '></div> 顺槽小煤柱矿压:"+crossHeadingPress[params[i].dataIndex]+"KN";
                    }
                    return str
                }
            }
        },
        legend: {
            data: ['工作面矿压', '顺槽小煤柱矿压'],
            left:"5%",
            textStyle:{
                fontSize:'12'
            }
        },
        toolbox: {
            right:'5%',
            feature: {
                saveAsImage: {
                    title:'保存',
                }
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true, //grid大小是否包含左侧的label
            position: 'center'
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: days2,
                axisLabel:{
                    interval: 0,
                    formatter: (value)=>{
                        return timeStampString(updateTime[value])
                    }
                },
                //x轴线颜色
                axisLine:{
                    lineStyle:{
                        color:'rgb(1,120,255)',
                        fontSize: 12,
                        fontWeight: 700
                    }
                },
                axisPointer:{
                    label:{
                        show: false
                    }
                },
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '工作面矿压',
                axisTick: {
                    show: false
                },
                axisLabel: {
                    textStyle: {
                        color:'#3f3f3f',
                        fontSize: 12
                    }
                }
            },
            {
                type: 'value',
                name: '顺槽小煤柱矿压',
                axisTick: {
                    show: false
                },
                axisLabel: {
                    textStyle: {
                        color:'#3f3f3f',
                        fontSize: 12
                    }
                }
            }
        ],
        series: [
            {
                name: '工作面矿压',
                type: 'line',
                smooth:true,
                yAxisIndex: 0,
                emphasis: {
                    focus: 'series'
                },
                data: workingFacePress,
                //填充颜色设置
                areaStyle:{
                    color: new echarts.graphic.LinearGradient(
                        0,0,0,1,
                        [
                            {
                                offset:0,
                                color:'rgb(1,120,255)',//渐变色的起始颜色
                            },
                            {
                                offset: 0.95,
                                color: 'rgba(1,120,255,0.2)'
                            }
                        ],
                        false
                    ),
                    shadowColor:"rgba(0,0,0,0.1)"
                },
                itemStyle: {
                    color: 'rgb(1,120,255)'
                },
                animation:true,
                animationDuration: transitionTime,
                animationEasing: 'liner',

            },
            {
                name: '顺槽小煤柱矿压',
                type: 'line',
                yAxisIndex: 1,
                smooth:true,
                emphasis: {
                    focus: 'series'
                },
                data: crossHeadingPress,
                //填充颜色设置
                areaStyle:{
                    color: new echarts.graphic.LinearGradient(
                        0,0,0,1,
                        [
                            {
                                offset:0,
                                color:'rgb(176,151,243)',//渐变色的起始颜色
                            },
                            {
                                offset: 0.95,
                                color: 'rgba(176,151,243,0.2)'
                            }
                        ],
                        false
                    ),
                    shadowColor:"rgba(0,0,0,0.1)"
                },
                itemStyle: {
                    color: 'rgb(176,151,243)'
                },
                animation:true,
                animationDuration: transitionTime,
                animationEasing: 'liner',
            }
        ]
    }
    let macStatusOption = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            show: false
        },
        grid: {
            left: '22%',
            bottom: '10%',
            top: "10%",
            containLabel: false //grid大小是否包含左侧的label
        },
        //不显示x轴的相关信息
        xAxis: {
            show: false
        },
        yAxis: [
            {
                inverse:true,
                type: 'category',
                data: macStatusValueChinese,
                //不显示y轴的线
                axisLine: {
                    show: false
                },
                //不显示y轴的刻度
                axisTick: {
                    show: false
                },
                //把刻度标签里面的文字设置颜色
                axisLabel:{
                    color:'#000'
                }
            },
            {
                inverse:true,
                type: 'category',
                data: macStatusValue,
                //不显示y轴的线
                axisLine: {
                    show: false
                },
                //不显示y轴的刻度
                axisTick: {
                    show: false
                },
                //把刻度标签里面的文字设置颜色
                axisLabel:{
                    color:'#fff'
                }
            }
        ],
        series: [
            {
                name: '数值',
                type: 'bar',
                data: macStatusValue,
                //柱子之间的距离
                barCategoryGap:50,
                //柱子的高度
                barWidth:14,
                yAxisIndex:0,
                //修改第一组柱子的圆角
                itemStyle:{
                    barBorderRadius: 20,
                    //此时的color可以修改柱子的颜色
                    color:(params)=>{
                        //params传进来的是柱子对象
                        //dataIndex是当前柱子的索引号
                        return myColor[params.dataIndex]
                    }
                },//显示柱子内的文字
                label:{
                    normal:{
                        show:true,
                        //图形内显示
                        position: 'inside',
                        //{c}会自动解析为数据，是data里面的数字
                        formatter:(value)=>{
                            return value.dataIndex===3?macStatusValue[value.dataIndex]/100+labelMeasure[value.dataIndex]:macStatusValue[value.dataIndex]+labelMeasure[value.dataIndex];
                        },
                        color:'#fff'
                    }
                }
            }
        ],
    };

    macStatus.setOption(macStatusOption)

    option1.visualMap[0].pieces[0]={gte:6,lte:7,color:'rgb(227,11,11)'}
    option1.visualMap[1].pieces[0]={gte:6,lte:7,color:'rgb(227,11,11)'}
    myChart.setOption(option1)
    myChart2.setOption(option2)


    /*new MutationObserver(()=>{
        $('.leftContent').css('flex')==='8 1 0%'?$('.leftContent').css('flex','7'):$('.leftContent').css('flex','8')
        $('.rightContent').css('flex')==='2 1 0%'?$('.rightContent').css('flex','3'):$('.rightContent').css('flex','2')
    }).observe(document.getElementById('main-content'),{
        attributes:['width']
    })*/

    window.addEventListener('resize',()=>{
        myChart2.resize()
        myChart.resize()
        macStatus.resize()
        $("#liveVideo").width($("#realTime").width())
        $("#liveVideo").height($("#realTime").height())
    })

    if (chartResizeInterval){
        clearInterval(chartResizeInterval)
    }
    chartResizeInterval=setInterval(()=>{
        if ($("#leftResizeFlag").text()==='true'){
            myChart.showLoading()
            myChart2.showLoading()
            const middleInterval=setTimeout(()=>{
                myChart.resize()
                myChart2.resize()
                myChart.clear()
                myChart2.clear()
                myChart.setOption(option1)
                myChart2.setOption(option2)
                myChart.hideLoading()
                myChart2.hideLoading()
                $("#liveVideo").width($("#realTime").width())
                $("#liveVideo").height($("#realTime").height())
                macStatus.resize()
            },500)
            $("#leftResizeFlag").text("false")
        }
    },100)

}
