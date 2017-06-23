var Sidebar = function () {
    return {
        //main function to initiate the module
        init: function () {
            $(".page-sidebar li .active").parent().parent().addClass("open active");
        }
    };
}();