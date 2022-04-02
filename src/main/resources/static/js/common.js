let slider_margin_left=0.14;
function sliderHide(){
    $("#toc").transition('zoom',500)
    $("#toc").removeClass("overlay visible")
    $("#main-content").stop().animate({right: "0"},200,function (){
        $(".placeholder-div a").text("UI")
    })
    $("#main-content").width("100%")
    $(".placeholder-div").width($("#header-outline").width()*slider_margin_left/2)
}
function sliderDisplay(){
    $("#main-content").width((1-slider_margin_left)*100+"%")
    $("#main-content").stop().animate({right: "0"},200,function (){
        $(".placeholder-div a").text("UI文档")
    })
    $(".placeholder-div").width($("#header-outline").width()*slider_margin_left)
    $("#toc").transition('zoom',500)
    $("#toc").addClass("overlay visible")
}
$(document).ready(function(){
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
});


