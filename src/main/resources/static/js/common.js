let slider_margin_left=0.14;

function sliderHide(){
    $("#toc").transition('zoom',500)
    $("#toc").removeClass("overlay visible")
    $("#main-content").stop().animate({right: "0"},200,function (){
        $(".placeholder-div a").text("巷道")
    })
    $("#main-content").width("100%")
    $(".placeholder-div").width($("#header-outline").width()*slider_margin_left/2)
    $("#leftResizeFlag").text("true")
}
function sliderDisplay(){
    $("#main-content").width((1-slider_margin_left)*100+"%")
    $("#main-content").stop().animate({right: "0"},200,function (){
        $(".placeholder-div a").text("巷道感知神经元")
    })
    $(".placeholder-div").width($("#header-outline").width()*slider_margin_left)
    $("#toc").transition('zoom',500)
    $("#toc").addClass("overlay visible")
    $("#leftResizeFlag").text("true")
}
$(document).ready(function(){
    let trasitionFlag=false;
    $("body").css("animation","gradient-move-to 5s ease")
    let trasitionInterval=setInterval(()=>{
        if (trasitionFlag){
            $("body").css("animation","gradient-move-to 5s ease")
            trasitionFlag=false
        }else {
            $("body").css("animation","gradient-move-from 5s ease")
            trasitionFlag=true
        }
    },5000)

    //用户点击slider-hide标志
    let isHide=true;

    $('.ui.accordion').accordion({duration:'click'});
    $(".ui.dropdown").dropdown();

    $(".slide-hide").click(function (){
        if($("#toc").hasClass("overlay")){
            sliderHide()
            isHide=true;
        }else {
            sliderDisplay()
            isHide=false;
        }
    })
    //滚动行为
    $(window).scroll(function (){
        if ($(window).scrollTop()===0){
            isHide=true
            !$("#toc").hasClass("overlay")?sliderDisplay():null
        }else {
            isHide?$("#toc").hasClass("overlay")?sliderHide():null:null
        }
    })

    $(".slider-mux-content").each(function (i,e){
        $(e).children().each(function (index,child){
            $(child).removeClass('active')
        })
    })

    $(".apage").each(function (i,e){
        $(e).removeClass('active')
        if (window.location.href===$(e).prop("href")) {
            $(e).addClass('active')
            $(e).parent().parent().parent().children().each((index,child)=>{
                $(child).addClass('active')
            })
        }
    })

});


