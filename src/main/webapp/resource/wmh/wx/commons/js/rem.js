/**
 * Created by Administrator on 2017/4/1 0001.
 */
(function(window, document) {
    function rootSize() {
        var screenWidth = document.documentElement.clientWidth || window.innerWidth;
        //        console.log(screenWidth);
        var rootSize = screenWidth > 720 ? 100 :
            screenWidth < 320 ? 320 / 720 * 100 : screenWidth / 720 * 100;
        document.documentElement.style.fontSize = rootSize + 'px';
    }
    //        window.addEventListener('DOMContentLoaded',rootSize);
    window.onload = function() {
        rootSize();
    };
    window.onresize = function() {
        rootSize();
    }
})(window, document);
