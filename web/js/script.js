jQuery(document).ready(function($) {
    $("#myTable").tablesorter({

        widgets:['zebra'],
        debug:true,
        widthFixed:true
    }).tablesorterPager({
        size:10,
        container:$('#pager'),
        positionFixed:false
    });
});