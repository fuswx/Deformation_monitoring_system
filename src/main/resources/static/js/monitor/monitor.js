(()=>{

})();

//饼状图
// (()=>{
//     const myChart=echarts.init(document.querySelector("#pieTest"))
//     const options={
//         tooltip: {
//             trigger: 'item',
//             formatter: '{a} <br/>{b} : {c} ({d}%)'
//         },
//         legend: {
//             bottom:'0',
//             left:'0',
//             itemWidth: 10,
//             itemHeight: 10,
//             textStyle:{
//                 color:'rgba(0,0,0,0.5)',
//                 fontSize:11
//             },
//             data: [
//                 'rose1',
//                 'rose2',
//                 'rose3',
//                 'rose4',
//                 'rose5',
//                 'rose6',
//                 'rose7',
//                 'rose8'
//             ]
//         },
//         toolbox: {
//             show: true,
//             feature: {
//                 mark: { show: true },
//                 dataView: { show: true, readOnly: false },
//                 restore: { show: true },
//                 saveAsImage: { show: true }
//             }
//         },
//         series: [
//             {
//                 name: 'Radius Mode',
//                 type: 'pie',
//                 radius: 180,
//                 center: ['50%', '50%'],
//                 //roseType: 'radius',
//                 itemStyle: {
//                     borderRadius: 3
//                 },
//                 label: {
//                     show: false
//                 },
//                 emphasis: {
//                     label: {
//                         show: true
//                     }
//                 },
//                 data: [
//                     { value: 40, name: 'rose 1' },
//                     { value: 33, name: 'rose 2' },
//                     { value: 28, name: 'rose 3' },
//                     { value: 22, name: 'rose 4' },
//                     { value: 20, name: 'rose 5' },
//                     { value: 15, name: 'rose 6' },
//                     { value: 12, name: 'rose 7' },
//                     { value: 10, name: 'rose 8' }
//                 ]
//             }
//         ]
//     }
//     myChart.setOption(options)
//     window.addEventListener('resize',()=>myChart.resize())
// })();

$(function (){
    setChart();
    $(".showBtnBox").width($(".rightContent").width()*0.95)

    $(".showBtn").hover(function (){
        $(".showBtnBox").transition('fade left')
    },function (){
        $(".showBtnBox").transition('fade left')
    })

    console.log(window.prototype)
})

function setChart(){
    let myChart=echarts.init(document.querySelector("#lineTest"))
    const options={
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            }
        },
        legend: {
            data: ['数据1', '数据2'],
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
            containLabel: true //grid大小是否包含左侧的label
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月','八月','九月','十月','十一月','十二月'],
                axisLabel:{
                    interval: 0
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
                name: '数据1',
                type: 'line',
                stack: 'Total',
                smooth:true,
                emphasis: {
                    focus: 'series'
                },
                data: [120, 132, 101, 134, 90, 230, 210,120, 132, 101, 134, 90],
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
                }
            },
            {
                name: '数据2',
                type: 'line',
                stack: 'Total',
                smooth:true,
                emphasis: {
                    focus: 'series'
                },
                data: [220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234, 290],
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
                }
            }
        ]
    }
    myChart.setOption(options)
    new MutationObserver(()=>{
        $('.leftContent').css('flex')==='8 1 0%'?$('.leftContent').css('flex','7'):$('.leftContent').css('flex','8')
        $('.rightContent').css('flex')==='2 1 0%'?$('.rightContent').css('flex','3'):$('.rightContent').css('flex','2')
        //myChart=echarts.init(document.querySelector("#lineTest"))
        //myChart.setOption(options)
    }).observe(document.getElementById('main-content'),{
        attributes:['width']
    })
    window.addEventListener('resize',()=>myChart.resize())
    //document.getElementById('main-content').addEventListener('resize',()=>myChart.resize())
}
